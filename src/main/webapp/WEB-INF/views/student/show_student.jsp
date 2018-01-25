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
		<legend>Student details: ${student.userRole.user.firstName}
			${student.userRole.user.lastName} </legend>

		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">Description</th>
					<th scope="col">Value</th>
				</tr>
			</thead>
			<tbody>
				<tr class="table-light">
					<td>ID</td>
					<td scope="row"><c:out value="${student.id}" /></td>
				</tr>
				<tr class="table-light">
					<td>E-mail</td>
					<td scope="row"><c:out value="${student.userRole.user.email}" /></td>
				</tr>
				<tr class="table-light">
					<td>First name</td>
					<td scope="row"><c:out
							value="${student.userRole.user.firstName}" /></td>
				</tr>
				<tr class="table-light">
					<td>Last name</td>
					<td scope="row"><c:out
							value="${student.userRole.user.lastName}" /></td>
				</tr>
			</tbody>
		</table>
		<input action="action" onclick="window.history.go(-1); return false;"
			type="button" class="btn btn-secondary" value="Return" />
	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>