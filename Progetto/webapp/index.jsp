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
		Boolean isLogged=(Boolean) session.getAttribute("isLogged");
		String user=(String) session.getAttribute("user");
		if (isLogged==null || isLogged==false){
	%>
		<h1>Welcome user</h1>
	<%}else{%>
		<h1>Welcome <%=user%></h1>
	<%}%>
	
	<%
		if (isLogged!=null && isLogged){%>
			<form action="Logout" method="post"><input type="submit" value="Log out"></form>
		<%} else { %>
	<a href="login.jsp">Log in</a>
	<a href="signup.jsp">Sign up</a><%} %>
</body>
</html>