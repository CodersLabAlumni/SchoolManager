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
		<legend>All Users</legend>

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

		<div>
			<form:form method="post">
				<h4>Please provide new password for this user:</h4>
				Password:
				<input type="password" name="password1" />
				Confirm password:
				<input type="password" name="password2" />
				<h5><c:out value="${msg}"></c:out></h5>
				<input type="submit" value="Change password" class="btn btn-primary"/>
			</form:form>
		</div>
	</div>
	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>