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
		<legend>All Users</legend>

		<table class="table table-bordered">
			<thead>
				<tr class="table-light">
					<th scope="col">ID</th>
					<th scope="col">USERNAME</th>
					<th scope="col">EMAIL</th>
					<th scope="col">USER MENU</th>
					<th scope="col">ROLES MENU</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${availableUsers}" var="user">
					<tr class="table-light">
						<td scope="row"><c:out value="${user.id}" /></td>
						<td><c:out value="${user.username}" /></td>
						<td><c:out value="${user.email}" /></td>
						<td>
							<div class="btn-group">
								<div class="btn-group">
									<button type="button" class="btn btn-primary dropdown-toggle"
										data-toggle="dropdown">Actions</button>
									<div class="dropdown-menu">
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/user/update/${user.id}">
											Update user</a> <a class="dropdown-item"
											href="${pageContext.request.contextPath}/user/changepassword/${user.id}">
											Change password</a> <a class="dropdown-item"
											href="${pageContext.request.contextPath}/user/delete/${user.id}">Delete</a>
										<a class="dropdown-item"
											href="${pageContext.request.contextPath}/userrole/create/${user.id}">Add
											user role</a>
									</div>
								</div>
							</div>
						</td>
						<td>
							<table>
								<c:forEach items="${user.userRoles}" var="userRole">
									<tr>
										<td><c:out value="${userRole.userRole}"></c:out></td>
										<td>Placeholder for school info</td>
										<td>
											<div class="btn-group">
												<div class="btn-group">
													<button type="button"
														class="btn btn-primary dropdown-toggle"
														data-toggle="dropdown">Actions</button>
													<div class="dropdown-menu">
														<a class="dropdown-item"
															href="${pageContext.request.contextPath}/userrole/delete/${userRole.id}">Delete</a>
													</div>
												</div>
											</div>
										</td>
									</tr>
								</c:forEach>
							</table>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>

	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>