<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="eadmission" uri="/WEB-INF/tld/eadmission.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/registration.css">
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

table {
	border: none;
}

tr {
	border-top: 3px solid #ccc;
}

th, td {
	text-align: center;
}

.bs-callout {
	padding: 20px;
	margin: 20px 0;
	border: 1px solid #eee;
	border-left-width: 5px;
	border-radius: 3px;
}

.bs-callout-danger {
	border-left-color: #d9534f;
}

.bs-callout-warning {
	border-left-color: #f0ad4e;
}

.bs-callout-success {
	border-left-color: green;
}

.bs-callout h4 {
	margin-top: 0;
	margin-bottom: 5px;
}

.bs-callout-danger h4 {
	color: #d9534f;
}

.bs-callout-warning h4 {
	color: #f0ad4e;
}

.bs-callout-info h4 {
	color: #5bc0de;
}
</style>
</head>
<body>
	<fmt:requestEncoding value="UTF-8" />
	<fmt:setLocale value="${language}" />
	<fmt:setBundle basename="com.erealty.i18n.inscriptions" />
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<a class="navbar-brand" href="/EAdmission/" id="reglink"> <fmt:message
						key="main.link" />
				</a>
				<h1>OOPS, AN ERROR OCCURED</h1>
			</div>
		</div>
	</div>

</body>
</html>