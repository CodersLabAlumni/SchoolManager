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
	<%@ include file="../jspf/student_menu.jspf"%>
	<div class="jumbotron">

		<legend align="center">Your school: ${thisSchool.name}</legend>
		<legend>Your division: ${thisStudent.division.name}</legend>

		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">FULL NAME</th>
					<th scope="col">EMAIL</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${thisStudent.division.student}" var="student">
					<tr class="table-light">
						<td><c:out value="${student.userRole.user.fullName}" /></td>
						<td><c:out value="${student.userRole.user.email}" /></td>
				</c:forEach>
			</tbody>
		</table>

		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">TIME</th>
					<th scope="col">MONDAY</th>
					<th scope="col">TUESDAY</th>
					<th scope="col">WEDNESDAY</th>
					<th scope="col">THURSDAY</th>
					<th scope="col">FRIDAY</th>
				</tr>
			</thead>
			<tbody>
				<tr class="table-light">
					<td>7:00 - 7:45</td>
					<td><c:out value="${mondaySubjects['1']}" /></td>
					<td>
						<%-- ${mondaySubjects.1} --%>
					</td>
					<td>${mondaySubjects["1"]}</td>
					<td>TEST</td>
					<td>TEST</td>
				</tr>
				<tr class="table-light">
					<td>7:00 - 7:45</td>
					<td><c:out value="${mondaySubjects['0']}" /></td>
					<td>
						<%-- ${mondaySubjects.1} --%>
					</td>
					<td>${mondaySubjects["0"]}</td>
					<td>
						<%-- ${mondaySubjects} --%>
					</td>
					<td>TEST</td>
				</tr>
				<c:forEach begin="1" end="8" varStatus="time">
					<tr class="table-light">
						<td>7:00 - 7:45</td>
						<td><c:out value="${mondaySubjects['time.index']}" /></a></td>
						<td><c:out value="${mondaySubjects['time.index']}" /></a></td>
						<td><c:out value="${mondaySubjects['time.index']}" /></a></td>
						<td><c:out value="${mondaySubjects['time.index']}" /></a></td>
						<td><c:out value="${mondaySubjects['time.index']}" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>