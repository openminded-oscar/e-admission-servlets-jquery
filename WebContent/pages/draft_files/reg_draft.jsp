<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<title>Registration Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/registration.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style>
</style>
</head>

<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="60">
	<jsp:include page="header.jsp" />
	<fmt:requestEncoding value="UTF-8" />
	<fmt:setLocale value="${language}" />
	<fmt:setBundle basename="com.erealty.i18n.inscriptions" />
	<div class="jumbotron text-center">
		<div class="container" style="color: black;">
			<h1 class="well">
				<fmt:message key="general.form.title" />
			</h1>
			<c:choose>
				<c:when test="${addingResult=='true'}">
					<h5 style="color: white;">
						<fmt:message key="reg.success" />
					</h5>
				</c:when>
				<c:when test="${addingResult=='false'}">
					<h5 style="color: red;">
						<fmt:message key="reg.failure" />
					</h5>
				</c:when>
			</c:choose>
			<div class="col-lg-12 well">
				<div class="row">
					<form action="register" method="post" enctype="multipart/form-data"
						name="registrationform" id="registrationform">
						<div class="col-sm-12">
							<div class="row">
								<div class="col-sm-3 form-group">
									<label><fmt:message key="general.form.fname" />*</label> <input
										type="text" name="fname" id="fname" value="${fname}"
										placeholder="Enter First Name Here.."
										pattern="([A-Z]{1}[a-z]+)|([A-ЯІЇҐ]{1}[а-яіїґ]+)"
										class="form-control"
										data-toggle="popover" data-trigger="hover" data-content="A-Za-z|А-Яа-я" data-placement="bottom"
										required>
								</div>

								<div class="col-sm-3 form-group">
									<label><fmt:message key="general.form.lname" />*</label> <input
										type="text" name="lname" id="lname" value="${lname}"
										placeholder="Enter Last Name Here.."
										pattern="([A-Z]{1}[a-z]+)|([A-ЯІЇҐ]{1}[а-яіїґ]+)"
										data-toggle="popover" data-trigger="hover" data-content="A-Za-z|А-Яа-я" data-placement="bottom"
										class="form-control" required>
								</div>
								<div class="col-sm-3 form-group">
									<label><fmt:message key="general.form.mname" /></label> <input
										type="text" name="mname" id="mname" value="${mname}"
										placeholder="Enter First Name Here.."
										pattern="([A-Z]{1}[a-z]+)|([A-ЯІЇҐ]{1}[а-яіїґ]+)"
										data-toggle="popover" data-trigger="hover" data-content="A-Za-z|А-Яа-я" data-placement="bottom"
										class="form-control">
								</div>
								<div class="col-sm-3 form-group">
									<label><fmt:message key="general.form.dob" />*</label> <input
										type="date" name="DOB" id="DOB" value="${DOB}"
										placeholder="Enter Date OF Birth Here.." class="form-control"
										required>
								</div>
							</div>
							<div class="form-group">
								<label><fmt:message key="general.form.phone" />*</label> <input
									name="phone" id="phone" type="text" value="${phone}"
									placeholder="Enter Phone Number Here.." pattern="[0-9]{5,12}"
									data-toggle="popover" data-trigger="hover" data-content="Format: [0-9]{5,12}" data-placement="bottom"
									class="form-control" required>
							</div>
							<div class="form-group">
								<label><fmt:message key="general.form.email" />*</label> <input
									name="email" id="email" type="email" value="${email}"
									placeholder="Enter Email Address Here.."
									pattern="[a-z0-9._%+-]{1,30}@[a-z0-9.-]{1,30}\.[a-z]{2,4}$"
									data-toggle="popover" data-trigger="hover" data-content="[a-z0-9._-]+@[a-z0-9.-]+\.[a-z]{2,4}" data-placement="bottom"
									class="form-control" required>
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label><fmt:message key="general.form.zno" />*</label> <input
										name="zno" id="zno" type="text" value="${zno}"
										placeholder="Enter ZNO Number Here.." pattern="[0-9]{1,10}"
										data-toggle="popover" data-trigger="hover" data-content="[0-9]+" data-placement="bottom"
										class="form-control" required>
								</div>
								<div class="col-sm-6 form-group">
									<label><fmt:message key="general.form.atestat" />*</label> <input
										name="graduationCertificate" id="graduationCertificate"
										value="${graduationCertificate}" type="text"
										placeholder="Enter Graduation Certeficate Number Here.."
										pattern="[0-9]{1,10}" 
										data-toggle="popover" data-trigger="hover" data-content="[0-9]+" data-placement="bottom"
										class="form-control" required>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 form-group">
									<label><fmt:message key="general.form.photo" /></label> <input
										type="hidden" name="MAX_FILE_SIZE" value="4194304" /> <input
										type="file" accept="image/*" name="photo" id="photo"
										class="form-control" value="${photo}">
								</div>
								<!-- 								<div class="col-sm-4 form-group">
									<label><fmt:message key="general.form.photodoc1" /></label> <input
										type="file" accept="image/*" name="passportPhoto1"
										class="form-control" value="${passportPhoto1}">
								</div>

								<div class="col-sm-4 form-group">
									<label><fmt:message key="general.form.photodoc2" /></label> <input
										type="file" accept="image/*" name="passportPhoto2"
										value="${passportPhoto2}" class="form-control">
								</div> -->
							</div>
							<div class="row">
								<div class="col-sm-6 form-group">
									<label><fmt:message key="general.form.login" />*</label> <input
										name="login" id="login" type="text"
										placeholder="Enter Login Here.."
										pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" 
										class="form-control"
										data-toggle="popover" data-trigger="hover" data-content="[a-zA-Z][a-zA-Z0-9-_\.]{1,20}" data-placement="bottom"
										value="${login}" required>
								</div>
								<div class="col-sm-6 form-group">
									<label><fmt:message key="general.form.password" />*</label> <input
										name="password" id="password" type="password"
										placeholder="Enter Password Here.." pattern=".{4,30}"
										class="form-control" 
										value="${password}"
										required>
								</div>
							</div>
							<button type="submit" class="btn btn-lg btn-info">
								<fmt:message key="general.form.register" />
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<footer class="container-fluid text-center"> </footer>
	<script>
		$(document).ready(function() {
			$('[data-toggle="popover"]').popover();
		});
	</script>
</body>
</html>