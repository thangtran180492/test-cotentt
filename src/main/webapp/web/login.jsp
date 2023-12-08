<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">s
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/css/login.css"/>
<title>Insert title here</title>
</head>
<body>
<% 
	String error = (String)session.getAttribute("error"); 
	if(error != null){
%>

<h2 style="color: red;"><%= error %></h2>

<% } 
	session.setAttribute("error", null);
%>
<jsp:include page="WEB-INF/form-login.jsp"/>
</body>
</html>