<%@page import="DTO.ScarpaOrdine"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collection"%>
<%@page import="model.CarrelloDataSource"%>
<%@page import="DTO.Carrello"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String user=(String) session.getAttribute("user");
	if (user==null)
		response.sendRedirect(request.getContextPath()+"/common/index.jsp");
	CarrelloDataSource cds=new CarrelloDataSource();
	Collection<Carrello> ordini=cds.getOrdini(user);
%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Ordini</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/ordini.css">
</head>
<body>
	<a href="<%=request.getContextPath() %>/common/index.jsp">Torna alla home</a>
	<%for (Carrello c:ordini){%>
		<fieldset>
		<legend>Ordine</legend>
			Data:<%=c.getData() %><br>
			<%=c.getRiepilogo() %>
		</fieldset>
	<%}%>
</body>
</html>