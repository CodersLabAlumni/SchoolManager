<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../jspf/includes.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage</title>
</head>
<body>
	<div class="container">
	<h1>Sorry <b><sec:authentication property="principal.username" />, you do NOT have 
	permission to access requested resource. If you think this is incorrect, please contact administrators.</b></h1>
	</div>
</body>
</html>