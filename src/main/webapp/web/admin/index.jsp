<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/css/admin.css"/>
<title>Insert title here</title>
</head>
<body>
	<c:if test="${acc == null}">
		<jsp:forward page="/ListController" />
	</c:if>
	<div class="admin-background">
		<jsp:include page="manager.jsp"/>
		<jsp:include page="content.jsp"/>
	</div>
</body>
</html>