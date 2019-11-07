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

.back {
	background-color: #e4ead8;
}

.form-control{
	color: black !important;
	}
.error{
	color:red;
}
body {
	background-color: #e4ead8;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$(function() {
			$('form[id="vehicleDtls"]').validate({
				rules : {
					companyName : 'required',
					mfdYear : {
						valueNotEquals : "-1"
					},
					vehicleType : {
						valueNotEquals : "default"
					},
					color: 'required'
				},
				messages : {
					companyName : 'Please Enter CompanyName',
					mfdYear : {
						valueNotEquals : "Please select an Year!"
					},
					vehicleType : {
						valueNotEquals : "Please select an vehicle type!"
					},
					color: 'please Enter color Name'
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
		return confirm("Are You want to move to Previous page..!!");
	}
</script>
<meta charset="ISO-8859-1">
<title>Vehicle Details</title>
</head>
<body>
	<div class="back">
		<div class="container">
			<h2 class="text-center h2 text-secondary">Vehicle Details Form</h2>
			<form:form id="vehicleDtls" action="saveVehicleDtls"
				modelAttribute="vehicleDtls" method="post" cssClass="form-group">
				<input type="hidden" name="ownerId" value="${vehicleOwnerKey}" />
				<input type="hidden" name="vehicleId"
					value="${vehicleDtls.vhicleDtlsId}" />
				<label for="vehicleType" class="text-info">Select vehicle
					Type</label>
				<form:select path="vehicleType" cssClass="form-control">
					<form:option value="default">Select vehicle Type</form:option>
					<form:option value="2-Wheeler">2-Wheeler</form:option>
					<form:option value="4-Wheeler">4-Wheeler</form:option>
					<form:option value="6-Wheeler">6-Wheeler</form:option>
					<form:option value="8-Wheeler">8-Wheeler</form:option>
					<form:option value="10-Wheeler">10-Wheeler</form:option>
				</form:select>
				<br>
				<label for="companyName" class="text-info">Vehicle Company
					Name</label>
				<form:input path="companyName" cssClass="form-control" />
				<br>
				<label for="lname" class="text-info">Select Manufacturing
					Year</label>
				<form:select path="mfdYear" cssClass="form-control">
					<form:option value="-1">select mfd year</form:option>
					<form:option value="2013">2013</form:option>
					<form:option value="2014">2014</form:option>
					<form:option value="2015">2015</form:option>
					<form:option value="2016">2016</form:option>
					<form:option value="2017">2017</form:option>
					<form:option value="2018">2018</form:option>
					<form:option value="2019">2019</form:option>
				</form:select>
				<br>
				<label for="color" class="text-info">Enter your Color Name</label>
				<form:input path="color" cssClass="form-control" />
				<br>
				<div align="center" class="text-info">
					<a
						href="addrindex?ownerId=${vehicleOwnerKey}&addrId=${vehicleAddrKey}"
						class="btn btn-outline-info" onclick="return prevConf()"> &lt;
						Previous </a> <input id="submitBtn" type="submit" value="Next >"
						class="btn btn-outline-success" />
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>