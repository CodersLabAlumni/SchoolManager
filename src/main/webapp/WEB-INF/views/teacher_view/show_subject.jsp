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
	<%@ include file="../jspf/teacher_menu.jspf"%>

	<div class="jumbotron">
		<legend>Subject details: ${subject.name} </legend>

		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">Description</th>
					<th scope="col">Value</th>
				</tr>
			</thead>
			<tbody>
				<tr class="table-light">
					<td>ID</td>
					<td scope="row"><c:out value="${subject.id}" /></td>
				</tr>
				<tr class="table-light">
					<td>Name</td>
					<td scope="row"><c:out value="${subject.name}" /></td>
				</tr>
				<tr class="table-light">
					<td>Description</td>
					<td scope="row"><c:out value="${subject.description}" /></td>
				</tr>
			</tbody>
		</table>
		<input action="action" onclick="window.history.go(-1); return false;"
			type="button" class="btn btn-secondary" value="Return" /> <br />
		<legend>Divisions attending the subject </legend>

		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">Name</th>
					<th scope="col">Description</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${subject.division}" var="division">
					<tr class="table-light">
						<td><a
							href="${pageContext.request.contextPath}/teacherView/showDivision/${division.id}">${division.name}</a></td>
						<td>${division.description}</td>
					</tr>
					<table class="table table-bordered">
						<thead>
							<tr class="table-light">
								<th scope="col">MONDAY</th>
								<th scope="col">TUESDAY</th>
								<th scope="col">WEDNESDAY</th>
								<th scope="col">THURSDAY</th>
								<th scope="col">FRIDAY</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach begin="1" end="8" varStatus="time">
								<tr class="table-light">
									<td><c:out value="${mondaySubjects[time.index-1]}" /><br><a href="${pageContext.request.contextPath}/teacherView/addSubject/${division.id}/1/${time.index}">add subject</a><br><a href="${pageContext.request.contextPath}/teacherView/removeSubject/${division.id}/1/${time.index}">remove current subject</a></td>
									<td><c:out value="${tuesdaySubjects[time.index-1]}" /><br><a href="${pageContext.request.contextPath}/teacherView/addSubject/${division.id}/2/${time.index}">add subject</a><br><a href="${pageContext.request.contextPath}/teacherView/removeSubject/${division.id}/2/${time.index}">remove current subject</a></td>
									<td><c:out value="${wednesdaySubjects[time.index-1]}" /><br><a href="${pageContext.request.contextPath}/teacherView/addSubject/${division.id}/3/${time.index}">add subject</a><br><a href="${pageContext.request.contextPath}/teacherView/removeSubject/${division.id}/3/${time.index}">remove current subject</a></td>
									<td><c:out value="${thursdaySubjects[time.index-1]}" /><br><a href="${pageContext.request.contextPath}/teacherView/addSubject/${division.id}/4/${time.index}">add subject</a><br><a href="${pageContext.request.contextPath}/teacherView/removeSubject/${division.id}/4/${time.index}">remove current subject</a></td>
									<td><c:out value="${fridaySubjects[time.index-1]}" /><br><a href="${pageContext.request.contextPath}/teacherView/addSubject/${division.id}/5/${time.index}">add subject</a><br><a href="${pageContext.request.contextPath}/teacherView/removeSubject/${division.id}/5/${time.index}">remove current subject</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:forEach>

			</tbody>
		</table>
	</div>
	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>