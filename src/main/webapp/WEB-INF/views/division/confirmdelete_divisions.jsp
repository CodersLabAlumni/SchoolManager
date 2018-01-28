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
		<legend>Delete division</legend>
		<form:form method="post">
			<table class="table table-bordered">
				<thead>
					<tr class="table-light">
						<th scope="col">ID</th>
						<th scope="col">NAME</th>
						<th scope="col">DESCRIPTION</th>
					</tr>
				</thead>
				<tbody>
					<tr class="table-light">
						<td scope="row"><c:out value="${division.id}" /></td>
						<td><c:out value="${division.name}" /></td>
						<td><c:out value="${division.description}" /></td>
					</tr>
				</tbody>
			</table>
			<%@ include file="../jspf/confirm.jspf"%>
		</form:form>
	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>