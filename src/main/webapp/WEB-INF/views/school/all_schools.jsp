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
		<legend>All Schools</legend>

		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">ID</th>
					<th scope="col">NAME</th>
					<th scope="col">TYPE</th>
					<th scope="col">OPTION</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${schools}" var="school">
					<tr class="table-light">
						<td scope="row"><c:out value="${school.id}" /></td>
						<td><c:out value="${school.name}" /></td>
						<td><c:out value="${school.type}" /></td>
						<td>
							<div class="btn-group">
								<div class="btn-group">
									<button type="button" class="btn btn-primary dropdown-toggle"
										data-toggle="dropdown"></button>
									<div class="dropdown-menu">
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/school/view/${school.id}">View
											details</a> <a class="dropdown-item"
											href="${pageContext.request.contextPath}/school/update/${school.id}">Update</a>
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/school/delete/${school.id}">Delete</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/school/addDivision/${school.id}">Add
											Division</a>
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/school/addSubject/${school.id}">Add
											Subject</a>
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/school/addStudent/${school.id}">Add
											Student</a>
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/school/addTeacher/${school.id}">Add
											Teacher</a>
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