<%@page import="DTO.Scarpa"%>
<%@page import="DTO.Carrello"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	Carrello carrello=(Carrello) session.getAttribute("carrello");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrello</title>
</head>
<body>
	<div id="ordini">
		<h1>Saldo: <%=carrello.getScarpe().stream().mapToDouble(s->s.getPrezzo()).sum() %></h1>
		<%
		for (Scarpa s:carrello.getScarpe()){%>
			<label for="scarpa"><%=s.getNome() %> <%=s.getPrezzo() %></label>
			<img alt="no image" src="<%=request.getContextPath() %>/common/GetPictureServlet?id=<%=s.getId() %>" id="scarpa"><br>
		<%} %>
		<a href="<%=request.getContextPath() %>/common/OrdinaServlet">Acquista</a>
	</div>
</body>
</html>