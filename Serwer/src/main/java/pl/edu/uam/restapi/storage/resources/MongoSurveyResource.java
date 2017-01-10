package pl.edu.uam.restapi.storage.resources;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.uam.restapi.storage.database.MongoDB;
import pl.edu.uam.restapi.storage.database.UserDatabase;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mongo/surveys")
@Api(value = "/mongo/surveys", description = "Operations regarding surveys using mongoDB")
@Component
public class MongoSurveyResource extends AbstractSurveyResource {

    private static final UserDatabase database = new MongoDB();

    @Override
    protected UserDatabase getDatabase() {
        return database;
    }
}
