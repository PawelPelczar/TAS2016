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
@RequestMapping("/mongo/users")
@Api(value = "/mongo/users", description = "Operations about users using mongoDB")
@Component
public class MongoUsersResource extends AbstractUsersResource {

    private static final UserDatabase database = new MongoDB();

    @Override
    protected UserDatabase getDatabase() {
        return database;
    }


}
