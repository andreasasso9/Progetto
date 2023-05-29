<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Log in</title>
</head>
<body>
	<form action="Login" method="post">
		<label for="username">Username: </label>
		<input type="text" name="username" placeholder="Username" id="username" autofocus>
		<label for="password">Password: </label>
		<input type="password" name="password" placeholder="Password" id="password">
		<input type="submit" value="Log in">
	</form>
	<%
		String errors=(String) request.getAttribute("errors");
		
		if (errors!=null){%>
			<%=errors %>
		<%} %>
</body>
</html>