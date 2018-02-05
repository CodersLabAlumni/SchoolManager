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
					href="#lessons">Lessons</a></li>
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
				<div class="tab-pane fade" id="lessons">
					<br />
					<p>Your lessons in this school:</p>
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">Name</th>
								<th scope="col">Teacher</th>
								<th scope="col">Day</th>
								<th scope="col">From</th>
								<th scope="col">Until</th>
								<th scope="col">Time</th>
								<th scope="col">Duration</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${student.division}" var="division">
								<tr class="table-warning">
									<td colspan="7"><strong>Group/class: <c:out value="${division.name}" />(<c:out
											value="${division.description}" />)</strong></td>
								</tr>
								<c:forEach items="${division.lesson}" var="lesson">
									<tr>
										<td><c:out value="${lesson.subject.fullName}" /></td>
										<td><c:out
												value="${lesson.teacher.userRole.user.fullName}" /></td>
										<td><c:out value="${lesson.dayOfWeek}" /></td>
										<td><c:out value="${lesson.startDate}" /></td>
										<td><c:out value="${lesson.stopDate}" /></td>
										<td><c:out value="${lesson.startHour}" /></td>
										<td><c:out value="${lesson.duration}" /></td>
									</tr>
								</c:forEach>
							</c:forEach>
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