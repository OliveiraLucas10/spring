<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>

<title>Oliveira Company Home Page</title>


</head>

<body>
	<h2>Oliveira Company Home Page</h2>

	<hr>

	<p>Welcome to the Oliveira company home page</p>

	<hr>

	<!-- display user name an role -->
	<p>

		User:
		<security:authentication property="principal.username" />
		<br> <br> Role(s):
		<security:authentication property="principal.authorities" />
	</p>

	<security:authorize access="hasRole('MANAGER')">

		<hr>
		<!-- Add a link to point to /leaders ... this is for the managers -->

		<p>
			<a href="${pageContext.request.contextPath}/leaders">LeaderShip
				Meeting </a> (Only for Manager peeps)
		</p>

	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">

		<hr>

		<!-- Add a link to point to /systems ... this is for the admins -->

		<p>
			<a href="${pageContext.request.contextPath}/systems">IT Systems
				Meeting </a> (Only for ADMIN peeps)
		</p>

	</security:authorize>

	<hr>

	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">

		<input type="submit" value="Logout" />

	</form:form>


</body>


</html>