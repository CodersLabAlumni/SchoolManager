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
	<%@ include file="../jspf/school_admin_menu.jspf"%>

	<div class="jumbotron">
		</br>
		<legend>Activate user profile in school</legend>

		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">ID</th>
					<th scope="col">FULL NAME</th>
					<th scope="col">LAST NAME</th>
					<th scope="col">OPTION</th>
					<th scope="col">ACTIVATE</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${userToActivate}" var="userRole">
					<tr class="table-light">
						<td><c:out value="${userRole.id}" /></td>
						<td><c:out value="${userRole.user.firstName} ${userRole.user.lastName}" /></td>
						<td><c:out value="${userRole.username}" /></td>
						<td>
							<div class="btn-group">
								<div class="btn-group">
									<button type="button" class="btn btn-primary dropdown-toggle"
										data-toggle="dropdown"></button>
									<div class="dropdown-menu">
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/student/view/${userRole.id}">View
											details</a>
									</div>
								</div>
							</div>
						</td>
						<td><a class="btn btn-primary"
							href="${pageContext.request.contextPath}/schoolAdmin/setPofileAvailability/${userRole.id}">Activate</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


		<a class="btn btn-secondary"
			href="${pageContext.request.contextPath}/school/all">Return</a>

	</div>

	<%@ include file="../jspf/footer.jspf"%>

</body>
</html>