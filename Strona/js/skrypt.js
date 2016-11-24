
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


var ankietaString = '{"id":123,"title":"Test JSON- ankieta","pytania":[{"idPyt":0,"pytanie":"pytanie 1","rodzaj":"multiple","odpowiedzi":["1","2","3"]},{"idPyt":1,"pytanie":"pytanie 2","rodzaj":"single","odpowiedzi":["a","b","c","d"]},{"idPyt":2,"pytanie":"pytanie 3","rodzaj":"single","odpowiedzi":["one","two","three"]},{"idPyt":3,"pytanie":"pytanie 4","rodzaj":"multiple","odpowiedzi":["111","123","139"]},{"idPyt":4,"pytanie":"Are you a boy or a girl?","rodzaj":"single","odpowiedzi":["Y","N"]}]}';
//var ankietaString = "";
var ankieta = JSON.parse(ankietaString);



function uncheckAllCheckboxes(){
  var confirmUncheck = confirm("Na pewno chcesz usunąć zapisane odpowiedzi?");
  if (confirmUncheck==true) {
    $("input[type='checkbox']").prop("checked", false);
    $("input[type='radio']").prop("checked", false);
  }  
  console.log("Pressed Reset button");
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

function iterateJSON(){
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
      $("#ankieta_container").append("<div class='ankieta_pytanie'><b>" + ankieta.pytania[i].pytanie + "</b> (proszę wybrać co najmniej 1 odpowiedź):</div>");
    } else {
      $("#ankieta_container").append("<div class='ankieta_pytanie'><b>" + ankieta.pytania[i].pytanie + "</b> (proszę wybrać kilka odpowiedzi):</div>");
    }
    for(var j = 0; j < ankieta.pytania[i].odpowiedzi.length; j++){
      var checkbox = document.createElement("input");
      var label = document.createElement("label");
      checkbox.id = "an"+i+"_"+j;
      checkbox.name = ankieta.pytania[i].pytanie;
      checkbox.value = ankieta.pytania[i].odpowiedzi[j];
      checkbox.type = checkboxType;
      label.htmlFor = checkbox.id;
      label.appendChild(document.createTextNode(ankieta.pytania[i].odpowiedzi[j]));
      document.getElementById("ankieta_container").appendChild(checkbox);
      document.getElementById("ankieta_container").appendChild(label);
      $("#ankieta_container").append("<br>");
    }
    $("#ankieta_container").append("<br>");
  }
  $("#ankieta_container").append("<button onclick='getFilledAnkieta()'>Zatwierdź</button>");
  $("#ankieta_container").append("<button class='reset_button' onclick='uncheckAllCheckboxes()'>Reset</button>");
}
 
function getFilledAnkieta(){
  console.log("Data for: " + Date());
  var reJSON = '{"id":'+ankieta.id+',"pytania":[';
  for(var i=0; i<ankieta.pytania.length; i++) {
    reJSON += '{"idPyt":' + ankieta.pytania[i].idPyt +',"odpowiedzi":[';
    for(var j = 0; j < ankieta.pytania[i].odpowiedzi.length; j++){
      if (document.getElementById("an"+i+"_"+j).checked){
        reJSON += 'true';
	    //console.log(document.getElementById("an"+i+"_"+j));
      }
      else {
        reJSON += 'false';
      }
      if (j<ankieta.pytania[i].odpowiedzi.length-1) { reJSON += ',' }
	}
    reJSON = reJSON + ']}';
    if (i<ankieta.pytania.length-1) { reJSON += ',' }
  }
  reJSON += ']}'
  console.log(reJSON);
  //var reString = JSON.stringify(reJSON);
  //var reString = "'" + reJson "'";
  //console.log(reString);
  $.post/*ajax*/({
    type: "POST",
    crossDomain: true,
    url:"https://s410380.students.wmi.amu.edu.pl/ankieta.html", //można usunąć "http:"/"https:" dla spójności z domenami obsługującymi inny protokół
    contentType: "application/json",
    data: reJSON,
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
