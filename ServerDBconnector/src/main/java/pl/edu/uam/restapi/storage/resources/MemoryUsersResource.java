package pl.edu.uam.restapi.storage.resources;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.uam.restapi.storage.database.MemoryDB;
import pl.edu.uam.restapi.storage.database.UserDatabase;

@RestController
@RequestMapping("/users")
@Api(value = "/users", description = "Operations about users using static java array")
@Component
public class MemoryUsersResource extends AbstractUsersResource {

    private static final UserDatabase database = new MemoryDB();

    @Override
    protected UserDatabase getDatabase() {
        return database;
    }

}
