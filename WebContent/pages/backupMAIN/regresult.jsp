<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<jsp:include page="header.jsp" />
	<c:choose>
		<c:when test="${addingResult=='true'}">
					<label><fmt:message key="reg.success" /></label>
					</c:when>
		<c:otherwise>
					<label><fmt:message key="reg.failure" /></label>
					</c:otherwise>
	</c:choose>
</body>
</html>