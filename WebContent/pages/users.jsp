<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of users</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/registration.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
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

th, td {
	text-align: center;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<fmt:requestEncoding value="UTF-8" />
	<fmt:setLocale value="${language}" />
	<fmt:setBundle basename="com.erealty.i18n.inscriptions" />
	<div class="container">
		<div class="row">
			<div class="col-sm-9 col-sm-offset-2">
				<div id="first">
					<table border="1" class="table table-hover table-bordered">
						<caption>
							<fmt:message key="userlist.header" />
						</caption>
						<tr>
							<th>ID</th>
							<th><fmt:message key="general.form.login" /></th>
							<th><fmt:message key="applicant.fullname" /></th>
							<th><fmt:message key="general.form.photo" /></th>
							<th><fmt:message key="general.form.email" /></th>
							<th><fmt:message key="general.form.phone" /></th>
						</tr>
						<c:forEach items="${usersList}" var="element">
							<tr>
								<td>${element.ID}</td>
								<td>${element.login}</td>
								<td>${element.fName} ${element.lName} ${element.mName}</td>
								<td><c:choose>
										<c:when test="${element.photoPath==null}">
					No Image Was Uploaded
					</c:when>
										<c:otherwise>
											<img src="${element.photoPath}"
												alt="picture path is not valid" height="150" width="150" />
										</c:otherwise>
									</c:choose></td>
								<td>${element.eMail}</td>
								<td>${element.telNumber}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>