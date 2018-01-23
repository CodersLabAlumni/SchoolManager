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
		<legend>All sended message</legend>

		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">RECEIVER</th>
					<th scope="col">TITLE</th>
					<th scope="col">DATE</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sendedMessages}" var="sended">
					<tr class="table-light">
						<td scope="row"><c:out value="${sended.receiver.username}" /></td>
						<td><c:out value="${sended.title}" /></td>
						<td><c:out value="${sended.created}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>