<%@page import="java.util.List"%>
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
<meta charset="ISO-8859-1" name="viewport" content="initial-scale=1, width=device-width">
<title>Carrello</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/carrello.css">
</head>
<body>
	<fieldset id="ordine">
		<%if (carrello.getScarpe().size()==0) {%>
			<h1 id="vuoto">Il carrello è vuoto, <a href="<%=request.getContextPath() %>/common/index.jsp">torna alla home per acquistare</a></h1>
		<%} else { %>
			<legend>Saldo: <%=carrello.getScarpe().stream().mapToDouble(s->s.getPrezzo()*s.getQuantità()).sum() %></legend>
			<ul>
				<li><a href="<%=request.getContextPath() %>/common/pagamento.jsp">Acquista</a></li>
				<li><a href="<%=request.getContextPath() %>/common/SvuotaCarrelloServlet">Svuota il carrello</a></li>
				<li><a href="<%=request.getContextPath() %>/common/index.jsp">Trona alla home</a></li>
			</ul>
		
			<%for (ScarpaOrdine s:carrello.getScarpe()){%>
			<fieldset id="scarpa">
			<legend><%=s.getNome() %></legend>
				<label for="scarpa">prezzo:<%=s.getPrezzo() %><br>taglia:<%=s.getTaglia() %><br>quantità:<%=s.getQuantità() %></label><br>
				<img alt="no image" src="<%=request.getContextPath() %>/common/GetPictureServlet?id=<%=s.getId() %>"><br>
				<a href="<%=request.getContextPath() %>/common/RimuoviDalCarrello?scarpaId=<%=s.getId() %>">Rimuovi dal carrello</a>
			</fieldset>	
			<%}
		} %>
	</fieldset>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>