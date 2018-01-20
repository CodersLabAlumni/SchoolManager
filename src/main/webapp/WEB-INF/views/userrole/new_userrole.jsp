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
		<h3 class="text-center">The following user...</h3>
		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">ID</th>
					<th scope="col">USERNAME</th>
					<th scope="col">EMAIL</th>
				</tr>
			</thead>
			<tbody>
				<tr class="table-light">
					<td scope="row"><c:out value="${user.id}" /></td>
					<td><c:out value="${user.username}" /></td>
					<td><c:out value="${user.email}" /></td>
				</tr>
			</tbody>
		</table>

		<h3 class="text-center">...have the following user roles
			assigned:</h3>


		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Role</th>
					<th>School</th>
				</tr>
			</thead>
			<c:forEach items="${user.userRoles}" var="userRole">
				<tr>
					<td><c:out value="${userRole.userRole}"></c:out></td>
					<td>Placeholder for school info</td>
				</tr>
			</c:forEach>
		</table>

		<div class="form-group">
			<form method="post">
				<select id="selectedRole" name="selectedRole">
				<c:forEach items="${userRolesForSelect}" var="userRoleText">
					<option value="${userRoleText}"><c:out value="${userRoleText}"/></option>
				</c:forEach>
				</select> <br /> 
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
				<input type="submit" class="btn btn-primary">

			</form>
		</div>
	</div>
	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>