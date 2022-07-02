<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<!-- Required meta tags -->
<meta charset="utf-8">

</head>
<body>

	<!-- Create a form which will have text boxes for News ID, title, author,description, content
		 along with a Send button. Handle errors like empty fields -->
	
	<div class="container">
	<h1 class="text-center" style="color:green">Newz Application Assignment</h1>
		<form method="Post" action="saveData" modelAttribute="news">
			<div>
				<label>News Id</label> <input type="text" name="newsId"
					class="form-control is-valid" placeholder="News Id" required>
				<div class="valid-feedback">Please choose a newsid.</div>
			</div>
			<div>
				<label>Title</label> <input type="text" name="title"
					class="form-control is-valid" placeholder="Title" required>
				<div class="valid-feeback">The title is good.</div>
			</div>
			<div>
				<label>Author</label> <input type="text" name="author"
					class="form-control is-valid" placeholder="Title" required>
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
			<br> <br>
			<div class="valid-feedback">Content looks proper.</div>

			<div class="text-center">
				<button class="btn btn-primary" type="submit">Submit</button>
			</div>
		</form>
	</div>

	<!-- display all existing news in a tabular structure with News ID, title, author,description,content
		publishedAt and Action(delete button) -->

	<div class="container">
		<table color="primary" border="3">
			<tr>
				<td>News Id</td>
				<td>Title</td>
				<td>Author</td>
				<td>Description</td>
				<td>PublishedAt</td>
				<td>Content</td>
			</tr>

			<c:forEach items="${allnews}" var="newsobj">

				<tr>

					<td>${newsobj.getNewsId()}</td>

					<td>${newsobj.getTitle()}</td>
					<td>${newsobj.getAuthor()}</td>
					<td>${newsobj.getDescription()}</td>
					<td>${newsobj.getPublishedAt()}</td>
					<td>${newsobj.getContent()}</td>
					<td><a href="deleteNews?mynewsId=${newsobj.getNewsId()}">
							Delete</a>
				</tr>

			</c:forEach>

		</table>
	</div>

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