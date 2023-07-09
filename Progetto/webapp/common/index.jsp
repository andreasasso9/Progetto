<%@page import="java.util.Iterator"%>
<%@page import="DTO.Scarpa"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Collection<?> scarpe=(Collection<?>) getServletContext().getAttribute("scarpe");
%>
	
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

	<a href="<%=request.getContextPath() %>/common/login.jsp">Log in</a>
	<a href="<%=request.getContextPath() %>/common/signup.jsp">Sign up</a>
	<%	}else{%>
	<h1>
		Welcome
		<%=user%></h1>
	<form action="Logout" method="post">
		<input type="submit" value="Log out">
	</form>
	<%}%>

	<%
		Boolean isAdmin=(Boolean) session.getAttribute("isAdmin");
	
		if (isAdmin!=null && isAdmin){%>
	<a href="<%=request.getContextPath()%>/admin/reserved.jsp">Admin</a>
	<%} %>
	
	<%
		Iterator<?> it=scarpe.iterator();
	
		while (it.hasNext()){
			Scarpa s=(Scarpa) it.next();%>
			<%=s.getNome() %>
		<%}
	%>

</body>
</html>