<%request.getSession().invalidate(); %>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Log in</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/login.css">
</head>
<body>
<div id="container">
	<div id="form">
		<fieldset>
		<legend>Login</legend>
			<form action="Login" method="post">
				<input type="text" name="username" placeholder="Username" id="username" autofocus><br>
				<input type="password" name="password" placeholder="Password" id="password"><br>
				<input type="submit" value="Log in">
			</form>
			<a href="signup.jsp">Crea un nuovo account</a><br>
			<a href="index.jsp">Torna alla home</a><br>
			
			<%String errors=(String) request.getAttribute("errors");
				
			if (errors!=null){%>
				<p><%=errors %></p>
			<%} %>
		</fieldset>
	</div>
</div>
</body>
</html>