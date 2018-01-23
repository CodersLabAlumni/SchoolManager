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

	<div class="jumbotron">
		<legend>All Students</legend>

		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">ID</th>
					<th scope="col">FULL NAME</th>
					<th scope="col">EMAIL</th>
					<th scope="col">SCHOOL</th>
					<th scope="col">OPTION</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${availableStudents}" var="student">
					<tr class="table-light">
						<td scope="row"><c:out value="${student.id}" /></td>
						<td><c:out value="${student.userRole.user.fullName}" /></td>
						<td><c:out value="${student.userRole.user.email}" /></td>
						<td><c:out value="${student.userRole.school.nameForForm}" /></td>
						<td>
							<div class="btn-group">
								<div class="btn-group">
									<button type="button" class="btn btn-primary dropdown-toggle"
										data-toggle="dropdown">Menu</button>
									<div class="dropdown-menu">
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/student/view/${student.id}">View
											details</a> <a class="dropdown-item"
											href="${pageContext.request.contextPath}/student/update/${student.id}">Update</a>
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/student/delete/${student.id}">Delete</a>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>