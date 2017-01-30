
var screenHeight;
var screenWidth;
function getScreenProperties() {
	screenHeight=screen.height;
	screenWidth=screen.width;
	handleUpdate();
}

//const inputs = [].slice.call(document.querySelectorAll('.controls input'));

// listen for changes
//inputs.forEach(input => input.addEventListener('change', handleUpdate));
//inputs.forEach(input => input.addEventListener('mousemove', handleUpdate));

function handleUpdate(val) {
    document.documentElement.style.setProperty('screenHeight', val);
}

function parseURLParams() { // funkcja odpowiedzialna za pobieranie danych query zapisanego w URL-u (zwraca obiekt zbudowany na parametrach i zapisuje go w konsoli)
    var url = document.URL,
        queryStart = url.indexOf("?") + 1,
        queryEnd   = url.indexOf("#") + 1 || url.length + 1,
        query = url.slice(queryStart, queryEnd - 1),
        pairs = query.replace(/\+/g, " ").split("&"),
        parms = {}, name, value, namevalue;

    if (query === url || query === "") {
        return;
    }

    for (var i = 0; i < pairs.length; i++) {
        namevalue = pairs[i].split("=");
        name = decodeURIComponent(namevalue[0]);
        value = decodeURIComponent(namevalue[1]);

        if (!parms.hasOwnProperty(name)) {
            parms[name] = [];
        }

        parms[name].push(namevalue.length === 2 ? value : null);
    }
    //document.getElementById("querytestdiv") = parms;
    console.log(parms);
    console.log(JSON.stringify(parms));
    //console.log(JSON.stringify(parms));
    return parms;
}


var ankietaString = '{"id":123,"title":"Test JSON- ankieta","pytania":[{"idPyt":0,"pytanie":"pytanie 1","rodzaj":"multiple","odpowiedzi":["1","2","3"]},{"idPyt":1,"pytanie":"pytanie 2","rodzaj":"single","odpowiedzi":["a","b","c","d"]},{"idPyt":2,"pytanie":"pytanie 3","rodzaj":"single","odpowiedzi":["one","two","three"]},{"idPyt":3,"pytanie":"pytanie 4","rodzaj":"multiple","odpowiedzi":["111","123","139"]},{"idPyt":4,"pytanie":"Are you a boy or a girl?","rodzaj":"single","odpowiedzi":["Y","N"]}]}';
//var ankietaString = "";
var ankieta = JSON.parse(ankietaString);



function uncheckAllCheckboxes(){; //Funkcjonalność przycisku "Reset" na ekranie wypełniania ankiety
  if (confirm("Na pewno chcesz usunąć zapisane odpowiedzi?")==true) {
    $("input[type='checkbox'], input[type='radio']").prop("checked", false);
  }  
  console.log("Pressed Reset button");
}

function closeAnkieta(){ // Zastępuje ankietę tekstem podziękowania za uczestnictwo po jej zatwierdzeniu
  document.getElementById("main").innerHTML="Ankieta została wysłana.<br>Dziękujemy!";
}


function requestSurveyList(){
  //var listJSON = {"surveys":[{"id":"1111","title":"ankieta1","pytania":[{"idPyt":0,"pytanie":"pyt1","rodzaj":"single","odpowiedzi":["odp1-1","odp1-2"]},{"idPyt":0,"pytanie":"pyt2","rodzaj":"single","odpowiedzi":["odp2-1","odp2-2"]}]},{"id":"1112","title":"ankieta2","pytania":[{"idPyt":0,"pytanie":"pyt1","rodzaj":"single","odpowiedzi":["odp1-1","odp1-2"]},{"idPyt":0,"pytanie":"pyt2","rodzaj":"single","odpowiedzi":["odp2-1","odp2-2","odp2-3"]}]}]}
  $.ajax({
    type: "POST",
    url: "http://kedzierski,herokuapp.com/@@@@@@@@@@@@@@@@@@@@@@@@",
    crossDomain: true,
    data: reJSONString,
    dataType: "application/json",
    contentType: "application/json",
    success: function(responseData, textStatus, jqXHR){
      console.log("requestSurveyList successful");
    }
    error: function(responseData, textStatus, errorThrown){
      console.log("requestSurveyList failed");
    }
  });
  
  return listJSON;
}


function displaySurveyList(){
  $("#surveyListContainer").empty();
  var listJSON = requestSurveyList();
  
  var list = document.createElement("DIV");
  list.className = "surveyList";
  $("#surveyListContainer").append(list);  
  if(listJSON.surveys.length == 0){
    document.getElementById("surveyListContainer").innerHTML = "Nie otrzymano ankiet do wyświetlenia.";
  } else if(listJSON.surveys.length < 0) {
    document.getElementById("surveyListContainer").innerHTML = "Coś poszło nie tak.";
  } else {
    for(var i = 0; i < listJSON.surveys.length; i++){
      var element = document.createElement("P");
      element.className = "surveyListElement";
      
      var link = document.createElement("A");
      link.href = "ankieta.html?surveyID=" + listJSON.surveys[i].id;
      var title = document.createTextNode(listJSON.surveys[i].title);
      link.appendChild(title);
      element.appendChild(link);
      list.appendChild(element);
    }
  }
}


function receiveAnkietaData(id){
  //$(document).ready(function() {
    var ankietaString;
    $.ajax({
      type: "GET",
      url: "http://kedzierski.herokuapp.com/@@@@@@@@@@@@@@@"+id,
      crossDomain: true,
      data: ankietaString,
      dataType: "application/json",
      contentType: "application/json",
    })
    .done(function() {
      console.log( "done" );
    })
    .fail(function() {
      console.log( "fail" );
    })
    .always(function() {
      console.log( "finished" );
    });
    return ankietaString;
  //});
}


function validateAnkieta(){
  var okToClose = true;
  
  $("#ankieta_container").children(".questionContainingP").each(function(i) {
    goodToGo = false;
    $(this).children("input").each(function(){
      if( this.checked == true ) {goodToGo = true;}
      //console.log("wykonano iterację po P");
    })
    if (goodToGo == false){
      //console.log("Nie wypełniono pytania ");
      alert("Nie wszystkie pytania zostały wypełnione!");
      okToClose = false;
      return false;
    }
    //console.log("wykonano iterację validateAnkieta");
  });
  
  if (okToClose == true){
    //console.log("wykonanie funkcji zamykających ankietę");
    getFilledAnkieta();
    closeAnkieta();
  }
}


function displaySruvey(){ // tworzy ankietę na podstawie JSON-a (który powinien byc otrzymywany z serwera)
  $("#ankieta_container").empty();
  //var ankietaString = '{"id":123,"title":"Test JSON- ankieta","pytania":[{"idPyt":0,"pytanie":"pytanie 1","rodzaj":"multiple","odpowiedzi":["1","2","3"]},{"idPyt":1,"pytanie":"pytanie 2","rodzaj":"single","odpowiedzi":["a","b","c","d"]},{"idPyt":2,"pytanie":"pytanie 3","rodzaj":"single","odpowiedzi":["one","two","three"]},{"idPyt":3,"pytanie":"pytanie 4","rodzaj":"multiple","odpowiedzi":["111","123","139"]},{"idPyt":4,"pytanie":"Are you a boy or a girl?","rodzaj":"single","odpowiedzi":["Y","N"]}]}';
  var URLparams = parseURLParams();
  var surveyID = URLparams.surveyID[0];
  var ankietaString = receiveAnkietaData(surveyID);
  var ankieta = JSON.parse(ankietaString);
  
  for(var i=0; i<ankieta.pytania.length; i++){
    var checkboxType;
    if (ankieta.pytania[i].rodzaj=="multiple") {
      checkboxType = "checkbox";
    } else {
      checkboxType = "radio";
    }
    
    if (checkboxType=="checkbox") {
      $("#ankieta_container").append("<div class='ankieta_pytanie'><b>" + ankieta.pytania[i].pytanie + "</b> (proszę wybrać jedną lub więcej odpowiedzi):</div>");
    } else {
      $("#ankieta_container").append("<div class='ankieta_pytanie'><b>" + ankieta.pytania[i].pytanie + "</b> (proszę wybrać jedną odpowiedź):</div>");
    }
    
    var p = document.createElement("P");
    p.id = "an"+i;
    p.className = "questionContainingP";
    $("#ankieta_container").append(p);
    
    for(var j = 0; j < ankieta.pytania[i].odpowiedzi.length; j++){
      var checkbox = document.createElement("input");
      var label = document.createElement("label");
      checkbox.id = "an"+i+"_"+j;
      checkbox.name = ankieta.pytania[i].pytanie;
      checkbox.value = ankieta.pytania[i].odpowiedzi[j];
      checkbox.type = checkboxType;
      label.htmlFor = checkbox.id;
      label.appendChild(document.createTextNode(" " + ankieta.pytania[i].odpowiedzi[j]));
      //document.getElementById("ankieta_container").appendChild(checkbox);
      p.appendChild(checkbox);
      //document.getElementById("ankieta_container").appendChild(label);
      p.appendChild(label);
      $("#an"+i).append("<br>");
    }
    $("#ankieta_container").append("<br>");
  }
  $("#ankieta_container").append("<button onclick='validateAnkieta()'>Zatwierdź</button>");
  $("#ankieta_container").append("<button class='reset_button' onclick='uncheckAllCheckboxes()'>Reset</button>");
}


function getFilledAnkieta(){ // funkcjonalność przycisku "Zatwierdź" z ekranu wypełniania ankiety- zbiera dane z zaznaczonych pól, buduje obiekt JSON i wysyła do serwera
  console.log("Data for: " + Date());
  var reJSONString = '{"id":'+ankieta.id+',"pytania":[';
  for(var i=0; i<ankieta.pytania.length; i++) {
    reJSONString += '{"idPyt":' + ankieta.pytania[i].idPyt +',"odpowiedzi":[';
    for(var j = 0; j < ankieta.pytania[i].odpowiedzi.length; j++){
      if (document.getElementById("an"+i+"_"+j).checked){
        reJSONString += 'true';
	    //console.log(document.getElementById("an"+i+"_"+j));
      }
      else {
        reJSONString += 'false';
      }
      if (j<ankieta.pytania[i].odpowiedzi.length-1) { reJSONString += ',' }
	}
    reJSONString = reJSONString + ']}';
    if (i<ankieta.pytania.length-1) { reJSONString += ',' }
  }
  reJSONString += ']}'
  console.log(reJSONString);
  //var reString = JSON.stringify(reJSONString);
  //var reString = "'" + reJSONString "'";
  //console.log(reString);
  $.ajax({
    type: "POST",
    url: "http://kedzierski,herokuapp.com/@@@@@@@@@@@@@@@@@@@@@@@@",
    crossDomain: true,
    data: reJSONString,
    dataType: "application/json",
    contentType: "application/json",
  })
  .done(function() {
    console.log( "done" )
  })
  .fail(function() {
    console.log( "fail" );
  })
  .always(function() {
    console.log( "finished" );
  });
  //document.getElementById("main").innerHTML=("Dziękujemy za wypełnienie ankiety!");
}


function isUser(){
	f=0
	for(i=0;i<user.userz.length;i++){
	   if(document.getElementById("log").value==user.userz[i].name && document.getElementById("psw").value==user.userz[i].password){
		   f=1
		   location.href='log_in.html'
		   alert("Logged!");
		   break
		}
		else{
			continue
		}
	}
	if (f==0){
		alert("No!");
	}
}
