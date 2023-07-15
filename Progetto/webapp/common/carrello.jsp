<%@page import="java.util.ArrayList"%>
<%@page import="DTO.ScarpaOrdine"%>
<%@page import="DTO.Scarpa"%>
<%@page import="DTO.Carrello"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	if (session.getAttribute("user")==null)
		response.sendRedirect(request.getContextPath()+"/common/index.jsp");
	Carrello carrello=(Carrello) session.getAttribute("carrello");
%>
<!DOCTYPE html>
<html lang=it>
<head>
<meta charset="ISO-8859-1">
<title>Carrello</title>
</head>
<body>
	<div id="ordini">
		<h1>Saldo: <%=carrello.getScarpe().stream().mapToDouble(s->s.getPrezzo()*s.getQuantità()).sum() %></h1>
		<a href="<%=request.getContextPath() %>/common/OrdinaServlet">Acquista</a>
		<a href="<%=request.getContextPath() %>/common/SvuotaCarrelloServlet">Svuota il carrello</a>
		<a href="<%=request.getContextPath() %>/common/index.jsp">Trona alla home</a>
		<%
		for (ScarpaOrdine s:carrello.getScarpe()){%>
		<fieldset>
		<legend><%=s.getNome() %></legend>
			<label for="scarpa">prezzo:<%=s.getPrezzo() %><br>taglia:<%=s.getTaglia() %><br>quantità:<%=s.getQuantità() %></label><br>
			<img alt="no image" src="<%=request.getContextPath() %>/common/GetPictureServlet?id=<%=s.getId() %>"><br>
			<a href="<%=request.getContextPath() %>/common/RimuoviDalCarrello?scarpaId=<%=s.getId() %>">Rimuovi dal carrello</a>
		</fieldset>	
		<%} %>
	</div>
</body>
</html>