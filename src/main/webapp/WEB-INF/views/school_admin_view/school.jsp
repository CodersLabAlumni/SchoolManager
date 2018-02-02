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
				<c:out value="${schoolAdmin.school.name}" />
				(
				<c:out value="${schoolAdmin.school.type}" />
				)
				<c:out value="${schoolAdmin.userRole.user.fullName}" />
				.
			</h2>
			<h4>You are an administrator in this school</h4>
		</div>

		<div class="row">
			<ul class="nav nav-tabs">
				<li class="nav-item"><a class="nav-link active show"
					data-toggle="tab" href="#home">Home</a></li>
				<li class="nav-item"><a class="nav-link" data-toggle="tab"
					href="#divisions">Groups/Classes</a></li>
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
							Name:
							<c:out value="${schoolAdmin.school.name}" />
						</p>
						<p>
							Type:
							<c:out value="${schoolAdmin.school.type}" />
						</p>
						<p>Number of divisions: (placeholder)</p>
						<p>Number of students: (placeholder)</p>
					</div>
				</div>
				<div class="tab-pane fade" id="divisions">
					<br /> <a type="button" class="btn btn-danger"
						href="${pageContext.request.contextPath}/schoolAdminView/${schoolAdmin.id}/createDivision">
						Create new division </a> <br /><br/>
					<p>Groups/classes in this school:</p>
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">Name</th>
								<th scope="col">Description</th>
								<th scope="col">Num of students</th>
								<th scope="col">Num of subjects</th>
								<th scope="col">Manage</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${schoolAdmin.school.division}" var="division">
								<tr>
									<td><c:out value="${division.name}" /></td>
									<td><c:out value="${division.description}" /></td>
									<td><c:out value="${division.numStudents}" /></td>
									<td><c:out value="${division.numSubjects}" /></td>
									<td>
										<div class="btn-group">
											<div class="btn-group">
												<button type="button"
													class="btn btn-primary dropdown-toggle"
													data-toggle="dropdown">Menu</button>
												<div class="dropdown-menu">
													<a class="dropdown-item"
														href="${pageContext.request.contextPath}/division/view/${division.id}">View
														details</a> <a class="dropdown-item"
														href="${pageContext.request.contextPath}/schoolAdminView/${schoolAdmin.id}/updateDivision/${division.id}">Update</a>
													<div class="dropdown-divider"></div>
													<a class="dropdown-item"
														href="${pageContext.request.contextPath}/division/addStudent/${division.id}">Add/Remove
														Student</a> <a class="dropdown-item"
														href="${pageContext.request.contextPath}/division/addSubject/${division.id}">Add/Remove
														Subject</a>
												</div>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="tab-pane fade" id="teachers">
					<br />
					<p>Teachers in this school:</p>
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">Name</th>
								<th scope="col">Manage</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${schoolAdmin.school.teacher}" var="teacher">
								<tr>
									<td><c:out value="${teacher.userRole.user.fullName}" /></td>
									<td>Menu</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="tab-pane fade" id="students">
					<br />
					<p>Students in this school:</p>
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">Name</th>
								<th scope="col">Manage</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${schoolAdmin.school.student}" var="student">
								<tr>
									<td><c:out value="${student.userRole.user.fullName}" /></td>
									<td>Menu</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>