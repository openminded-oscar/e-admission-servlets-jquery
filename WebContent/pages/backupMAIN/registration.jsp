<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
		<h1>
			<fmt:message key="reg.welcome" />
		</h1>
		<br>
		<div>
			<fmt:message key="reg.todo" />
		</div>
		<div>&nbsp;</div>
		<form action="register" method="post" target="_blank" enctype="multipart/form-data">
			<div>
				<label class="formLabel"><fmt:message
						key="general.form.login" />*</label><input name="login"
					required="required">
			</div>
			<div>
				<label class="formLabel"><fmt:message
						key="general.form.password" />*</label><input type="password"
					name="password" required="required">
			</div>
			<div>
				<label class="formLabel"><fmt:message
						key="general.form.fname" />*</label><input name="fname"
					required="required">
			</div>
			<div>
				<label class="formLabel"><fmt:message
						key="general.form.lname" />*</label><input name="lname"
					required="required">
			</div>
			<div>
				<label class="formLabel"><fmt:message
						key="general.form.mname" /></label><input name="mname">
			</div>
			<div>
				<label class="formLabel"><fmt:message
						key="general.form.gender" />*</label><input type="radio" name="gender"
					value="male" required="required" id="male"> <label
					for="male"><fmt:message key="general.gender.male" /></label> <input
					type="radio" name="gender" value="female" id="female"> <label
					for="female"><fmt:message key="general.gender.female" /></label>
			</div>
			<div>
				<label class="formLabel">Attach your photo here</label><input
					type="file" accept="image/*" name="photo">
			</div>
			<div>
				<label class="formLabel"><input type="submit" name="submit"></label>
			</div>
		</form>
	</div>
</body>
</html>