<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="body">
	<div class="info-card">
		<h2 class="info-name-product">
			<c:out value="${product.name}"/>
		</h2>
		
		<div class="info-mid">
			<img class="info-image" src="${product.src}" alt="avartar"/>
			<div class="infomation">
				<p class="info-price">
						<c:out value="${product.price} USD $$$"/>
				</p>
				
				<p class="info-details">
						<c:out value="${product.description}"/>
				</p>
				
				<a class="info-add-cart" href="<c:url value="/AddToCartController?action=add&id=${product.id}"/>">Add to cart</a>
			</div>
		</div>
	</div>
</div>