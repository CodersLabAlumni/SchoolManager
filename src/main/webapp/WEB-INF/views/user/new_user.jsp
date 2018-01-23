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

		<form:form method="post" modelAttribute="user" class="form-horizontal">
			<fieldset>
				<legend>Add new User or Edit existing one</legend>

				<div class="form-group">
					<label for="inputUsername" class="col-lg-2 control-label">Username</label>
					<div class="col-lg-10">
						<form:input class="form-control" id="inputUsername"
							placeholder="Enter username" type="text" path="username" />
						<form:errors path="username" class="text-danger" />
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail" class="col-lg-2 control-label">E-mail
					</label>
					<div class="col-lg-10">
						<form:input class="form-control" id="inputEmail"
							aria-describedby="emailHelp" placeholder="Enter email"
							type="email" path="email" />
						<form:errors path="email" class="text-danger" />
					</div>


					<div class="form-group">
						<label for="firstName" class="col-lg-2 control-label">First
							name: </label>
						<div class="col-lg-10">
							<form:input class="form-control" id="firstName"
								aria-describedby="firstName" placeholder="Enter first name"
								type="text" path="firstName" />
							<form:errors path="firstName" class="text-danger" />

						</div>
					</div>

					<div class="form-group">
						<label for="lastName" class="col-lg-2 control-label">Last
							name: </label>
						<div class="col-lg-10">
							<form:input class="form-control" id="firstName"
								aria-describedby="lastName" placeholder="Enter last name"
								type="text" path="lastName" />
							<form:errors path="lastName" class="text-danger" />

						</div>
					</div>


					<div class="form-group">
						<label for="password" class="col-lg-2 control-label">Password:</label>
						<div class="col-lg-10">
							<form:input class="form-control" id="password"
								aria-describedby="password" placeholder="Enter password"
								type="text" path="password" />
							<form:errors path="password" class="text-danger" />
						</div>
					</div>

					<div class="form-group">
						<label for="confirmPassword" class="col-lg-2 control-label">Confirm
							password:</label>
						<div class="col-lg-10">
							<form:input class="form-control" id="confirmPassword"
								aria-describedby="confirmPassword" placeholder="Enter password"
								type="text" path="confirmPassword" />
							<form:errors path="confirmPassword" class="text-danger" />
						</div>
					</div>

					<div class="form-group">

						<div>
							Account type: Enabled:
							<form:radiobutton path="enabled" value="true" checked="checked"/>
							Blocked:
							<form:radiobutton path="enabled" value="false" />
						</div>
					</div>

					<br />
			</fieldset>
			<input action="action" onclick="window.history.go(-1); return false;"
				type="button" class="btn btn-secondary" value="Cancel" />
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>