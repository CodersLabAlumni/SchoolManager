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
					<th scope="col">TO</th>
					<th scope="col">TITLE</th>
					<th scope="col">DATE</th>
					<th scope="col">REMOVE</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sendedMessages}" var="sended">
						<td scope="row"><c:out value="${sended.receiverDescription}" /></td>
						<td><c:out value="${sended.title}" /></td>
						<td><c:out value="${sended.created}" /></td>
						<td><c:choose>
								<c:when test="${remove eq sended.id}">
									<form:form method="post" modelAttribute="message">
										<%@ include file="../jspf/confirm.jspf"%>
									</form:form>
								</c:when>
								<c:otherwise>
									<div class="btn-group">
										<div class="btn-group">
											<button type="button" class="btn btn-primary dropdown-toggle"
												data-toggle="dropdown"></button>
											<div class="dropdown-menu">
												<a class="dropdown-item"
													href="${pageContext.request.contextPath}/message/view/${sended.id}">DETAILS</a>
												<a class="dropdown-item"
													href="${pageContext.request.contextPath}/message/remove/sended/${sended.id}">REMOVE</a>
											</div>
										</div>
									</div>

								</c:otherwise>
							</c:choose></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>