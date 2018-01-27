<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../jspf/includes.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../jspf/headconfig.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exception</title>
</head>
<body>
	<%@ include file="../jspf/main_menu.jspf"%>

	<div class="container">
		<h2>
			DELETE EXCEPTION </br>
			Sorry <b><sec:authentication property="principal.username" /></b>,</br>
			Before deleting You need to remove all connection with division/student/teacher etc.
		</h2>
		<input action="action" onclick="window.history.go(-1); return false;" type="button" class="btn btn-secondary" value="Return" />
		
	</div>
	<%@ include file="../jspf/footer.jspf"%>

</body>
</html>