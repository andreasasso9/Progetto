<%@page import="DTO.Carrello"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
String user=(String) session.getAttribute("user");
Boolean isAdmin=(Boolean) session.getAttribute("isAdmin");
Carrello carrello=(Carrello) session.getAttribute("carrello");
if (carrello==null){
	carrello=new Carrello();
	session.setAttribute("carrello", carrello);
}
%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1" name="viewport" content="initial-scale=1, width=device-width">
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/index.css">
<script src="<%=request.getContextPath() %>/scripts/jquery.js"></script>
<script src="<%=request.getContextPath() %>/scripts/ordina.js"></script>
</head>
<body>
	<header>
		<%if (user==null){%>
			<h1 id="welcome">Welcome user</h1>
			<ul class="user">
				<li><a href="<%=request.getContextPath() %>/common/login.jsp">Log in</a></li>
				<li><a href="<%=request.getContextPath() %>/common/signup.jsp">Sign up</a></li>
				<li><a onclick="ordinaPerNome()">Ordina per nome</a></li>
				<li><a onclick="ordinaPerPrezzo()">Ordina per prezzo</a></li>
			</ul>
		<%}else{ carrello.setUsername(user);%>
			<h1 id="welcome">Welcome <%=user%></h1>
			<%	int size=carrello.getScarpe().size();
				if (size>0) { %>
					<h2 id="carrello">Carrello: <%=size %></h2>
			<%} %>
			<ul class="user">
				<li><a href="<%=request.getContextPath() %>/common/Logout">Logout</a></li>
				<li><a href="<%=request.getContextPath() %>/common/carrello.jsp">Vai al carrello</a></li>
				<li><a href="<%=request.getContextPath() %>/common/ordini.jsp">Visualizza i tuoi ordini</a></li>
				<li><a onclick="ordinaPerNome()">Ordina per nome</a></li>
				<li><a onclick="ordinaPerPrezzo()">Ordina per prezzo</a></li>
				<%if (isAdmin!=null && isAdmin){%>
					<li><a href="<%=request.getContextPath()%>/admin/reserved.jsp">Admin</a></li>
				<%} %>
			</ul>
		<%}%>
 	</header> <!-- fine div user -->
</body>
</html>