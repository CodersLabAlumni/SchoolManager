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
	<div class="jumbotron">
		<legend>Delete student</legend>
		<form:form method="post">
			<table class="table table-bordered">
				<thead>
					<tr class="table-light">
						<th scope="col">ID</th>
						<th scope="col">FULL NAME</th>
						<th scope="col">EMAIL</th>
					</tr>
				</thead>
				<tbody>
					<tr class="table-light">
						<td scope="row"><c:out value="${student.id}" /></td>
						<td><c:out value="${student.userRole.user.fullName}" /></td>
						<td><c:out value="${student.userRole.user.email}" /></td>
					</tr>
				</tbody>
			</table>
			<%@ include file="../jspf/confirm.jspf"%>
		</form:form>
	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>