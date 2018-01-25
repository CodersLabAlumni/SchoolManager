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
	<c:choose>
		<c:when test="${thisSchoolAdmin != null}">
			<%@ include file="../jspf/school_admin_menu.jspf"%>
		</c:when>
		<c:otherwise>
			<%@ include file="../jspf/main_menu.jspf"%>
		</c:otherwise>
	</c:choose>

	<div class="jumbotron">
		<legend>All Divisions</legend>

		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">ID</th>
					<th scope="col">NAME</th>
					<th scope="col">DESCRIPTION</th>
					<th scope="col">OPTION</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${availableDivisions}" var="division">
					<tr class="table-light">
						<td scope="row"><c:out value="${division.id}" /></td>
						<td><a
							href="${pageContext.request.contextPath}/division/inside/${division.id}">${division.name}</a></td>
						<td><c:out value="${division.description}" /></td>
						<td>
							<div class="btn-group">
								<div class="btn-group">
									<button type="button" class="btn btn-primary dropdown-toggle"
										data-toggle="dropdown"></button>
									<div class="dropdown-menu">
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/division/view/${division.id}">View
											details</a> <a class="dropdown-item"
											href="${pageContext.request.contextPath}/division/update/${division.id}">Update</a>
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/division/delete/${division.id}">Delete</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/division/addStudent/${division.id}">Add
											Student</a> <a class="dropdown-item"
											href="${pageContext.request.contextPath}/division/addSubject/${division.id}">Add
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

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>