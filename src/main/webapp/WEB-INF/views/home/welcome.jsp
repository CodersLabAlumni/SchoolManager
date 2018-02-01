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
	<br />
	<div class="container">
		<div class="row">
			<h2 class="text-center">
				Welcome to School Manager <b><sec:authentication
						property="principal.username" /> </b>.
			</h2>

			<h4 class="text-center">If you would like to create a new
				school, teacher or user profile, click below:</h4>
		</div>
		<br />
		<hr />
		<br />
		<div class="row">
			<div class="col-sm-4">
				<a type="button" class="btn btn-success btn-lg"
					href="${pageContext.request.contextPath}/school/create">Create
					new school</a>
			</div>
			<div class="col-sm-4">

				<a type="button" class="btn btn-info btn-lg"
					href="${pageContext.request.contextPath}/teacher/userNewTeacher">Create
					new teacher profile</a>
			</div>
			<div class="col-sm-4">

				<a type="button" class="btn btn-warning btn-lg"
					href="${pageContext.request.contextPath}/student/userNewStudent">Create
					new student profile</a>
			</div>
		</div>
		<br />
		<hr />
		<br />
		<div class="row">
			<h4 class="text-center">Or manage one of your existing
				activities:</h4>
		</div>
		<br />
		<hr />
		<br />
		<div class="row">
			<div class="col-sm-4">
				<p class="text-success">Schools you are managing:</p>
			</div>
			<div class="col-sm-4">
				<p class="text-info">Schools where you teach:</p>
			</div>
			<div class="col-sm-4">
				<p class="text-warning">Schools where you study:</p>
			</div>
		</div>


	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>