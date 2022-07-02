<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	</nav>
	<form action="" class="row g-3 needs-validation" novalidate>
		<div class="col-md-4">
			<label  class="form-label">Movie Id</label>
			 <input
				type="number" class="form-control" name="movieid" id="movieid"
				required>
			<div class="valid-feedback">Looks good!</div>
		</div>
		<br>
		<div class="col-md-5">
			<label  class="form-label">Movie name</label>
			<input type="text" class="form-control" id="moviename" name="moviename"
				required>
			<div class="valid-feedback">Looks good!</div>
		</div>
		<br>
		<div class="col-md-6">
			<label  class="form-label">Price</label> <input
				type="number" class="form-control" id="price" name="price"
				required>
			<div class="valid-feedback">Looks good!</div>
		</div>
		<br>
		<div class="col-12">
			<button class="btn btn-primary" type="submit">Add</button>
		</div>
	</form>

</body>
</html>