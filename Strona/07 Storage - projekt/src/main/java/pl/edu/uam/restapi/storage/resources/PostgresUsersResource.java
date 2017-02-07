package pl.edu.uam.restapi.storage.resources;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.uam.restapi.storage.database.PostgresqlDB;
import pl.edu.uam.restapi.storage.database.UserDatabase;


@RestController
@RequestMapping("/postgresql/users")
@Api(value = "/postgresql/users", description = "Operations about users using postgresql")
@Component
public class PostgresUsersResource extends AbstractUsersResource {

    private static final UserDatabase database = new PostgresqlDB();

    @Override
    protected UserDatabase getDatabase() {
        return database;
    }

}
