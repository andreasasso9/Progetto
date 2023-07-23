<%@page import="DTO.Carrello"%>
<%@page import="control.FotoControl"%>
<%@page import="model.ScarpaDataSource"%>
<%@page import="java.util.Iterator"%>
<%@page import="DTO.Scarpa"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String user=(String) session.getAttribute("user");
	Boolean isAdmin=(Boolean) session.getAttribute("isAdmin");
	Collection<?> scarpe=(Collection<?>) application.getAttribute("scarpe");
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
<title>LowSneakers</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/index.css">
<script src="<%=request.getContextPath() %>/scripts/jquery.js"></script>
<script src="<%=request.getContextPath() %>/scripts/ordina.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="scarpa">
		
		<%Iterator<?> it=scarpe.iterator();
	
		while (it.hasNext()){
			Scarpa s=(Scarpa) it.next();
			byte foto[]=FotoControl.load(s.getId()+"");
			if (foto!=null){%>
				<fieldset>
				<legend><%=s.getNome() %></legend>
						<img alt="no immage" src="<%=request.getContextPath() %>/common/GetPictureServlet?id=<%=s.getId() %>">
					<%if (user!=null) {%>
						<form action="AggiungiCarrelloServlet" method="post">
						<div id="caratteristiche">
							<label for="taglia">Taglia</label>
								<select name="taglia" id="taglia">
									<%for (int i=38; i<46; i++){%>
										<option value=<%=i %>><%=i %></option>
									<%}%>
								</select><br>
							<label for="quantità">Quantit&aacute;</label>
							<input type="number" name="quantita" min="1" value="1" id="quantità">
						</div>
							<input type="text" name="scarpaId" style="visibility: hidden;" value="<%=s.getId() %>">
							<input type="submit" value="Aggiungi al carrello">
						</form>
						<p>&euro;<%=s.getPrezzo() %></p>
					<%} else { %>
						<p>&euro;<%=s.getPrezzo() %></p>
					<%} %>
				</fieldset>
			<%}
		}%>
	</div> <!-- fine div foto -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>