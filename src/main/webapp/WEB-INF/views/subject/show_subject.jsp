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
		<legend>Subject details: ${subject.name} </legend>

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
					<td scope="row"><c:out value="${subject.id}" /></td>
				</tr>
				<tr class="table-light">
					<td>Name</td>
					<td scope="row"><c:out value="${subject.name}" /></td>
				</tr>
				<tr class="table-light">
					<td>Description</td>
					<td scope="row"><c:out value="${subject.description}" /></td>
				</tr>
			</tbody>
		</table>
		<input action="action" onclick="window.history.go(-1); return false;"
			type="button" class="btn btn-secondary" value="Return" />
	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>