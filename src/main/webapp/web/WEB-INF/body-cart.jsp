<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="body">
	<div class="cart">
		<table>
			<c:if test="${notification != null}">
				<tr class="notification-error" style="text-align: center; color: red;">
					<th colspan="4">
						<c:out value="${notification}"/>
					</th>
				</tr>
			</c:if>
			<tr>
				<th>Products in cart : <c:out value="${cart.getItems().size()}"/></th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Amount</th>
			</tr>
			<c:forEach var="product" items="${cart.getItems()}">
			<tr>
				<td>
					<a class="remove-product" href="<c:url value="/AddToCartController?action=delete&&id=${product.id}"/>">
						Remove
					</a>
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
	</div>
	<form class="form-cart" action="<c:url value="/PayController"/>" method="get">
		<table>
			<tr>
				<th>Customer name :</th>
				<td>
					<input name="email" type="text" placeholder="examp@gmail.com" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
						<c:if test="${acc != null}">value="${acc.usr}"</c:if>/>
				</td>
			</tr>
			<tr>
				<th>Customer address :</th>
				<td>
					<input name="address" type="text" placeholder="Ha noi, Viet Nam"
						<c:if test="${acc != null}">value="${acc.address}"</c:if>/>
				</td>
			</tr>
			<tr>
				<th>Customer phone :</th>
				<td>
					<input name="phone" type="text" placeholder="xxx-xxxx-xxxx" pattern="[0-9]{,13}"
						<c:if test="${acc != null}">value="${acc.phone}"</c:if>/>
				</td>
			</tr>
			<tr>
				<th>Discount code(if any) :</th>
				<td>
					<input name="discount" type="text" placeholder="10010110100"/>
				</td>
			</tr>
		</table>
		<input class="pay-cart" type="submit" value="Submit"/>
	</form>
</div>