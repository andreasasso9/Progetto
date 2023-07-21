<%@page import="java.util.Comparator"%>
<%@page import="java.util.LinkedList"%>
<%@page import="DTO.Carrello"%>
<%@page import="model.CarrelloDataSource"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
CarrelloDataSource cds=new CarrelloDataSource();
LinkedList<Carrello> ordini=(LinkedList<Carrello>) cds.doRetrieveAll();
Comparator<Carrello> comparator=(carrello1, carrello2)->{
	return carrello1.getUsername().compareTo(carrello2.getUsername());
};
ordini.sort(comparator);
%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Ordini Utenti</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/ordini.css">
</head>
<body>
	<aside>
		<ul>
			<li><a href="<%=request.getContextPath() %>/admin/reserved.jsp">Torna alla pagina<br>riservata all'admin</a></li>
			<li><a href="<%=request.getContextPath() %>/common/index.jsp">Torna alla home</a></li>
			<li><a onclick="">Ordina per cliente</a></li>
		</ul>
	</aside>
	<%for (Carrello c:ordini) { %>
		<fieldset>
		<legend><%=c.getUsername() %></legend>
			<p><%=c.getData() %><br>
			<%=c.getRiepilogo() %></p>
		</fieldset>
	<%} %>
</body>
</html>