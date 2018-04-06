<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>List of users</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/registration.css">
<link rel="stylesheet" type="text/css" href="css/sweetalert.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/sweetalert.min.js"></script>
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

.good {
	background-color: red;
}

.better {
	background-color: #FFFF49;
}

.best {
	background-color: rgb(145, 240, 66);
}

table {
	border-collapse: collapse;
}

#tablediv {
	margin: auto;
	width: 80%;
}

th, td {
	text-align: center;
}

tr:hover {
	background-color: blue
}

th {
	background-color: white;
	color: black;
}

caption {
	font-size: 2em;
}
</style>

</head>
<body>
	<jsp:include page="header.jsp" />
	<fmt:requestEncoding value="UTF-8" />
	<fmt:setLocale value="${language}" />
	<fmt:setBundle basename="com.erealty.i18n.inscriptions" />
	<div id="about" style="background-color: white;"
		class="jumbotron text-center container">
		<!-- 		 && applyStatus != 'true'  -->
		<div class="row">
			<div class="col-sm-2 col-sm-offset-5">
				<c:if test="${role=='user'}">
					<c:if test="${applyStatus==null}">
						<a href="apply" class="btn btn-block  btn-lg"
							style="background-color: red; color: white;"><fmt:message
								key="apply.button" /></a>
					</c:if>
					<c:if test="${justApplied=='yes'}">
					<label id="sendSuccess" style="display:none"><fmt:message key="applied.success" /></label>
						<script>
							swal($('#sendSuccess').text(), "", "success");
						</script>
					</c:if>
				</c:if>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<h2>
					<fmt:message key="applicant.announce" />
				</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<table border="1">
					<thead>
						<tr class="row">
							<th class="col-sm-3"><fmt:message key="applicant.number" /></th>
							<th class="col-sm-3"><fmt:message key="applicant.fullname" /></th>
							<th class="col-sm-3"><fmt:message key="applicant.marks" /></th>
							<th class="col-sm-3"><fmt:message key="applicant.total" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${applicantsList}" var="applicant"
							varStatus="status">
							<tr
								<c:choose>
						<c:when test="${status.count<=speciality.budgetLimit}">				
						class="row best"
						</c:when>
						<c:when test="${status.count<=(speciality.commerceLimit+speciality.commerceLimit)}">				
						class="row better"
						</c:when>
						<c:otherwise>				
						class="row good"
						</c:otherwise>
						</c:choose>>
								<td class="col-sm-3">${status.count}</td>
								<td class="col-sm-3">${applicant.abiturFullName}</td>
								<td class="col-sm-3">${applicant.mark1Points}
									${applicant.mark2Points} ${applicant.mark3Points}
									${applicant.mark4Points}</td>
								<td class="col-sm-3">${applicant.mark1Points+applicant.mark2Points+applicant.mark3Points+applicant.mark4Points}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
	</div>
</body>

</html>