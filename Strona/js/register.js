function validateForm() {
	var wszystko_OK = true;

	//Sprawdzanie loginu (d�ugo��)
	var val_login = document.forms["myForm"]["nazwa_uzytkownika"].value;
	if (val_login.length <3 || val_login.length >20) {
		alert("Login musi mie� 3-20 znak�w.");
		wszystko_OK = false;
		return false;
	}

	//Sprawdzenie loginu (znaki alfanumeryczne)
	var alphanum_regex = /^[a-z0-9]+$/i;
	var res = alphanum_regex.test(val_login);
	if (res==false){
		alert("Login musi sk�ada� si� tylko z liter i cyfr (bez polskich znak�w)");
		wszystko_OK = false;
		return false;
	}

	//Sprawdzanie maila (format)
	var val_email = document.forms["myForm"]["e-mail"].value;
	var email_format = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
	var email_res = email_format.test(val_email);
	if (email_res==false)
	{
		alert("Niepoprawny format adresu e-mail.");
		wszystko_OK = false;
		return false;
	}

	//Sprawdzanie has�a (d�ugo��)
	var val_haslo1 = document.forms["myForm"]["haslo1"].value;
	var val_haslo2 = document.forms["myForm"]["haslo2"].value;
	if (val_haslo1.length <8 || val_haslo2.length >20) {
		alert("Has�o musi mie� 8-20 znak�w.");
		wszystko_OK = false;
		return false;
	}

	//Sprawdzanie has�a (czy s� identyczne)
	if (val_haslo1 != val_haslo2){
		alert("Has�a nie s� identyczne.");
		wszystko_OK = false;
		return false;
	}

	//Wysy�anie JSONA podczas rejestracji
	if (wszystko_OK = true){
		var nazwa_uzytkownika = document.querySelector('input[name="nazwa_uzytkownika"]').value;
		var haslo = document.querySelector('input[name="haslo1"]').value;
		var email = document.querySelector('input[name="e-mail"]').value;
		if (nazwa_uzytkownika != "") {
			var users = `{"name": "${nazwa_uzytkownika}", "pass": "${haslo}", "email": "${email}"}`;
			console.log(users);

			$.ajax({
				type: 'POST',
				url: 'localhost:8080/mongo/users',
				crossDomain: true,
				data: users,
				dataType: "application/json",
				contentType: "application/json",
				statusCode: {
					201: function(xhr) {
					  alert("Success 201");
					}
				},
				success: function(responseData, textStatus, jqXHR) {
					console.log(responseData);
					console.log("Success");
				}
			});
		}
		return false;
	}
}

//Wysy�anie JSONA podczas logowania
function sendLogin() {
	var login = document.querySelector('input[name="login"]').value;
	var haslo = document.querySelector('input[name="haslo"]').value;
	if (login != "") {
		var users = `{"name": "${login}","pass": "${haslo}"}`;
		console.log(users);

		$.ajax({
		  type: 'POST',
		  url: 'localhost:8080/mongo/users/login',
		  crossDomain: true,
		  data: users,
		  dataType: "application/json",
		  contentType: "application/json",
		  statusCode: {
			200: function(xhr) {
				sessionStorage.token = xhr.responseText.token;
				console.log("Zalogowano, otrzymano token");
			}
		  },
		  success: function(responseData, textStatus, jqXHR) {
			  // value = responseData.someKey;
			  // sessionStorage.token = responseData;
			  // console.log(responseData);
			  // alert("responseData");
		  }
		});
	}
	return false;
}
