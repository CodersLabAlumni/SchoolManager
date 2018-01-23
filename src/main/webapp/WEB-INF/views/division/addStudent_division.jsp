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
		<legend>Division: ${division.name} </legend>
		</br>
		<legend>All students in division </legend>
		<%@ include file="../jspf/division_students.jspf"%>

		<legend>All students to add </legend>


		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">ID</th>
					<th scope="col">FIRST NAME</th>
					<th scope="col">LAST NAME</th>
					<th scope="col">OPTION</th>
					<th scope="col">ADD</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${studentsNotInDivision}" var="student">
					<tr class="table-light">
						<td><c:out value="${student.id}" /></td>
						<td><c:out value="${student.userRole.user.firstName}" /></td>
						<td><c:out value="${student.userRole.user.lastName}" /></td>
						<td>
							<div class="btn-group">
								<div class="btn-group">
									<button type="button" class="btn btn-primary dropdown-toggle"
										data-toggle="dropdown"></button>
									<div class="dropdown-menu">
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/student/view/${student.id}">View
											details</a>
									</div>
								</div>
							</div>
						</td>
						<td><a class="btn btn-primary"
							href="${pageContext.request.contextPath}/division/addStudent/${division.id}/${student.id}">Add</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>





		<a class="btn btn-secondary"
			href="${pageContext.request.contextPath}/division/all">Return</a>

	</div>

	<%@ include file="../jspf/footer.jspf"%>

</body>
</html>