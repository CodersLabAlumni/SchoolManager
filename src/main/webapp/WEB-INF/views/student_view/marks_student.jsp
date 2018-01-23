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
		<legend>All Subjects</legend>

		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">MARK</th>
					<th scope="col">DESCRIPTION</th>
					<th scope="col">DATE</th>
					<th scope="col">SUBJECT</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${thisStudent.mark}" var="subject">
					<tr class="table-light">
						<td scope="row"><c:out value="${mark.value}" /></td>
						<td><c:out value="${mark.description}" /></td>
						<td><c:out value="${mark.date}" /></td>
						<td><c:out value="${mark.subject.name}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>