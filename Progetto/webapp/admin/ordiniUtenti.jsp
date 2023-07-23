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
%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1" name="viewport" content="initial-scale=1, width=device-width">
<title>Ordini Utenti</title>
<script src="<%=request.getContextPath() %>/scripts/jquery.js"></script>
<script src="<%=request.getContextPath() %>/scripts/ordina.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/ordini.css">
</head>
<body>
	<aside>
		<ul>
			<li><a href="<%=request.getContextPath() %>/admin/reserved.jsp">Torna alla pagina<br>riservata all'admin</a></li>
			<li><a href="<%=request.getContextPath() %>/common/index.jsp">Torna alla home</a></li>
			<li><a onclick="ordinaPerNome()">Ordina per cliente</a></li>
			<li><input type="date" id="inizio"><input type="date" id="fine"><input type="button" value="Seleziona"
			onclick="ordinaPerData($('input#inizio').val(), $('input#fine').val())"></li>
			
		</ul>
	</aside>
	<div class="scarpa">
		<%for (Carrello c:ordini) { %>
			<fieldset>
			<legend><%=c.getUsername() %></legend>
				<p id="data"><%=c.getData() %></p>
				<p><%=c.getRiepilogo() %></p>
			</fieldset>
		<%} %>
	</div>
</body>
</html>