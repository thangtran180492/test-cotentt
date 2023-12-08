<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/css/notification.css"/>
<title>Insert title here</title>
</head>
<body>
<div class="login">
	<div class="title-login">
		<a href="${pageContext.request.contextPath}/ListController">
			<img class="goto-home" src="${pageContext.request.contextPath}/web/asset/image/icon shop orange.png" alt="photo" />
		</a>
		<h1><c:out value="${notification}"/></h1>
		<table class="notification-product">
			<tr>
				<th>Products in cart : 1</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Amount</th>
			</tr>
			<c:forEach var="product" items="${cart.getItems()}">
			<tr>
				<td>
					<c:out value="${product.name}"/>
				</td>
				<td>
					<c:out value="${product.price} USD"/>
				</td>
				<td>
					<c:out value="${product.number}"/>
				</td>
				<td>
					<fmt:formatNumber value="${product.price * product.number}" pattern="0.00"/> USD
				</td>
			</tr>
			</c:forEach>
			<tr class="total">
				<th colspan="4">Total : <fmt:formatNumber value="${cart.getAmount()}" pattern="0.00"/></th>
			</tr>
		</table>
		<table class="notification-infor">
			<tr>
				<th>Customer name :</th>
				<td>
					<p><c:out value="${email}"/></p>
				</td>
			</tr>
			<tr>
				<th>Customer address :</th>
				<td>
					<p><c:out value="${address}"/></p>
				</td>
			</tr>
			<tr>
				<th>Customer phone :</th>
				<td>
					<p><c:out value="${phone}"/></p>
				</td>
			</tr>
			<tr>
				<th>Discount code(if any) :</th>
				<td>
					<p><c:out value="${discount}"/></p>
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>