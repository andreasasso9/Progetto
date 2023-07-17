<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Sign up</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/login.css">
<script src="<%=request.getContextPath() %>/scripts/validate.js"></script>
</head>
<body>
	<div id="container">
		<div id="form">
			<fieldset>
			<legend>Registrati</legend>
				<form action="Signup" method="post">
					<input type="text" name="nome" placeholder="Nome"
					onchange="validateFormElem(this, nameOrLastnamePattern, document.getElementById('errorName'), nameErrorMessage)"><span id="errorName"></span><br>
					
					<input type="text" name="cognome" placeholder="Cognome"
					onchange="validateFormElem(this, nameOrLastnamePattern, document.getElementById('errorLastname'), nameErrorMessage)"><span id="errorLastname"></span><br>
					
					<input type="text" name="età" placeholder="Età"
					onchange="validateFormElem(this, etaPattern, document.getElementById('errorEtà'), etaErrorMessage)"><span id="errorEtà"></span><br>
					
					<div id="phones">
						<input type="text" name="telefono" placeholder="Telefono"  class="dati" id="telefono0"
						onchange="validateFormElem(this, phonePattern, document.getElementById('errorPhone0'), phoneErrorMessage)">
						<input type="button" value="+" onclick="addPhone()"><span id="errorPhone0"></span>
					</div>
					
					<input type="text" name="username" placeholder="Username"><br>
					
					<input type="text" name="email" placeholder="E-mail"
					onchange="validateFormElem(this, emailPattern, document.getElementById('errorEmail'), emailErrorMessage)"><span id="errorEmail"></span><br>
					
					<input type="password" name="password" placeholder="password"
					onchange="validateFormElem(this, passPattern, document.getElementById('errorPassword'), passErrorMessage)"><span id="errorPassword"></span><br>
					
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