<%@page import="com.mysql.cj.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>LowSneakers</title>
</head>
<body>

	<%
		String user=(String) session.getAttribute("user");
		if (user==null){
	%>
		<h1>Welcome user</h1>
	<%}else{%>
		<h1>Welcome <%=user%></h1>
	<%}%>
		
	<a href="login.html">Log in</a>
	<a href="signup.html">Sign up</a>
</body>
</html>