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
	<jsp:include page="header.jsp" />
	<fmt:requestEncoding value="UTF-8" />
	<fmt:setLocale value="${language}" />
	<fmt:setBundle basename="com.erealty.i18n.inscriptions" />
	<div class="jumbotron text-center">
		<h1>
			<fmt:message key="general.university" />
		</h1>
		<p>
			<fmt:message key="general.description" />
		</p>
	</div>

	<!-- Container (About Section) -->
	<div id="about" class="container">
		<div class="row">
			<div class="col-sm-8">
				<h2>
					<fmt:message key="rules.header" />
				</h2>
				<br>
				<ul class="list-group" style="color: black;">
					<c:choose>
						<c:when test="${language=='uk_UA'}">
							<jsp:include page="rulesUA.jsp" />
						</c:when>
						<c:otherwise>
							<jsp:include page="rulesEN.jsp" />
						</c:otherwise>
					</c:choose>
				</ul>
				<br>
				<div class="row">
					<div class="col-sm-6">
						<h4 style="display: inline">
							<fmt:message key="reg.demand" />
						</h4>
					</div>
					<c:if test="${role!='user'}">
						<div class="col-sm-2 col-sm-offset-2">
							<a class="btn btn-default btn-danger btn-lg"
								href="toregistration"> <fmt:message key="reg.link.text" />
							</a>
						</div>
					</c:if>
				</div>
				<br>
			</div>
			<c:if test="${role=='user'}">
				<div class="col-sm-4">
					<div>
						<h2>
							<fmt:message key="abiturient.data" />
						</h2>
						<h4>${abiturient.lName}${abiturient.fName}
							${abiturient.mName}</h4>
						<h4>${abiturient.eMail}</h4>
						<c:choose>
							<c:when test="${abiturient.photoPath==null}">
					No Image Was Uploaded
					</c:when>
							<c:otherwise>
								<img src="${abiturient.photoPath}"
									alt="picture path is not valid" height="150" width="150" />
							</c:otherwise>
						</c:choose>
					</div>
					<h2>
						<fmt:message key="marks.headline" />
					</h2>
					<eadmission:usermarks login="${curUser}" />
					<h2>
						<fmt:message key="specialities.applied" />
					</h2>
					<table class="table table-striped" border="1">
						<thead>
							<tr>
								<th><fmt:message key="specialities.name" /></th>
								<th><fmt:message key="specialities.status" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${appliedSpecialities}" var="element">
								<tr>
									<td>${element.key}</td>
									<td><c:choose>
											<c:when test="${element.value}">
												<fmt:message key="specialities.confirmed" />
											</c:when>
											<c:otherwise>
												<fmt:message key="specialities.notconfirmed" />
											</c:otherwise>
										</c:choose></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
		</div>
		<div class="row" style="margin-top: 2em;" id="specialitiesList">
			<div class="col-sm-12">
				<a href="specialities" class="btn btn-block btn-lg"
					style="background-color: green; color: white;"><fmt:message
						key="continue.limited" /></a>
			</div>
		</div>
	</div>
	<footer class="container-fluid text-center">
			<p>Made by Oleh Kosar, 2016</p>
	</footer>
	<div class="container">
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog" style="max-width: 300px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h2 style="color: black; text-align: center;">
							<fmt:message key="mark.add.header" />
						</h2>
					</div>
					<div class="modal-body">
						<form name="myForm" id="myForm">
							<input type="hidden" name="user" value="${curUser}" />
						</form>
						<form role="form" method="post" action=addmark>
							<div class="form-group" style="text-align: center;">
								<label for="subject"><fmt:message
										key="subject.choose.header" /></label> 
										<select name="subject"
									class="form-control" id="subject"
									required>
								</select>
							</div>
							<div class="form-group" style="text-align: center;">
								<label for="gotpoints"> <fmt:message key="mark.points" /></label>
								<input type="text" class="form-control" name="gotpoints"
									placeholder="Enter points amount" pattern="(1[0-9]{2}|200)"
									data-toggle="popover" data-trigger="hover"
									data-content="100-200" data-placement="bottom" id="gotpoints"
									required />
							</div>
							<button type="submit"
								class="btn btn-default btn-success pull-left">
								<span class="glyphicon glyphicon-send"></span>
								<fmt:message key="mark.submit" />
							</button>
							<button type="submit"
								class="btn btn-default btn-danger pull-right"
								data-dismiss="modal">
								<span class="glyphicon glyphicon-remove"></span>
								<fmt:message key="cancel" />
							</button>
						</form>
					</div>
					<div class="modal-footer">
					
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		function getSubjects(str) {
			$.get("subjects", {
				abiturient : str
			}, function(response) {
				$('#subject').html(response.json);
			});
		}
		
		$(document).ready(function() {
			$("#myBtn").click(function() {
				$("#myModal").modal({
					keyboard : true
				});
			});
			$("#myModal").on('show.bs.modal', function() {
				getSubjects('${curUser}');
			});
		});
	</script>
</body>
</html>