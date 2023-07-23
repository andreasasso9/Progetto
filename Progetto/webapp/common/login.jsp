<%request.getSession().invalidate(); %>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1" name="viewport" content="initial-scale=1, width=device-width">
<title>Log in</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/login.css">
</head>
<body>
	
		<ul>
			<li><a href="signup.jsp" class="home">Crea un nuovo account</a></li>
			<li><a href="index.jsp" class="home">Torna alla home</a></li>
		</ul>
	
	<%String errors=(String) request.getAttribute("errors");
					
	if (errors!=null){%>
		<p><%=errors %></p>
	<%} %>		
	<div id="container">
		<div id="form">
			<fieldset>
			<legend>Login</legend>
				<form action="Login" method="post">
					<input type="text" name="username" placeholder="Username" id="username" autofocus><br>
					<input type="password" name="password" placeholder="Password" id="password"><br>
					<input type="submit" value="Log in" id="submit">
				</form>
			</fieldset>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>