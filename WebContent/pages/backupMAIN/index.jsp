<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="users" uri="/WEB-INF/tld/users.tld"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
<style>
.formLabel {
	width: 280px;
}

div {
	color: black;
	font-family: verdana;
	font-size: 100%;
}

input {
	color: black;
	font: 1em/1.25em Arial, Helvetica, sans-serif;
}
</style>
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp" />
		<fmt:requestEncoding value="UTF-8" />
		<fmt:setLocale value="${language}" />
		<fmt:setBundle basename="com.erealty.i18n.inscriptions" />
		<c:if test="${role!=null}">
			<fmt:message key="general.greeting" />, ${role}!!!
		</c:if>

		<c:if test="${role=='admin'}">
			<div>
				<label><fmt:message key="admin.allusers" /></label> <a
					href="/EAdmission/allusers"><fmt:message
						key="admin.allusers.link" /></a>
			</div>
		</c:if>
	</div>
</body>
</html>