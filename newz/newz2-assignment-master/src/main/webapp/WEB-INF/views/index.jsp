<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Newz Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<!-- Required meta tags -->
<meta charset="utf-8">

</head>
<body>

	<!-- Create a form which will have text boxes for News Name, News Author, description, content along with a Add 
		 button. Handle errors like empty fields.
		 
	<!-- display all existing news in a tabular structure with News Name, News Author, 
	description, content, Publish Date and Action -->
	<h1>Newz Application Assignment</h1>
	<form method="Post" action="add" modelAttribute="news">

		<div>
			<label>Name</label> <input type="text" name="name"
				class="form-control is-valid" placeholder="Title" required>
			<div class="valid-feeback">The title is good.</div>
		</div>
		<div>
			<label>Author</label> <input type="text" name="author"
				class="form-control is-valid" placeholder="Author" required>
			<div class="valid-feedback">Chosen an author name.</div>
		</div>
		<div>
			<label>Description</label> <input type="text" name="description"
				class="form-control is-valid" placeholder="Description" required>
		</div>
		<div class="valid-feedback">Valid description</div>
		<div>
			<label>Content</label> <input type="text" name="content"
				class="form-control is-valid" placeholder="Content" required>
		</div>
		<div class="valid-feedback">Content looks proper.</div>
		<button class="btn btn-primary" type="submit">Add</button>
	</form>

	<!-- display all existing news in a tabular structure with News ID, title, author,description,content
		publishedAt and Action(delete button) -->
	<table color="primary" border="3">
		<tr>

			<td>Name</td>
			<td>Author</td>
			<td>Description</td>
			<td>PublishedAt</td>
			<td>Content</td>
		</tr>

		<c:forEach items="${List}" var="newsObj">

			<tr>


				<td>${newsObj.name}</td>
				<td>${newsObj.author}</td> 
				<td>${newsObj.description}</td>
				<td>${newsObj.publishedAt}</td>
				<td>${newsObj.content}</td>
				<td><a href="update" method="put" action="update"
					modelAttribute="mynews"> Update</a> <a method="delete"
					action="delete" href="delete?mynewsId=${newsObj.newsId}">
						Delete</a>
			</tr>

		</c:forEach>

	</table>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>

</body>
</html>


