<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../jspf/includes.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../jspf/headconfig.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage</title>
</head>
<body>
	<%@ include file="../jspf/main_menu.jspf"%>

	<div class="container">
		<h2>
			Sorry <b><sec:authentication property="principal.username" />,
				you do NOT have permission to access requested resource. If you
				think this is incorrect, please contact administrators.</b>
		</h2>
	</div>
	<%@ include file="../jspf/footer.jspf"%>

</body>
</html>