package pl.edu.uam.restapi.storage.resources;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.uam.restapi.dokumentacjaibledy.exceptions.UserException;
import pl.edu.uam.restapi.storage.database.UserDatabase;
//import pl.edu.uam.restapi.storage.model.Result;
import pl.edu.uam.restapi.storage.model.User;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Collection;

/**
 * Created by s407283 on 10.01.2017.
 */
public abstract class AbstractResultResource {

    protected abstract UserDatabase getDatabase();

    /*@CrossOrigin(origins = "*")
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiOperation(value = "Get users collection", notes = "Get users collection", response = Survey.class, responseContainer = "LIST")
    public Collection<Result> list()

    {
        return getDatabase().getResults();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/{surveyId}/{questionId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiOperation(value = "Get survey by id", notes = "[note]Get survey by id", response = Result.class)
    public Result getUser(@ PathVariable("surveyId") String surveyId) throws Exception {
        Result result = getDatabase().getSurvey(surveyId);

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
    }*/
}


