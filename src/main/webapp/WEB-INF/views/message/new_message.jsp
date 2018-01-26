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
	<c:choose>
		<c:when test="${thisSchoolAdmin != null}">
			<%@ include file="../jspf/school_admin_menu.jspf"%>
		</c:when>
		<c:when test="${thisTeacher != null}">
			<%@ include file="../jspf/teacher_menu.jspf"%>
		</c:when>
		<c:when test="${thisStudent != null}">
			<%@ include file="../jspf/student_menu.jspf"%>
		</c:when>
		<c:otherwise>
			<%@ include file="../jspf/main_menu.jspf"%>
		</c:otherwise>
	</c:choose>

	<div class="jumbotron">

		<form:form method="post" modelAttribute="message"
			class="form-horizontal">
			<fieldset>
				<legend>New message</legend>

				<div class="form-group">
					<label for="inputEmail" class="col-lg-2 control-label">Receiver
						e-mail </label> <span class="text-danger">${errorMessage}</span>
					<div class="col-lg-10">
						<form:input class="form-control" id="inputEmail"
							aria-describedby="emailHelp" placeholder="Enter email"
							type="email" path="receiver.email"/>
					</div>
				</div>

				<div class="form-group">
					<label for="inputTitle" class="col-lg-2 control-label">Title</label>
					<div class="col-lg-10">
						<form:input class="form-control" id="inputTitle"
							placeholder="Message title" type="text" path="title" />
					</div>
				</div>

				<div class="form-group">
					<label for="textArea" class="col-lg-2 control-label">Message
						content</label>
					<div class="col-lg-10">
						<form:textarea class="form-control" rows="3" id="textArea"
							path="content" />
<<<<<<< 320883889910646c0cf6906a22141aaebfe1f697
						</textarea>
						<form:errors path="content" />
=======
>>>>>>> tag fix, remove unused error fields in new_message view
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