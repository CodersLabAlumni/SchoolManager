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
		<legend>All received message</legend>

		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">SENDER</th>
					<th scope="col">TITLE</th>
					<th scope="col">DATE</th>
					<th scope="col">DELETE</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${receivedMessages}" var="received">
					<tr class="table-light">
						<td scope="row"><c:out value="${received.sender.username}" /></td>
						<td><c:out value="${received.title}" /></td>
						<td><c:out value="${received.created}" /></td>
						<td><c:choose>
								<c:when test="${del eq received.id}">
									<form:form method="post" modelAttribute="message">
										<%@ include file="../jspf/delete.jspf"%>
									</form:form>
								</c:when>
								<c:otherwise>
									<a class="btn btn-primary"
										href="${pageContext.request.contextPath}/message/delete/received/${received.id}">Delete</a></td>
						</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>