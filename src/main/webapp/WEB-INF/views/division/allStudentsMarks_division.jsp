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
		<c:otherwise>
			<%@ include file="../jspf/main_menu.jspf"%>
		</c:otherwise>
	</c:choose>

	<div class="jumbotron">
		<legend>Division: ${division.name} ; Subject: ${subject.name}
		</legend>
		<%@ include file="../jspf/division_menu.jspf"%>
		</br>

		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">ID</th>
					<th scope="col">FIRST NAME</th>
					<th scope="col">LAST NAME</th>
					<th scope="col">MARKS</th>
					<th scope="col">ADD</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${students}" var="student">
					<tr class="table-light">
						<td><c:out value="${student.id}" /></td>
						<td><c:out value="${student.firstName}" /></td>
						<td><c:out value="${student.lastName}" /></td>
						<td><c:forEach items="${marks}" var="mark">
								<c:choose>
									<c:when test="${mark.student.id == student.id}">
										<a
											href="${pageContext.request.contextPath}/mark/update/${mark.id}?subject=${subject.id}&student=${student.id}">${mark.value}</a> ,
									</c:when>
								</c:choose>
							</c:forEach></td>
						<td><a class="btn btn-primary"
							href="${pageContext.request.contextPath}/mark/create?subject=${subject.id}&student=${student.id}">Add</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>



		<input action="action" onclick="window.history.go(-1); return false;"
			type="button" class="btn btn-secondary" value="Return" />
	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>