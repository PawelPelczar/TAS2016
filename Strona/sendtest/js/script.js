var simpleJson = {"name":"Bond", "password":"Jamesbond"};
var simpleJsonString = JSON.stringify(simpleJson);

function sendByCORS(jsonForSending){
	$.ajax({
		type: 'POST',
		url: 'http://kedzieski.herokuapp.com/register',
		crossDomain: true,
		data: jsonForSending,
		dataType: "application/json",
		contentType: "application/json",
		timeout: 2000,
		success: function(responseData, textStatus, jqXHR) {
			var value = responseData.someKey;
			console.log(responseData);
			console.log("Success");
		},
		error: function (responseData, textStatus, errorThrown) {
			console.log('POST failed.');
		}
	});
}

function sendSimpleJson(amount, interval){
  console.log("\n-----------------------------------------\n")
	for(var i = 0; i < amount; i++){
		sendByCORS(simpleJson);
		setTimeout(interval);
	}
}