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
				<c:out value="${teacher.school.name}" />
				(
				<c:out value="${teacher.school.type}" />
				)
				<c:out value="${teacher.userRole.user.fullName}" />
				.
			</h2>
			<h4>You are a teacher in this school</h4>
		</div>

		<div class="row">
			<ul class="nav nav-tabs">
				<li class="nav-item"><a class="nav-link active show"
					data-toggle="tab" href="#home">Home</a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="tab"
					href="#lessons">Lessons</a></li>
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
							<c:out value="${teacher.school.name}" />
						</p>
						<p>
							Type:
							<c:out value="${teacher.school.type}" />
						</p>
						<p>Number of divisions: (placeholder)</p>
						<p>Number of students: (placeholder)</p>
					</div>
				</div>
				<div class="tab-pane fade" id="lessons">
					<br />
					<p>Lessons you teach in this school:</p>
					<div class="timetable"></div>
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">Name</th>
								<th scope="col">Group/class</th>
								<th scope="col">Day</th>
								<th scope="col">From</th>
								<th scope="col">Until</th>
								<th scope="col">Time</th>
								<th scope="col">Duration</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${lessons}" var="lesson">
								<tr>
									<td><c:out value="${lesson.subject.fullName}" /></td>
									<td><c:out value="${lesson.division.name}" /></td>
									<td><c:out value="${lesson.dayOfWeek}" /></td>
									<td><c:out value="${lesson.startDate}" /></td>
									<td><c:out value="${lesson.stopDate}" /></td>
									<td><c:out value="${lesson.startHour}" /></td>
									<td><c:out value="${lesson.duration}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>

	<%@ include file="../jspf/footer.jspf"%>
	<script
		src="${pageContext.request.contextPath}/resources/js/timetable.js"></script>
	<script>
		var timetable = new Timetable();
		timetable.setScope(7, 20); // optional, only whole hours between 0 and 23

		timetable.addLocations(${activeDays});

		<c:forEach items="${schedule}" var="lesson">
			timetable.addEvent(${lesson});
		</c:forEach>
		var renderer = new Timetable.Renderer(timetable);
		renderer.draw('.timetable'); // any css selector
	</script>
</body>
</html>