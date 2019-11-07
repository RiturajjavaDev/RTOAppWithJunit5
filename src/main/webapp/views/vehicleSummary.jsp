<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./views/css/bootstrap.min.css" />
<meta charset="ISO-8859-1">
<title>Vehicle Details</title>
<style type="text/css">
.btn {
	width: 200px !important;
}
</style>

<script type="text/javascript">
	function confPrev() {
		return confirm("Do you want to Modify  any Details....!!");
	}
</script>

</head>
<body>
	<h2 class="h2 text-center text-primary">Vehicle Registration
		Summary...!!</h2>

	<table class="table table-striped table-bordered">
		<tr>
			<th colspan="2" class=" h3 text-center text-danger">Vehicle
				Owner Details...</th>
		</tr>
		<tr>
			<th>Vehicle Owner Code</th>
			<td>${vehicleOwnerDtls.vhclOwnerid}</td>
		</tr>
		<tr>
			<th>Vehicle Owner Name</th>
			<td>${vehicleOwnerDtls.fname}${vehicleOwnerDtls.lname}</td>
		</tr>
		<tr>
			<th>Gender</th>
			<td>${vehicleOwnerDtls.gender}</td>
		</tr>
		<tr>
			<th>Birth Date</th>
			<td>${vehicleOwnerDtls.dob}</td>
		</tr>
		<tr>
			<th>Email</th>
			<td>${vehicleOwnerDtls.email}</td>
		</tr>
		<tr>
			<th>Contact Number</th>
			<td>${vehicleOwnerDtls.phno}</td>
		</tr>
		<tr>
			<th>SSN ID</th>
			<td>${vehicleOwnerDtls.zzn}</td>
		</tr>
		<tr>
			<th colspan="2" class=" h3 text-center text-danger">Vehicle
				Owner Address....</th>
		</tr>
		<tr>
			<th>House Number</th>
			<td>${ownerAddr.houseNo}</td>
		</tr>
		<tr>
			<th>Street Name</th>
			<td>${ownerAddr.streetName}</td>
		</tr>
		<tr>
			<th>City Name</th>
			<td>${ownerAddr.city}</td>
		</tr>
		<tr>
			<th>ZIP Code</th>
			<td>${ownerAddr.zip}</td>
		</tr>
		<tr>
			<th colspan="2" class=" h3 text-center text-danger">Vehicle
				Information</th>
		</tr>
		<tr>
			<th>Vehicle Type</th>
			<td>${vehicleDtls.vehicleType}</td>
		</tr>
		<tr>
			<th>Vehicle Company Name</th>
			<td>${vehicleDtls.companyName}</td>
		</tr>
		<tr>
			<th>Vehicle Company Name</th>
			<td>${vehicleDtls.color}</td>
		</tr>
		<tr>
			<th>Manufactured Year</th>
			<td>${vehicleDtls.mfdYear}</td>
		</tr>
	</table>
	<br>
	<div align="center">
		<a
			href="vchlIndex?ownerId=${vehicleOwnerDtls.vhclOwnerid}&addrId=${ownerAddr.addressId}&vehicleId=${vehicleDtls.vhicleDtlsId}"
			class="text-center btn btn-outline-warning" onclick="return confPrev()">Previous</a>
		<a
			href="register?ownerId=${vehicleOwnerDtls.vhclOwnerid}&center=${ownerAddr.city}&vchltype=${vehicleDtls.vehicleType}"
			class="text-center btn btn-outline-success">Register</a>
	</div>
</body>
</html>