<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="header-background">
	<div class="header-inside">
		<a class="link-home" href="${pageContext.request.contextPath}/ListController">
		<img class="logo" alt="phote" src="${pageContext.request.contextPath}/web/asset/image/logoMarket.png"><br>
		Shopping smartphone</a>
		
		<form class="Form-search" action='<c:url value="/SearchControler"/>' method="get">
			<input id="list" list="browsers" name="browser" id="browser" value="CELLPHONE"/>

			<input id="text" type="text" name="search"
				<c:if test="${search != null}"> value="${search}" </c:if> />
			<input id="submit" type="submit" value="Search"/>
		</form>
		
	</div>
</div>

<div class="topnav">
	<div class="menu-list">
		<a href="${pageContext.request.contextPath}/ListController">Home</a>
		<a href="#">Product</a>
		<a href="#">About</a>
		<div class="login">
		<c:choose>
			<c:when test="${acc != null}">
				<a href="<c:url value="/web/admin/index.jsp" />">
					<c:out value="${acc.name}"/>
				</a>
			</c:when>
			<c:otherwise>
				<a href="<c:url value="/web/login.jsp" />">
				Login
				</a>
				<a href="<c:url value="/web/register.jsp" />">
				register
				</a>
			</c:otherwise>
		</c:choose>
		</div>
	</div>
</div>