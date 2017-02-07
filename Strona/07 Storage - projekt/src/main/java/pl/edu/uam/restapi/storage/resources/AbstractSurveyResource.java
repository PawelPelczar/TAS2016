package pl.edu.uam.restapi.storage.resources;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.uam.restapi.storage.database.UserDatabase;
import pl.edu.uam.restapi.storage.model.Survey;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.edu.uam.restapi.dokumentacjaibledy.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.*;

@RestController
public abstract class AbstractSurveyResource {

    protected abstract UserDatabase getDatabase();
    @CrossOrigin(origins = "*")
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiOperation(value = "Get users collection", notes = "Get users collection", response = Survey.class, responseContainer = "LIST")
    public Collection<Survey> list()

    {
        return getDatabase().getSurveys();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/{surveyId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiOperation(value = "Get survey by id", notes = "[note]Get survey by id", response = Survey.class)
    public Survey getUser(@ PathVariable("surveyId") String surveyId) throws Exception {
        Survey survey = getDatabase().getSurvey(surveyId);
//Usunąć
        if (surveyId.equals("db")) {
            throw new Exception("Database error");
        }
//
        if (survey == null) {
            throw new UserException("Survey not found", "Ankieta nie został znaleziona", "http://docu.pl/errors/user-not-found");
        }

        return survey;
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create survey", notes = "Create survey", response = Survey.class)
    public ResponseEntity createSurvey(@RequestBody Survey survey, HttpServletRequest request) {
        Survey dbSurvey = new Survey(
                "115",
                survey.getTitle(),
                survey.getQuestions()
        );

        Survey createdSurvey = getDatabase().createSurvey(dbSurvey);

        return ResponseEntity.created(URI.create(request.getPathInfo() + "/" + createdSurvey.getId())).body(createdSurvey);
    }

    }
//.header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Credentials", "true")