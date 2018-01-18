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
		
		<legend>All Teachers</legend>

		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">ID</th>
					<th scope="col">EMAIL</th>
					<th scope="col">FIRST NAME</th>
					<th scope="col">LAST NAME</th>
					<th scope="col">OPTION</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${availableTeachers}" var="teacher">
					<tr class="table-light">
						<td scope="row"><c:out value="${teacher.id}" /></td>
						<td><c:out value="${teacher.email}" /></td>
						<td><c:out value="${teacher.firstName}" /></td>
						<td><c:out value="${teacher.lastName}" /></td>
						<td>
							<div class="btn-group">
								<div class="btn-group">
									<button type="button" class="btn btn-primary dropdown-toggle"
										data-toggle="dropdown"></button>
									<div class="dropdown-menu">
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/teacher/view/${teacher.id}">View
											details</a> <a class="dropdown-item"
											href="${pageContext.request.contextPath}/teacher/update/${teacher.id}">Update</a>
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/teacher/delete/${teacher.id}">Delete</a>
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