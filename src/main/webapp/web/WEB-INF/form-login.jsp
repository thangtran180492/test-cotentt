<%
	Cookie[] cookies = request.getCookies();
	String name = "", pass = "", rem = "";
	for(Cookie cookie: cookies ){
		if(cookie.getName().equals("cookuser")){
			name = cookie.getValue();
		}else if(cookie.getName().equals("cookpass")){
			pass = cookie.getValue();
		}else if(cookie.getName().equals("cookremb")){
			rem = cookie.getValue();
		}
	}
%>

<div class="login">
	<div class="form">
		<a href="${pageContext.request.contextPath}/ListController">
			<img class="goto-home" src="${pageContext.request.contextPath}/web/asset/image/icon shop orange.png" alt="photo" />
		</a>
		<form action="${pageContext.request.contextPath}/LoginController" method="get">
			<h1>Sign in</h1>
			<input name="username" type="text" value="<%= name %>" 
				pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" /><br>
			
			<input name="password" type="password" value="<%= pass %>"/><br>
			
			<input name="remember" type="checkbox" value="1" 
			<%=  "1".equals(rem.trim()) ? "checked" : "" %>/>
			<label>remember</label>
			<a class="gotoRegister" href="${pageContext.request.contextPath}/web/register.jsp">
				register
			</a><br>
			<input type="submit"  value="LOGIN" />
		</form>
	</div>
	<div class="title-login">
		<a href="${pageContext.request.contextPath}/ListController">
			<img class="goto-home" src="${pageContext.request.contextPath}/web/asset/image/icon shop orange.png" alt="photo" />
		</a>
		<h1>Welcome back</h1>
		<p>To keep connected with us please login with your person info</p>
	</div>
</div>