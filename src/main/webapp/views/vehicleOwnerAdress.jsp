<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./views/css/bootstrap.min.css" />
<link rel="stylesheet" href="./views/css/jquery-ui.css">
<script type="text/javascript" src="./views/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="./views/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="./views/js/additional-methods.min.js"></script>
<meta charset="ISO-8859-1">
<title>Owner Address Page</title>
<style type="text/css">
.container {
	max-width: 540px;
	width: 400px;
	border: 1px solid blue;
	border-radius: 7px;
	box-shadow: 5px 17px 10px #956d6d;
	background: #fff;
}

.text-info {
	color: #17a2b8 !important;
	font-weight: bold;
}
.form-control{
	color: black !important;
	}

.back {
	background-color: #e4ead8;
}

.error {
	color: red;
}

body {
	background-color: #e4ead8;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$(function() {
			$('form[id="vehicleAdrr"]').validate({
				rules : {
					houseNo : 'required',
					streetName : 'required',
					city : {
						valueNotEquals : "default"
					},
					zip : {
						required : true,
						number : true,
						minlength : 5,
						maxlength : 6
					}
				},
				messages : {
					houseNo : 'Please Enter valid house No.',
					streetName : 'Please Enter City Name ',
					city : {
						valueNotEquals : "Please select a City Name!"
					},
					zip : {
						required : 'Zip Code Required',
						number : 'Only Numbers allowed',
						minlength : 'minimum five  length required',
						maxlength : 'maximum 6 digit are required'
					}
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
		});
		$.validator.addMethod("valueNotEquals", function(value, element, arg) {
			return arg !== value;
		}, "Value must not equal arg.");
	});
</script>
<script type="text/javascript">
	function prevConf() {
		
		return confirm("Are you want to move to prevoius page....!!");
	}
</script>
</head>
<body>
	<div class="back">
		<div class="container">
			<h2 class="text-center h2 text-secondary">Vehicle Owner Address
				Form</h2>
			<form:form id="vehicleAdrr" action="handleNext"
				modelAttribute="ownerAddr" method="post" cssClass="form-group">
				<input type="hidden" name="ownerId" value="${vehicleOwnerKey}" />
				<input type="hidden" name="addrId" value="${ownerAddr.addressId}" />
				<label for="houseNo" class="text-info">Enter House Number</label>
				<form:input path="houseNo" cssClass="form-control" />
				<br>
				<label for="streetName" class="text-info">Enter Street Name</label>
				<form:input path="streetName" cssClass="form-control" />
				<br>
				<label for="city" class="text-info">Select City Name</label>
				<form:select path="city" cssClass="form-control">
					<form:option value="default">Select your City Name</form:option>
					<form:option value="Albany">Albany</form:option>
					<form:option value="Aliso Viejo">Aliso Viejo</form:option>
					<form:option value="Anaheim">Anaheim</form:option>
					<form:option value="Jamestown">Jamestown</form:option>
					<form:option value="Cortland">Cortland</form:option>
					<form:option value="Richmond">Richmond</form:option>
				</form:select>
				<br>
				<label for="zip" class="text-info">Enter Zip Code</label>
				<form:input path="zip" cssClass="form-control" />
				<br>
				<div align="center" class="text-info">
					<a href="ownerForm?ownerId=${vehicleOwnerKey}"
						class="btn btn-outline-info" onclick="return prevConf()"> &lt;
						Previous </a> <input id="submitBtn" type="submit" value="Next >"
						class="btn btn-outline-success" />
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>