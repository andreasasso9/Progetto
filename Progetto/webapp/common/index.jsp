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
<meta charset="ISO-8859-1">
<title>LowSneakers</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/index.css">
</head>
<body>
	<div class="user">
		<%
		if (user==null){
		%>
		<h1 id="welcome">Welcome user</h1>
		<ul class="user">
			<li"><a href="<%=request.getContextPath() %>/common/login.jsp">Log in</a></li>
			<li><a href="<%=request.getContextPath() %>/common/signup.jsp">Sign up</a></li>
		</ul>
		<%}else{ carrello.setUsername(user);%>
			<h1 id="welcome">Welcome <%=user%></h1>
			<%if (carrello.getScarpe().size()>0) { %>
				<h2 id="carrello">Carrello: <%=carrello.getScarpe().size() %></h2>
			<%} %>
			<ul class="user">
				<li><a href="<%=request.getContextPath() %>/common/Logout">Logout</a></li>
				<li><a href="<%=request.getContextPath() %>/common/carrello.jsp">Vai al carrello</a></li>
				<li><a href="<%=request.getContextPath() %>/common/ordini.jsp">Visualizza i tuoi ordini</a></li>
				<%if (isAdmin!=null && isAdmin){%>
					<li><a href="<%=request.getContextPath()%>/admin/reserved.jsp">Admin</a></li>
				<%} %>
			</ul>
		<%}%>
 	</div> <!-- fine div user -->
 	
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
							<select name="taglia">
								<%for (int i=38; i<46; i++){%>
									<option value=<%=i %>><%=i %></option>
								<%}%>
							</select>
							<input type="number" name="quantità" min="1" value="1" id="quantità">
							<input type="text" name="scarpaId" style="visibility: hidden;" value="<%=s.getId() %>">							<input type="submit" value="Aggiungi al carrello">
						</form>
						<p>Prezzo:<%=s.getPrezzo() %></p>
					<%} %>
				</fieldset>
			<%}
		}%>
	</div> <!-- fine div foto -->
</body>
</html>