<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1" name="viewport" content="initial-scale=1, width=device-width">
<title>Sign up</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/styles/login.css">
<script src="<%=request.getContextPath() %>/scripts/validate.js"></script>
</head>
<body>
		<ul>
			<li><a href="login.jsp" class="home">Effettua il login</a></li>
			<li><a href="index.jsp" class="home">Torna alla home</a></li>
		</ul>
	<%String errors=(String) request.getAttribute("errors");
					
	if (errors!=null){%>
		<p><%=errors %></p>
	<%} %>
	<div id="container">
		<div id="form">
			<fieldset>
			<legend>Registrati</legend>
				<form action="Signup" method="post" id="regForm" onsubmit="return validate()">
					<input type="text" name="nome" id="nome" placeholder="Nome" autofocus
					onchange="validateFormElem(this, nameOrLastnamePattern, document.getElementById('errorName'), nameErrorMessage)"><span id="errorName"></span><br>
					
					<input type="text" name="cognome" id="cognome" placeholder="Cognome"
					onchange="validateFormElem(this, nameOrLastnamePattern, document.getElementById('errorLastname'), nameErrorMessage)"><span id="errorLastname"></span><br>
					
					<input type="text" name="età" id="età" placeholder="Età"
					onchange="validateFormElem(this, etaPattern, document.getElementById('errorEtà'), etaErrorMessage)"><span id="errorEtà"></span><br>
					
					<div id="phones">
						<input type="text" name="telefono" placeholder="Telefono"  class="dati" id="telefono0"
						onchange="validateFormElem(this, phonePattern, document.getElementById('errorPhone0'), phoneErrorMessage)">
						<input type="button" value="+" onclick="addPhone()"><span id="errorPhone0"></span>
					</div>
					
					<input type="text" name="username" id="username" placeholder="Username"
					onchange="validateFormElem(this, usernamePattern, document.getElementById('errorUsername'), usernameErrorMessage)"><span id="errorUsername"></span>
					
					<input type="text" name="email" id="email" placeholder="E-mail"
					onchange="validateFormElem(this, emailPattern, document.getElementById('errorEmail'), emailErrorMessage)"><span id="errorEmail"></span><br>
					
					<input type="password" name="password" id="password" placeholder="password"
					onchange="validateFormElem(this, passPattern, document.getElementById('errorPassword'), passErrorMessage)"><span id="errorPassword"></span><br>
					
					<input type="submit" id="submit">
					<input type="reset" id="reset">
				</form>
			</fieldset>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>