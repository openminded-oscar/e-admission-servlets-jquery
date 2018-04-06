<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.erealty.i18n.inscriptions" />
<nav class="navbar navbar-default navbar-fixed-top">

	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/EAdmission/" id="reglink"> <fmt:message
					key="main.link" />
			</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-left">
				<li>
					<form class="form-inline" style="display: inline;">
						<select id="language" name="language" onchange="submit()"
							style="width: 120px; position: relative; top: 1em; color: black;">
							<option value="uk_UA" ${language=='uk_UA' ? 'selected' : ''}>Українська</option>
							<option value="en_US" ${language=='en_US' ? 'selected' : ''}>English</option>
						</select>
					</form>
				</li>
				<li><c:if test="${role!=null}">
						<label style="position: relative; left: 1em; top: 1em;"> <fmt:message
								key="general.greeting" />, ${curUser}!
						</label>
					</c:if></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${role!=null}">
					<li>
						<form action="logout" method="post">
							<button type="submit" class="btn btn-default">
								<fmt:message key="general.form.toLogOut" />
							</button>
						</form>
					</li>
				</c:if>
				<c:if test="${role==null}">
					<li>
						<form class="form-inline" action="/EAdmission/" method="post"
							style="display: inline;">
							<div class="form-group">
								<label for="userLogin" style="color: white;"> <fmt:message
										key="general.form.login" />
								</label> <input type="text" class="form-control" name="login"
									id="userLogin" placeholder="Input login here" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"
									data-toggle="popover" data-trigger="hover" data-content="[a-zA-Z][a-zA-Z0-9-_\.]{1,20}" data-placement="bottom"
									required>
							</div>
							<div class="form-group">
								<label for="userPassword" style="color: white;"> <fmt:message
										key="general.form.password" />
								</label> <input type="password" class="form-control" name="password"
									id="userPassword" placeholder="Input password here" required>
							</div>
							<button type="submit" class="btn btn-default">
								<fmt:message key="general.form.toLogin" />
							</button>
						</form>
					</li>
				</c:if>
			</ul>
		</div>
		<c:if test="${error!=null}">
			<div class="row">
				<span style="color: red"><c:out value="${error_message}"></c:out></span>
				<span style="color: red"> <!-- 						<c:out value="${error_message}"></c:out> -->
					<fmt:message key="${error_message}" />
				</span>
			</div>
		</c:if>
	</div>
</nav>