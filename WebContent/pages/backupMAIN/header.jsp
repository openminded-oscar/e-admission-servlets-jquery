<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.erealty.i18n.inscriptions" />
<div>
	<div>
		<label><fmt:message key="general.description" /></label>
	</div>
	<div>
		<label><fmt:message key="general.language.select" /></label>
	</div>
	<form>
		<select id="language" name="language" onchange="submit()">
			<option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Українська</option>
			<option value="en_US" ${language == 'en_US' ? 'selected' : ''}>English</option>
		</select>
	</form>

	<c:if test="${role!=null}">
		<form action="logout" method="post">
			<input type="submit" name="submit" value="Log out!">
		</form>
	</c:if>
	<c:if test="${role==null}">
		<form action="/EAdmission/" method="post">
			<div>
				<label class="formLabel"><fmt:message
						key="general.form.login" /><input name="login" value="${login}"
					required></label>
			</div>
			<div>
				<label class="formLabel"><fmt:message
						key="general.form.password" /><input type="password"
					name="password" required></label>
			</div>
			<input type="submit" value="Log in!">
		</form>
		<c:if test="${error!=null}">
			<span style="color: red"><c:out value="${error_message}"></c:out></span>
			<span style="color: red"> <!-- 						<c:out value="${error_message}"></c:out> -->
				<fmt:message key="${error_message}" /></span>
		</c:if>
		<div>
			<label><fmt:message key="reg.slogan" /></label> <a
				href="/EAdmission/toregistration" id="reglink"><fmt:message
					key="reg.link.text" /></a>
		</div>
	</c:if>
	<a href="/EAdmission/" id="reglink"><fmt:message
			key="main.link" /></a>
</div>