<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Details</title>
<link rel="stylesheet" href="./views/css/bootstrap.min.css" />
<style type="text/css">
.text-right {
	margin-left: 520px;
}
</style>
</head>
<body>
	<span class="h4 text-left text-success">${msg} :: &nbsp;
		${vchlReg.vehicleRegNumber}</span>
	<a href="ownerForm" class="text-right btn btn-outline-info btn-lg">Go
		Back To Home</a>
	<table class="table table-striped table-bordered">
		<tr>
			<th colspan="2" class=" h3 text-center text-danger">Vehicle
				Registration Details</th>
		</tr>
		<tr>
			<th>Vehicle Registration Id</th>
			<td>${vchlReg.vehicleRegId}</td>
		</tr>
		<tr>
			<th>Vehicle Reg. Number</th>
			<td>${vchlReg.vehicleRegNumber}</td>
		</tr>
		<tr>
			<th>Registration Center</th>
			<td>${vchlReg.regCenter}</td>
		</tr>
		<tr>
			<th>Registration Date</th>
			<td>${vchlReg.regDate}</td>
		</tr>
	</table>
	<br>
	<a href="ownerForm" class="text-center btn btn-outline-info btn-lg">Go
		Back To Home</a>
</body>
</html>