<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="users" uri="/WEB-INF/tld/users.tld"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of users</title>
<link rel="stylesheet" href="css/main.css">
<!-- <style>
body {
	background-color: silver;
}

table {
	border-collapse: collapse;
}

#tablediv {
	margin: auto;
	width: 80%;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}

tr:hover {
	background-color: blue
}

th {
	background-color: #4CAF50;
	color: white;
}

caption {
	font-size: 2em;
}
</style> -->
</head>
<body>
	<jsp:include page="header.jsp" />
	<fmt:requestEncoding value="UTF-8" />
	<fmt:setLocale value="${language}" />
	<fmt:setBundle basename="com.erealty.i18n.inscriptions" />
<!-- 	<div id="first">
		<table border="1">
			<caption>
				<fmt:message key="userlist.header" />
			</caption>
			<tr>
				<th>ID</th>
				<th><fmt:message key="general.form.login" /></th>
				<th><fmt:message key="general.form.password" /></th>
				<th><fmt:message key="general.form.fname" /></th>
				<th><fmt:message key="general.form.lname" /></th>
				<th><fmt:message key="general.form.mname" /></th>
				<th><fmt:message key="general.form.gender" /></th>
				<th><fmt:message key="general.form.photo" /></th>
			</tr>
			<c:forEach items="${usersList}" var="element">
				<tr>
					<td>${element.id}</td>
					<td>${element.login}</td>
					<td>${element.password}</td>
					<td>${element.fname}</td>
					<td>${element.lname}</td>
					<td>${element.mname}</td>
					<td><c:choose>
							<c:when test="${element.isMale=='true'}">
					Male
					</c:when>
							<c:otherwise>
					Female
					<br />
							</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${element.photoPath==null}">
					No Image Was Uploaded
					</c:when>
							<c:otherwise>
								<img src="${element.photoPath}" alt="picture path is not valid"
									height="150" width="150" />
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</table>
	</div>-->
	<div id="tablediv">
	<users:registered />
	</div>
</body>
</html>