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

		<form:form method="post" modelAttribute="division"
			class="form-horizontal">
			<fieldset>
				<legend>Add new division</legend>
				<div class="form-group">
					<label for="inputDivisionName" class="col-lg-2 control-label">Name</label>
					<div class="col-lg-10">
						<form:input class="form-control" id="inputDivisionName"
							placeholder="Division name" type="text" path="name" />
						<form:errors path="name" class="text-danger" />
					</div>
				</div>

				<div class="form-group">
					<label for="textArea" class="col-lg-2 control-label">Division
						description</label>
					<div class="col-lg-10">
						<form:textarea class="form-control" rows="3" id="textArea"
							path="description" />
						</textarea>
						<form:errors path="description" />
					</div>
				</div>
			</fieldset>
			<input action="action" onclick="window.history.go(-1); return false;"
				type="button" class="btn btn-secondary" value="Cancel" />
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>

	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>