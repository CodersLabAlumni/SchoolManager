<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../jspf/includes.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../jspf/headconfig.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../jspf/main_menu.jspf"%>
	<div class="container">
		<br />
		<div class="row">
			<h2>
				Welcome to
				<c:out value="${student.school.name}" />
				(
				<c:out value="${student.school.type}" />
				)
				<c:out value="${student.userRole.user.fullName}" />
				.
			</h2>
			<h4>You are a student in this school</h4>
		</div>

		<div class="row">
			<ul class="nav nav-tabs">
				<li class="nav-item"><a class="nav-link active show"
					data-toggle="tab" href="#home">Home</a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="tab"
					href="#divisions">Divisions</a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="tab"
					href="#subjects">Subjects</a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="tab"
					href="#students">---Students</a></li>
			</ul>
		</div>
		<div class="row">
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade active show" id="home">
					<div>
						<br />
						<hr />
						<br />
						<p>Basic information about this school:</p>
						<p>
							Name:
							<c:out value="${student.school.name}" />
						</p>
						<p>
							Type:
							<c:out value="${student.school.type}" />
						</p>
						<p>Number of divisions: (placeholder)</p>
						<p>Number of students: (placeholder)</p>
					</div>
				</div>
				<div class="tab-pane fade" id="divisions">
					<br />

					<p>Groups/classes in this school:</p>
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">Name</th>
								<th scope="col">Description</th>
								<th scope="col">Num of students</th>
								<th scope="col">Num of subjects</th>
								<th scope="col">Num of lessons</th>
								<th scope="col">Manage</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${student.division}" var="division">
								<tr>
									<td><c:out value="${division.name}" /></td>
									<td><c:out value="${division.description}" /></td>
									<td><c:out value="${division.numStudents}" /></td>
									<td><c:out value="${division.numSubjects}" /></td>
									<td><c:out value="${division.numLessons}" /></td>
									<td></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="tab-pane fade" id="subjects">
					<br />
					<p>Subjects you study in this school:</p>
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">Name</th>
								<th scope="col">Description</th>
								<th scope="col">Manage</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Subject name</td>
								<td>Subject division</td>
								<td>Menu</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="tab-pane fade" id="students">
					<br />
				</div>
			</div>

		</div>
	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>