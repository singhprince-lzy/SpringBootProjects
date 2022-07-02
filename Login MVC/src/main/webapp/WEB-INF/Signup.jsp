<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
</head>
<body class="p-3 bg-light" >

	<form action="final" class="row g-3 needs-validation" novalidate>
		<div class="col-md-4">
			<label for="validationCustom01" class="form-label">Name</label> <input
				type="text" class="form-control" name="name" id="name" required>
			<div class="valid-feedback">Looks good!</div>
		</div>
		<br>
		<div class="col-md-4">
			<label for="validationCustom01" class="form-label">email</label> <input
				type="text" class="form-control" name="email" id="email" required>
			<div class="valid-feedback">Looks good!</div>
		</div>
		<br>
		<div class="col-md-4">
			<label for="validationCustom01" class="form-label">PhoneNumber</label>
			<input type="text" class="form-control" name="pno" id="pno" required>
			<div class="valid-feedback">Looks good!</div>
		</div>
		<br>
		<div class="col-md-4">
			<label for="validationCustom01" class="form-label">Date of
				Birth</label> <input type="text" class="form-control" name="dob" id="dob"
				required>
			<div class="valid-feedback">Looks good!</div>
		</div>
		<br>
		<div class="col-md-4">
			<label for="validationCustom01" class="form-label">User name</label>
			<input type="text" class="form-control" name="username" id="username"
				required>
			<div class="valid-feedback">Looks good!</div>
		</div>
		<br>
		<div class="col-md-4">
			<label for="validationCustom01" class="form-label">Password</label> <input
				type="password" class="form-control" name="password" id="password"
				required>
			<div class="valid-feedback">Looks good!</div>
		</div>
		<br>
		<div class="col-12">
			<button class="btn btn-primary" type="submit">SignUp</button>
		</div>
	</form>
</body>
</html>