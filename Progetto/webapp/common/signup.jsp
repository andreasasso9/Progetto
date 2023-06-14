<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Sign up</title>
</head>
<body>
	<form action="Signup" method="post">
		<label for="nome">Nome: </label>
		<input type="text" name="nome" placeholder="Nome" id="nome"><br>
		
		<label for="Cognome">Cognome: </label>
		<input type="text" name="cognome" placeholder="Cognome" id="cognome"><br>
		
		<label for="età">Età: </label>
		<input type="number" name="età" placeholder="età" id="età"><br>
		
		<label for="telefono">Telefono: </label>
		<input type="tel" name="telefono" placeholder="Telefono" id="telefono"><br>
		
		<label for="username">Username: </label>
		<input type="text" name="username" placeholder="username" id="username"><br>
		
		<label for="email">E-mail: </label>
		<input type="email" name="email" placeholder="E-mail" id="email"><br>
		
		<label for="password">Password: </label>
		<input type="password" name="password" placeholder="password" id="password"><br>
		
		<input type="submit">
		<input type="reset">
		
		<%
			String errors=(String) request.getAttribute("errors");
		
			if (errors!=null){%>
				<%=errors %><%} %>
	</form>
</body>
</html>