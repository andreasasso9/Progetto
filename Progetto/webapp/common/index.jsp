<%@page import="DTO.Carrello"%>
<%@page import="control.FotoControl"%>
<%@page import="model.ScarpaDataSource"%>
<%@page import="java.util.Iterator"%>
<%@page import="DTO.Scarpa"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Collection<?> scarpe=(Collection<?>) getServletContext().getAttribute("scarpe");
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
</head>
<body>
	<div class="user">
		<%
		String user=(String) session.getAttribute("user");
		if (user==null){
		%>
		<h1>Welcome user</h1>
		<h2>Carrello: <%=carrello.getScarpe().size() %></h2>
		
		<a href="<%=request.getContextPath() %>/common/login.jsp">Log in</a>
		<a href="<%=request.getContextPath() %>/common/signup.jsp">Sign up</a>
		<%}else{%>
		<h1>Welcome <%=user%></h1>
		<h2>Carrello: <%=carrello.getScarpe().size() %></h2>
		<form action="Logout" method="post">
			<input type="submit" value="Log out">
		</form>
		<%}%>
	
		<%
		Boolean isAdmin=(Boolean) session.getAttribute("isAdmin");

		if (isAdmin!=null && isAdmin){%>
			<a href="<%=request.getContextPath()%>/admin/reserved.jsp">Admin</a>
		<%} %>
	</div>
	<div class="foto">
		<%
		Iterator<?> it=scarpe.iterator();
	
		while (it.hasNext()){
			Scarpa s=(Scarpa) it.next();
			byte foto[]=FotoControl.load(s.getId()+"");
			if (foto!=null){%>
			<div>
				<img alt="<%=s.getNome() %>" src="<%=request.getContextPath() %>/common/GetPictureServlet?id=<%=s.getId() %>" id="scarpa">
				<label for="scarpa"><%=s.getNome() %></label>
				<a href="<%=request.getContextPath() %>/common/AggiungiCarrelloServlet?scarpaId=<%=s.getId() %>">Aggiungi al carrello</a>
			</div>
			<%}
		}%>
	</div>
</body>
</html>