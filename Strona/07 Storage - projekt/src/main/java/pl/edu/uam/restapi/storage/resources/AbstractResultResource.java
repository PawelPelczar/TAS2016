package pl.edu.uam.restapi.storage.resources;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.uam.restapi.dokumentacjaibledy.exceptions.UserException;
import pl.edu.uam.restapi.storage.database.UserDatabase;
import pl.edu.uam.restapi.storage.model.Result;
import pl.edu.uam.restapi.storage.model.User;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Collection;

/**
 * Created by s407283 on 10.01.2017.
 */
public abstract class AbstractResultResource {

    protected abstract UserDatabase getDatabase();

   @CrossOrigin(origins = "*")
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiOperation(value = "Get results collection", notes = "Get results collection", response = Result.class, responseContainer = "LIST")
    public Collection<Result> list()

    {
        return getDatabase().getResults();

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/{surveyId}/{questionId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiOperation(value = "Get responses by id", notes = "[note]Get survey by id", response = Result.class)
    public Result getUser(@ PathVariable("surveyId") String surveyId, @ PathVariable("questionId") String questionId) throws Exception {
        Result result = getDatabase().getResult(surveyId, questionId);

        if (result == null) {
            throw new UserException("Result not found", "Wynik nie został znaleziony", "http://docu.pl/errors/user-not-found");
        }

        return result;
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Send results", notes = "Create results", response = Result.class)
    public ResponseEntity createResults(@RequestBody Result result, HttpServletRequest request) {
        Result currentResult = getDatabase().getResult(result.getSurveyId(), result.getQuestionId());
        Result dbResult = new Result(currentResult, result);

        Result createdResult = getDatabase().createResult(dbResult);

        return ResponseEntity.created(URI.create(request.getPathInfo() + "/" + createdResult.getSurveyId())).body(createdResult);
    }

}


