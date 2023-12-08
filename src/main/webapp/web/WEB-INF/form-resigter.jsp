<% String rem = ""; %>
<div class="login">
	<div class="form">
		<form action="${pageContext.request.contextPath}/CreateAccountController" method="post">
			<h1>RESIGTER</h1>
			<input name="email" type="text" placeholder="Enter email" 
				pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" /><br>
			
			<input name="password" type="text" placeholder="Enter password"/><br>
			
			<input name="coverPassword" type="text" placeholder="Enter cover password" /><br>
			
			<input name="fullName" type="text" placeholder="Enter your full Name"/><br>
			
			<input name="address" type="text" placeholder="Enter your address" /><br>
			
			<input name="phone" type="text" placeholder="Enter your number phone" pattern="[0-9]{1,11}"/><br>
			
			<input type="submit"  value="REGISTER" />
		</form>
	</div>
	<div class="title-login">
		<a href="${pageContext.request.contextPath}/ListController">
			<img class="goto-home" src="${pageContext.request.contextPath}/web/asset/image/icon shop orange.png" alt="photo" />
		</a>	
		<h1>Welcome create Account</h1>
		<p>Please enter your infomation to your create new Account</p>
	</div>
</div>