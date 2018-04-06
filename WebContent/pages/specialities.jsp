<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="eadmission" uri="/WEB-INF/tld/eadmission.tld"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>List of users</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/registration.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/custom.js"></script>
<script src="js/sweetalert.min.js"></script>
<style>
.formLabel {
	width: 280px;
}

.tab-pane {
	height: 300px;
	overflow-y: scroll;
	width: 100%;
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
	<div id="about" style="background-color: white;"
		class="jumbotron text-center container">
		<div class="row">
			<c:if test="${role=='admin'}">
				<div class="col-sm-2">
					<div
						style="background-color: #878787; border-radius: 5px; position: fixed; text-align: left;">
						<a type="button" class="btn btn-block" href="allusers"
							style="color: white;"> <fmt:message key="admin.allusers" />
						</a>
						<button style="background-color: #555; color: white;"
							type="button" class="btn btn-block" id="myBtn">
							<fmt:message key="applications.new" />
							<span class="badge"
								style="background-color: white; color: green;">${notActiveApplications}</span>
						</button>
					</div>

					<div class="container">
						<div class="modal fade" id="myModal" role="dialog">
							<div class="modal-dialog"
								style="max-width: 950px; max-height: 300px;">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>
									<div class="modal-body">
										<div class="form-group tab-pane" style="text-align: center;">
											<label id="mainheader"><fmt:message
													key="applications.toprocess" /></label>
											<form id="applicationProcessor" method="post"
												action="process">
												<input id="applicationSelector" type="hidden"
													name="applicationid" /> <input id="actionSelector"
													type="hidden" name="action" />
											</form>
											<eadmission:applicationsToProcess />
										</div>
										<div class="form-group" style="text-align: center;"></div>
										<button type="submit" class="btn btn-default pull-left"
											data-dismiss="modal">
											<span class="glyphicon glyphicon-remove"></span>
											<fmt:message key="cancel" />
										</button>
									</div>
									<div class="modal-footer"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:if>
			<div
				class="col-sm-9 <c:if test="${role!='admin'}">col-sm-offset-2</c:if>">
				<h2>
					<fmt:message key="specialities.announce" />
				</h2>
				<c:choose>
					<c:when test="${role=='user'}">
						<eadmission:specialities lang="${language}"
							abiturient="${curUser}" />
					</c:when>
					<c:otherwise>
						<eadmission:specialities lang="${language}" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			$("#myBtn").click(function() {
				$("#myModal").modal({
					keyboard : true
				});
			});
		});
	</script>
</body>
</html>