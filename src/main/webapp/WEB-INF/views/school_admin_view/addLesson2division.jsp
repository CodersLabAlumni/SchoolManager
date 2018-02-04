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

	<div class="container">
		<legend>Division: ${division.name} </legend>
		</br>
		<legend>All lessons in this division </legend>

		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">Name</th>
					<th scope="col">Teacher</th>
					<th scope="col">Day</th>
					<th scope="col">From</th>
					<th scope="col">Until</th>
					<th scope="col">Time</th>
					<th scope="col">Duration</th>
					<th scope="col">Remove</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${lessons}" var="lesson">
					<tr>
						<td><c:out value="${lesson.subject.fullName}" /></td>
						<td><c:out value="${lesson.teacher.userRole.user.fullName}" /></td>
						<td><c:out value="${lesson.dayOfWeek}" /></td>
						<td><c:out value="${lesson.startDate}" /></td>
						<td><c:out value="${lesson.stopDate}" /></td>
						<td><c:out value="${lesson.startHour}" /></td>
						<td><c:out value="${lesson.duration}" /></td>
						<td><a class="btn btn-primary"
							href="${pageContext.request.contextPath}/schoolAdminView/${schoolAdmin.id}/removeLesson/${division.id}/${lesson.id}">Remove</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		Add new lesson:

		<form:form method='post' modelAttribute="lesson"
			class="form-horizontal">
			<div class="form-group">
				Subject:
				<form:select type="text" path="subject" cssClass="form-control">
					<form:options items="${subjects}" itemValue="id"
						itemLabel="fullName" />
				</form:select>
				<form:errors path="subject" />
			</div>
			<div class="form-group">
				Teachers:
				<form:select type="text" path="teacher" cssClass="form-control">
					<form:options items="${teachers}" itemValue="id"
						itemLabel="fullName" />
				</form:select>
				<form:errors path="teacher" />
			</div>
			<div class="form-group">
				Day:
				<form:select type="text" path="dayOfWeek" cssClass="form-control">
					<form:option value="Mon">Monday</form:option>
					<form:option value="Tue">Tuesday</form:option>
					<form:option value="Wed">Wednesday</form:option>
					<form:option value="Thu">Thursday</form:option>
					<form:option value="Fri">Friday</form:option>
					<form:option value="Sat">Saturday</form:option>
					<form:option value="Sun">Sunday</form:option>
				</form:select>
				<form:errors path="dayOfWeek" />
			</div>

			<div class="form-group">
				Start date (yyyy-MM-dd):
				<form:input path="startDate" cssClass="form-control" />
				<form:errors path="startDate" />
			</div>

			<div class="form-group">
				Stop date (yyyy-MM-dd):
				<form:input path="stopDate" cssClass="form-control" />
				<form:errors path="stopDate" />
			</div>

			<div class="form-group">
				Starting at (hh:mm):
				<form:input path="startHour" cssClass="form-control" />
				<form:errors path="startHour" />
			</div>

			<div class="form-group">
				Duration:
				<form:input path="duration" cssClass="form-control" />
				<form:errors path="duration" />
			</div>
			<input action="action" onclick="window.history.go(-1); return false;"
				type="button" class="btn btn-secondary" value="Cancel" />
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>


		<a class="btn btn-secondary"
			href="${pageContext.request.contextPath}/schoolAdminView/access/${schoolAdmin.id}">Return</a>

	</div>

	<%@ include file="../jspf/footer.jspf"%>

</body>
</html>