<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../jspf/includes.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../jspf/headconfig.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage</title>
</head>
<body>
	<%@ include file="../jspf/main_menu.jspf"%>
	<div class="container">
		<c:if test="${empty msg}">
			<h3 class="text-center">Are you sure you want to delete the
				following user:</h3>
			<table class="table table-bordered">
				<thead>
					<tr class="table-light">
						<th scope="col">ID</th>
						<th scope="col">USERNAME</th>
						<th scope="col">EMAIL</th>
						<th scope="col">ROLES</th>
					</tr>
				</thead>
				<tbody>
					<tr class="table-light">
						<td scope="row"><c:out value="${user.id}" /></td>
						<td><c:out value="${user.username}" /></td>
						<td><c:out value="${user.email}" /></td>
						<td>
							<table>
								<c:forEach items="${user.userRoles}" var="userRole">
									<tr>
										<td><c:out value="${userRole.userRole}"></c:out></td>
										<td>Placeholder for school info</td>
									</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="text-center">
				<form:form method="post" modelAttribute="user">
					<input type="submit" value="Yes"
						class="btn btn-primary btn-success btn-lg" />
					<input type="button" value="No" onclick="history.back()"
						class="btn btn-primary btn-danger btn-lg">
				</form:form>
		</c:if>
		<h2>
			<c:out value="${msg}"></c:out>
		</h2>
	</div>
	</div>
	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>