<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/css/resigter.css"/>
</head>
<body>
	<% 
	String error = (String)session.getAttribute("error"); 
	if(error != null){
	%>

	<h2 style="color: red;"><%= error %></h2>

	<% } %>
<jsp:include page="WEB-INF/form-resigter.jsp"/>
</body>
</html>