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

		<form:form method="post" modelAttribute="message"
			class="form-horizontal">
			<fieldset>
				<legend>New message</legend>

				<div class="form-group">
					<label for="inputEmail" class="col-lg-2 control-label">Receiver e-mail </label>
					<span class="text-danger">${errorMessage}</span>
					<div class="col-lg-10">
						<form:input class="form-control" id="inputEmail"
							aria-describedby="emailHelp" placeholder="Enter email"
							type="email" path="receiverEmail"/>
						<form:errors path="receiverEmail" class="text-danger" />
					</div>
				</div>

				<div class="form-group">
					<label for="inputTitle" class="col-lg-2 control-label">Title</label>
					<div class="col-lg-10">
						<form:input class="form-control" id="inputTitle"
							placeholder="Message title" type="text" path="title" />
						<form:errors path="title" class="text-danger" />
					</div>
				</div>

				<div class="form-group">
					<label for="textArea" class="col-lg-2 control-label">Message
						content</label>
					<div class="col-lg-10">
						<form:textarea class="form-control" rows="3" id="textArea"
							path="content" /></textarea>
						<form:errors path="content" />
					</div>
				</div>
			</fieldset>
			<input action="action" onclick="window.history.go(-1); return false;"
				type="button" class="btn btn-secondary" value="Return" />
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>