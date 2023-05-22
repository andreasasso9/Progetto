<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Sign up</title>
</head>
<body>
	<form action="Signup" method="post">
		<label for="nome">Nome: </label>
		<input type="text" placeholder="Nome" id="nome">
		
		<label for="Cognome">Cognome: </label>
		<input type="text" placeholder="Cognome" id="cognome">
		
		<label for="telefono">Telefono: </label>
		<input type="tel" placeholder="Telefono" id="telefono">
		
		<label for="username">Username: </label>
		<input type="text" placeholder="username" id="username">
		
		<label for="email">E-mail: </label>
		<input type="email" placeholder="E-mail" id="email">
		
		<label for="password">Password: </label>
		<input type="password" placeholder="password" id="password">
		
		<input type="submit">
		<input type="reset">
	</form>
</body>
</html>