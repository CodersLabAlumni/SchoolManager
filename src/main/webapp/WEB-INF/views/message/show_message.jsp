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

		<p class="lead">
			From: ${message.senderDescription} </br> to:
			${message.receiverDescription} </br> created: ${message.created}
		</p>
		<p class="lead">Title: ${message.title}</p>
		<hr class="my-4">
		<p class="lead">${message.content}</p>
		<hr class="my-4">
		<input action="action" onclick="window.history.go(-1); return false;"
			type="button" class="btn btn-secondary" value="Return" /> <a
			class="btn btn-primary"
			href="${pageContext.request.contextPath}/message/create">Send
			response</a>
	</div>

	<%@ include file="../jspf/footer.jspf"%>



</body>
</html>