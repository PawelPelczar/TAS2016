
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

function receiveAnkietaData(){
  //$(document).ready(function() {
    $.ajax({
      crossDomain: true,
      contentType: "application/json",
      type: "GET",
      url: "https://s410380.students.wmi.amu.edu.pl/ankieta.html",
      data: { get_data: ankieta, id: 123},
      success: function(data){
        ankietaString = data;
        iterateJSON();
      }
    });
  //});
}

function validateAnkieta(){
  var okToClose = true;
  
  $("#ankieta_container").children(".questionContainingP").each(function(i) {
    goodToGo = false;
    $(this).children("input").each(function(){
      if( this.checked == true ) {goodToGo = true; console.log("question with id= " + this.id + " detected as checked");}
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

function iterateJSON(){ // tworzy ankietę na podstawie JSON-a (który powinien byc otrzymywany z serwera)
  $("#ankieta_container").html("");
  //var ankietaString = '{"id":123,"title":"Test JSON- ankieta","pytania":[{"idPyt":0,"pytanie":"pytanie 1","rodzaj":"multiple","odpowiedzi":["1","2","3"]},{"idPyt":1,"pytanie":"pytanie 2","rodzaj":"single","odpowiedzi":["a","b","c","d"]},{"idPyt":2,"pytanie":"pytanie 3","rodzaj":"single","odpowiedzi":["one","two","three"]},{"idPyt":3,"pytanie":"pytanie 4","rodzaj":"multiple","odpowiedzi":["111","123","139"]},{"idPyt":4,"pytanie":"Are you a boy or a girl?","rodzaj":"single","odpowiedzi":["Y","N"]}]}';
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
  $.post/*ajax*/({
    type: "POST",
    crossDomain: true,
    url:"https://s410380.students.wmi.amu.edu.pl/ankieta.html", //można usunąć "http:"/"https:" dla spójności z domenami obsługującymi inny protokół
    contentType: "application/json",
    data: reJSONString,
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
