<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="maxNumberProduct" value="${fn:length(products)}" />
<c:set var="maxProductPage" value="5" />
<%-- phân các Product tìm được và giới hạn chỉ hiển thị dược 5 Product 1 trang --%>
<c:choose>
	<c:when test="${param.pageNumber == null || param.pageNumber <= 1 || maxNumberProduct < maxProductPage}">
		<c:set var="first" value="0" />
	</c:when>
	<c:otherwise>
		<c:set var="first" value="${(param.pageNumber - 1) * maxProductPage}" />
	</c:otherwise>
</c:choose>
		<c:set var="last" value="${first + maxProductPage - 1}" />
<div class="body">
	<div class="row">
		<div class="leftcolumn">
			<div class="list-product">
				
				<%-- nếu không tìm thấy product nào --%>
				<c:if test="${fn:length(products) <= 0}">
					<h2>Not found product</h2>
				</c:if>
				<%-- hiện thị danh sách tất cả sản phẩm tìm được --%>
				<c:forEach var="product" items="${products}" begin="${first}" end="${last}">
					<div class="card">
						<a href="<c:url value="/InformationProductController?id=${product.id}"/>">
							<div class="fakeimg">
								<img class="imgProduct" src="${product.src}" alt="avartar"/>
							</div>
							<p class="name-shop">
								<c:out value="${product.type}"/>
							</p>
							<p class="name-product">
								<c:choose>
									<c:when test="${fn:length(product.name) > 17}">
										<c:out value="${fn:substring(product.name, 0, 17)}..."/>
									</c:when>
									<c:otherwise>
										<c:out value="${product.name}"/>
									</c:otherwise>
								</c:choose>
							</p>
							<p class="price">
								<c:out value="${product.price} USD $$$"/>
							</p>
						</a>
					</div>
				</c:forEach>
				
			</div>
			<div class="page">
				<%-- set số lượng trang chia ra --%>
				<c:choose>
					<c:when test="${maxNumberProduct < maxProductPage}">
						<c:set var="maxPage" value="1"/>
					</c:when>
					<c:when test="${(maxNumberProduct % maxProductPage) > 0}">
						<c:set var="maxPage" value="${(maxNumberProduct - (maxNumberProduct % maxProductPage)) / maxProductPage + 1}"/>
					</c:when>
					<c:otherwise>
						<c:set var="maxPage" value="${(maxNumberProduct - (maxNumberProduct % maxProductPage)) / maxProductPage}"/>
					</c:otherwise>
				</c:choose>
				
				<%-- set nút back --%>
			    <c:choose>
					<c:when test="${param.pageNumber == null || param.pageNumber <= 1}">
						<c:set var="back" value="1"/>
					</c:when>
					<c:otherwise>
						<c:set var="back" value="${param.pageNumber - 1}"/>
					</c:otherwise>
				</c:choose>
				<a href="<c:url value="/SearchControler?search=${search}&pageNumber=${back}"/>">
						back
				</a>
				
				<%-- hiện thị số trang tìm được --%>
				<c:forEach var="i" begin="1" end="${maxPage}">
					<a <c:if test="${i == param.pageNumber || (param.pageNumber == null && i == 1)}">style="background-color: #FF7235; color:#F5FCEF;"</c:if>
					 href="<c:url value="/SearchControler?search=${search}&pageNumber=${i}" />">
						<c:out value="${i}"/>
					</a>
				</c:forEach>
				
				<%-- set nut next --%>
				<c:choose>
					<c:when test="${param.pageNumber == null}">
						<c:set var="next" value="2"/>
					</c:when>
					<c:when test="${param.pageNumber >= maxPage}">
						<c:set var="next" value="1"/>
					</c:when>
					<c:otherwise>
						<c:set var="next" value="${param.pageNumber + 1}"/>
					</c:otherwise>
				</c:choose>
				<a href="
				<c:url value="/SearchControler?search=${search}&pageNumber=${next}"/>">
						next
				</a>
			</div>
		</div>
			<%-- các thành phần bên phải --%>
			<div class="rightcolumn">
				<div class="myCart">
					<a class="cart" href="<c:url value="/web/cart.jsp"/>">
						<img src="${pageContext.request.contextPath}/web/asset/image/icon cart.png"/>
						<h3>Shopping cart</h3>
					</a>
					
					<div class="cartImg">
						<a href="<c:url value="/web/cart.jsp"/>">
							<img class="imgCart" src="${pageContext.request.contextPath}/web/asset/image/icon cart.png" alt="avartar"/>
						</a>
					</div>
				</div>
				
				<div class="card" id="popular">
					<h3>Popular product or banners </h3>
					
				<%-- nếu không tìm thấy product nào --%>
				<c:if test="${fn:length(products) <= 0}">
					<h2>Not found product</h2>
				</c:if>
				<%-- hiện thị danh sách tất cả sản phẩm tìm được --%>
				<c:forEach var="product" items="${products}" begin="0" end="2">
					<a href="<c:url value="/InformationProductController?id=${product.id}"/>">
						<img class="imgProduct" src="${product.src}" alt="avartar"/>
					</a>
				</c:forEach>
				</div>
			</div>
	</div>
</div>