<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="jspf/includes.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="jspf/headconfig.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CONTROL PAGE</title>
</head>
<body>
<%@ include file="jspf/teacher_menu.jspf"%>

	Student id:
	<H1> ${thisTeacher.id} </H1>
	UserRole id:
	<H1> ${thisSchool.name} </H1>
	
		Second teacher id:
	<H1> ${teacher1.id} </H1>
	UserRole id:
	<H1> ${thisSchool.name} </H1>

	
	
<%@ include file="jspf/footer.jspf"%>	
</body>
</html>