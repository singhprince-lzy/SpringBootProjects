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
<style>
#div {
	width: 300px;
	height: 200px;
	background: red;
	animation: mymove 5s infinite;
}

@
keyframes mymove {
	from {background-color: red;
}

to {
	background-color: blue;
}
}
</style>
<body class="p-3 bg-light">
	<nav
		class="navbar navbar-expand-lg navbar-light bg-light static-top mb-5 shadow">
		<div class="container">
			<a class="navbar-brand" href="#">BreakOut 3</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Home</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">About</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Services</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Contact</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<form action="validation" class="row g-3 needs-validation" novalidate>
		<div class="col-md-4">
			<label for="validationCustom01" class="form-label">User Id</label> <input
				type="number" class="form-control" name="userid" id="userid"
				required>
			<div class="valid-feedback">Looks good!</div>
		</div>
		<br>
		<div class="col-md-5">
			<label for="validationCustom02" class="form-label">User name</label>
			<input type="text" class="form-control" id="username" name="username"
				required>
			<div class="valid-feedback">Looks good!</div>
		</div>
		<br>
		<div class="col-md-6">
			<label for="validationCustom02" class="form-label">Password</label> <input
				type="password" class="form-control" id="password" name="password"
				required>
			<div class="valid-feedback">Looks good!</div>
		</div>
		<br>
		<div class="col-12">
			<button class="btn btn-primary" type="submit">Login</button>
		</div>
	</form>
	<br>
	<form action="signup">
		<div class="col-12">
			<button class="btn btn-primary" type="submit">SignUp</button>
		</div>
	</form>

	${Message}

</body>
</html>