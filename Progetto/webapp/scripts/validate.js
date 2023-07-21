const nameOrLastnamePattern = /^[A-Za-z]+$/;
const emailPattern = /^\S+@\S+\.\S+$/;
const phonePattern = /^[0-9]{10}$/;
const etaPattern = /^\d+$/;
const passPattern = /^.{8,}$/;
const nameErrorMessage = "<br>Inserisci solo lettere";
const lastnameErrorMessage = "<br>Inserisci solo lettere";
const emailErrorMessage = "<br>Una email valid deve essere del tipo username@domain.ext";
const phoneErrorMessage = "<br>Un numero valido deve contenere 10 numeri";
const etaErrorMessage = "<br>Inserisci solo numeri";
const passErrorMessage = "<br>La password deve essere lunga almeno 8 caratteri"
let count = 1;

function validate() {
	let valid = true;	
	let form = document.getElementById("form");
	
	let spanName = document.getElementById("errorName");
	if(!validateFormElem(form.nome, nameOrLastnamePattern, spanName, nameErrorMessage)){
		valid = false;
	} 
	let spanLastname = document.getElementById("errorLastname");
	if (!validateFormElem(form.cognome, nameOrLastnamePattern, spanLastname, lastnameErrorMessage)){
		valid = false;
	}
	let spanEmail = document.getElementById("errorEmail");
	if (!validateFormElem(form.email, emailPattern, spanEmail, emailErrorMessage)){
		valid = false;
	}
	
	for (let i = 0; i < count; i++){
		let spanPhone = document.getElementById("errorPhone" + i);
		if (spanPhone == null){ // It has been removed
			continue;
		} else {
			if (!validateFormElem(document.getElementById("telefono" + i), phonePattern, spanPhone, phoneErrorMessage)){
				valid = false;
			}
		}	
	}
	
	let spanEta=document.getElementById("errorEtà");
	if (!validateFormElem(form.età, etaPattern, spanEta, etaErrorMessage)){
		valid=false;
	}
	
	let spanPassword=document.getElementById("errorPassword");
	if (!validateFormElem(form.password, passPattern, spanPassword, passErrorMessage));
	
	return valid;
}

function validateFormElem(formElem, pattern, span, message) {
	if(formElem.value.match(pattern)){
		formElem.classList.remove("error");
		span.style.color = "black";
		span.innerHTML = "";
		return true;
	}
	formElem.classList.add("error");
	span.innerHTML = message;
	span.style.color = "white";
	return false;
}

function addPhone() {
	let container = document.getElementById("phones");
	
	let div = document.createElement("div");
	div.id = "phoneRow" + count;
	
	let element = document.createElement("input");
	element.type = "text";
	element.name = "telefono";
	element.id = "phone" + count;
	element.placeholder = "Telefono";
	div.appendChild(element);
	
	let input = document.createElement("input");
	input.type = "button";
	input.value = "-";
	input.addEventListener("click", function() {removePhone(div.id)});
	div.appendChild(input);
	
	let span = document.createElement("span");
	span.id = "errorPhone" + count;
	div.appendChild(span);
	
	count++;
	
	container.appendChild(div);
	
}

function removePhone(id) {
	let element = document.getElementById(id);
	element.parentNode.removeChild(element);
}
