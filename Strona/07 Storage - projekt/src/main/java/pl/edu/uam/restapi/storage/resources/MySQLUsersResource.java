package pl.edu.uam.restapi.storage.resources;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.uam.restapi.storage.database.MySQLDB;
import pl.edu.uam.restapi.storage.database.UserDatabase;


@RestController
@RequestMapping("/mysql/users")
@Api(value = "/mysql/users", description = "Operations regarding users using MySQL")
@Component
public class MySQLUsersResource extends AbstractUsersResource {

    private static final UserDatabase database = new MySQLDB();

    @Override
    protected UserDatabase getDatabase() {
        return database;
    }

}
