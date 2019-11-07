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
<script type="text/javascript" src="./views/js/jquery-ui.js"></script>
<style type="text/css">
.container {
	max-width: 540px;
	width: 500px;
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
.error{
	color: red;
	
}
body{
background-color: #e4ead8;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#dob").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : "1987:2003"
		});
    
		$(function() {
			$('form[id="ownerDtls"]').validate({
					rules :{
						fname: 'required',
						lname: 'required',
						dob :{
							date: true
							
							},
						email :{
							required :true,
							email:true
								},
							phno : {
									required : true,
									number : true,
									minlength : 10,
									maxlength : 10
								},			
								zzn :{
									required : true,
									maxlength: 11,
									minlength: 11
									}	
						},
						messages : {
							fname : 'Please Enter First Name',
							lname : 'Please Enter Last Name',
							dob : {
								date : 'Please Enter Date',
								
							},
							email : {
								required: 'Please Enter email',
								email :'Enter valid Format'
							},
							phno : {
								required : 'Please Enter phone Number',
								number :'Enter Only Numbers',
								minlength: 'Phone no. must be 10 digit',
								maxlength:'Phone No. Must be 10 digit'
							},
							zzn :{	
							required: 'Please Enter SSN Number',
							maxlength: 'Only 9 number are Entered',
							minlength: 'only 9 digit are Entered'
								}
						},
						submitHandler : function(form) {
							form.submit();
						}
					});	
				});
	

	$('#zzn').keypress(function(e){
		console.log(e);
		console.log(typeof(e.which));
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			return false;
		 }	
		else  if (e.which != 8 && e.which != 0 && (e.which <= 57 &&  e.which >= 48)) {
		var zzn1=$('#zzn').val();
				console.log(e);
			if(event.keyCode!='8'){
				console.log("jtkakasm");
				if(zzn1.length==6){
					$('#zzn').val(zzn1+'-');
					}
				else if(zzn1.length==3){
					$('#zzn').val(zzn1+'-');
				}
			}
		}
	});

});

	function clearConf(){

	return confirm("Are you want to reset Your Form....!!");
}
	
</script>

<meta charset="ISO-8859-1">
<title>Owner Details</title>
</head>
<body >
	<div class="back">
		<div class="container">
			<h2 class="text-center h2 text-secondary">Vehicle Registration
				Form</h2>
			<form:form id="ownerDtls" action="saveDtls"
				modelAttribute="vehicleOwnerDtls" method="post"
				cssClass="form-group">
				<input type="hidden" name="ownerId" value="${vehicleOwnerDtls.vhclOwnerid}"/>
				<label for="fname" class="text-info">Enter your First Name</label>
				<form:input path="fname" cssClass="form-control" />
				<br>
				<label for="lname" class="text-info">Enter your Last Name</label>
				<form:input path="lname" cssClass="form-control" />
				<br>
				<label for="dob" class="text-info">Enter your Date Of Birth</label>
				<form:input path="dob" cssClass="form-control" placeholder="mm/dd/yyyy"/>
				<br>
				<label for="gender" class="text-info">Choose gender:: </label> &ensp;
				<form:radiobutton path="gender" value="Male" checked="true"  />Male &emsp;
				<form:radiobutton path="gender" value="female"  />Female
				<br>
				<label for="email" class="text-info">Enter your Mail Address</label>
				<form:input path="email" cssClass="form-control" />
				<br>
				<label for="phno" class="text-info">Enter Your Mobile Number</label>
				<form:input path="phno" cssClass="form-control" />
				<br>
				<label for="zzn" class="text-info">Enter your SSN Id</label>
				<form:input path="zzn" cssClass="form-control" placeholder="123-45-6789" maxlength="11"/>
				<br>
				<div align="center" class="text-info">
					<input type="reset" value="clear" class="btn btn-outline-danger" onclick="return clearConf()"/>
					<input type="submit" value="Next >" class="btn btn-outline-success" />
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>