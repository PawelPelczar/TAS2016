// ograniczona liczba możliwych odpowiedzi na pytanie
const MAX_NUMBER_OF_ANSWERS = 5;

// zmienne globalne
var numberOfQuestions = 0;
var numberOfAnswers = 0;
var error = false;
var Questions = [];

// funkcja generująca kod HTML
function makeInput(value, id, selector) {
  var selector = document.querySelector(selector);
  var input = document.createElement('div');
  input.setAttribute("class", "row");
  var template = `<label for="answerTitle">${value}</label>
                  <input class="u-full-width answerTitle" type="text" ${id} oninput="addAnswer()">`;
  input.innerHTML = template;
  selector.appendChild(input);
}

// funkcja dodająca kod HTML wygenerowany przez makeInput() do strony głównej
function addAnswer() {
  // numberOfAnswer to globalna zmienna przechowująca liczbę wywołań funkcji makeInput()
  if (numberOfAnswers < MAX_NUMBER_OF_ANSWERS) {
    // pod warunkiem, że ostatni input nie jest pusty
    if (!(document.getElementById("question").lastChild.lastChild.value == "")) {
      makeInput("Treść odpowiedzi:",`class="answerTitle"`,"#question");
      numberOfAnswers++;
    }
  }
}

// funkcja wywoływana w chwili naciśnięcia buttonu "Dodaj nowe pytanie"
function addQuestion() {
  var selector = document.querySelector("#question");
  var input = document.createElement('div');
  input.setAttribute("class", "row");
  var template = `<label for="answerTitle">Treść pytania:</label>
                  <input class="u-full-width" id="questionTitle" type="text" oninput="addAnswer()">`;
  input.innerHTML = template;
  selector.appendChild(input);
  makeInput("Treść odpowiedzi:",`class="answerTitle"`,"#question");
  numberOfAnswers++;
  numberOfQuestions++;
}

// dodawanie pytania do globalnej tablicy asocjacyjnej Questions po naciśnięciu
// buttona "Dodaj!"
function pushQuestion() {
  var titleOfQuestion = document.querySelector("#questionTitle").value;
  var typeOfQuestion = document.getElementById("typeOfQuestion").value;
  var answers = document.getElementsByClassName('u-full-width answerTitle');

  // usunięcie z odpowiedzi pustych inputów
  var newAnswers = [];
  for (var i = 0; i < numberOfAnswers; i++) {
    if (answers[i].value != "") {
      newAnswers.push(answers[i].value);
    }
  }

  if (titleOfQuestion == "" || typeof newAnswers[0] === 'undefined') {
    console.log("jeden z warunków spełniony");
  } else {
    var question = {
      "id": numberOfQuestions,
      "title": titleOfQuestion,
      "type": typeOfQuestion,
      "answers": newAnswers
    }

    Questions.push(question);

    viewSurvey();
    removeInputs();
    addQuestion();

    if (Questions.length == 1) {
      var surveyMenu = document.querySelector("#questionMenu .row");
      var button = document.createElement('div');
      button.setAttribute("class", "four columns");
      var template = `<label for="pushQuestion">Zapisz ankietę:</label>
                      <input class="button-primary" type="submit" value="Zapisz!" onclick="sendSurvey()">`;
      button.innerHTML = template;
      surveyMenu.appendChild(button);
    }
  }
}

// funkcja czyszcząca ekran dodawania pytania
function removeInputs() {
  var selector = document.querySelector("#question");
  document.getElementById('typeOfQuestion').selectedIndex = 0;
  selector.innerHTML = "";
  numberOfAnswers = 0;
}

// funkcja wyświetlająca gotowe (wprowadzone) pytania
function viewSurvey() {
  var selector = document.querySelector("#survey");
  var survey = document.createElement('div');
  var template;

  Questions.forEach((question, index) => {
    template = `<div>Pytanie ${index+1}. ${question.title}</div>`;
    question.answers.forEach((answer) => {
      template += `<div>${answer}</div>`
    });
  });

  survey.innerHTML = template;
  selector.appendChild(survey);
}

// funkcja wysyłająca w formacie JSON ankietę do serwera
function sendSurvey() {
  var titleOfSurvey = document.querySelector("#titleOfSurvey").value;
  if (titleOfSurvey != "") {
    var answers = [1,2,3];
    var survey = `{"title": "${titleOfSurvey}","pytania": [`;
    Questions.forEach((question, index) => {
      survey += `{"idPyt": ${index},"pytanie": "${question.title}","rodzaj": "${question.type}","odpowiedzi": [`
        question.answers.forEach((answer) => {
          survey += `"${answer}",`
        });
      survey = survey.slice(0,survey.length-1);
      survey += "]},";
    });
    survey = survey.slice(0,survey.length-1);
    survey += "]}";

    console.log(survey);
    
	xhr = new XMLHttpRequest();
	var url = "http://localhost:8080/mongo/surveys";
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-type", "application/json");
	
// NIE WIEM CZY TO JEST NA PEWNO POTRZEBNE	
//	xhr.onreadystatechange = function () { 
//    if (xhr.readyState == 4 && xhr.status == 200) {
//		var json = JSON.parse(xhr.responseText);
//		console.log(json.email + ", " + json.password)
//		}
//	}
//	var data = JSON.stringify({"email":"hey@mail.com","password":"101010"});
	xhr.send(survey);
	console.log("Wysłane");
  }
}

function surveySended() {
  var container = document.querySelector(".container");
  var template = `<div class="row">
                    <div class="column">
                      <h3 style="text-align: center">Ankieta zapisana! Życzymy miłego dnia! :-)
                      </div>
                  </div>
                  <div class="row" style="margin-top: 5%">
                    <div class="column">
                      <button style="display:block;margin: 0 auto" onclick="location.reload()">Stwórz kolejną ankietę!</button>
                    </div>
                  </div>
                  <div class="row" style="margin: 0.5%"></div>
                  <div class="row">
                    <div class="column">
                      <form action="index.html">
                        <input class="button-primary" type="submit" value="Powrót do strony głównej" style="display:block;margin: 0 auto"/>
                      </form>
                    </div>
                  </div>`;
  container.innerHTML = template;
}

window.addEventListener('DOMContentLoaded', function () {
  addQuestion();
});
