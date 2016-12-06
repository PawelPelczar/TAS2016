package pl.edu.uam.restapi.storage.resources;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.uam.restapi.storage.database.RedisDB;
import pl.edu.uam.restapi.storage.database.UserDatabase;


@RestController
@RequestMapping("/redis/users")
@Api(value = "/redis/users", description = "Operations about users using redisDB")
@Component
public class RedisUsersResource extends AbstractUsersResource {

    private static final UserDatabase database = new RedisDB();

    @Override
    protected UserDatabase getDatabase() {
        return database;
    }

}
