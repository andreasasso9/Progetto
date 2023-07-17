<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Sign up</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/login.css">
</head>
<body>
	<div id="container">
		<div id="form">
			<fieldset>
			<legend>Registrati</legend>
				<form action="Signup" method="post">
					<input type="text" name="nome" placeholder="Nome" id="nome"><br>
					
					<input type="text" name="cognome" placeholder="Cognome" id="cognome"><br>
					
					<input type="number" name="età" placeholder="età" id="età"><br>
					
					<input type="tel" name="telefono" placeholder="Telefono" id="telefono"><br>
					
					<input type="text" name="username" placeholder="username" id="username"><br>
					
					<input type="email" name="email" placeholder="E-mail" id="email"><br>
					
					<input type="password" name="password" placeholder="password" id="password"><br>
					
					<input type="submit" id="submit">
					<input type="reset" id="reset">
				</form>
				
				<a href="login.jsp">Effettua il login</a><br>
				<a href="index.jsp">Torna alla home</a><br>
					
				<%String errors=(String) request.getAttribute("errors");
					
					if (errors!=null){%>
						<p><%=errors %></p>
					<%} %>
			</fieldset>
		</div>
	</div>
</body>
</html>