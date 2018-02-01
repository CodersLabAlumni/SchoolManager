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
		<div class="row">
			<h2>
				Welcome to
				<c:out value="${schoolAdmin.school.name}" />
				(
				<c:out value="${schoolAdmin.school.type}" />
				)
				<c:out value="${schoolAdmin.userRole.user.fullName}" />
				.
			</h2>
			<h4>You are and administrator in this school</h4>
		</div>

		<div class="row">
			<ul class="nav nav-tabs">
				<li class="nav-item"><a class="nav-link active show"
					data-toggle="tab" href="#home">Home</a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="tab"
					href="#divisions">Divisions</a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="tab"
					href="#teachers">Teachers</a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="tab"
					href="#students">Students</a></li>
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
							Name: <c:out value="${schoolAdmin.school.name}"/>
						</p>
						<p>Type: <c:out value="${schoolAdmin.school.type}"/></p>
						<p>Number of divisions: (placeholder)</p>
						<p>Number of students: (placeholder)</p>
					</div>
				</div>
				<div class="tab-pane fade" id="divisions">
					<p>DIVISIONS</p>
				</div>
				<div class="tab-pane fade" id="teachers">
					<p>TEACHERS</p>
				</div>
				<div class="tab-pane fade" id="students">
					<p>STUDENTS</p>
				</div>
			</div>

		</div>
	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>