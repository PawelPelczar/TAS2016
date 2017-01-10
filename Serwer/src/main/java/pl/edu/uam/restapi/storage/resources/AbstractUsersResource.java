package pl.edu.uam.restapi.storage.resources;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.uam.restapi.storage.database.UserDatabase;
import pl.edu.uam.restapi.storage.model.User;
import pl.edu.uam.restapi.dokumentacjaibledy.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Collection;

@RestController
public abstract class AbstractUsersResource {

    protected abstract UserDatabase getDatabase();

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiOperation(value = "Get users collection", notes = "Get users collection", response = User.class, responseContainer = "LIST")
    public Collection<User> list() {

        return getDatabase().getUsers();
    }


    @RequestMapping(value="/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiOperation(value = "Get user by id", notes = "[note]Get user by id", response = User.class)
    public User getUser(@PathVariable("userId") String userId) throws Exception {
        User user = getDatabase().getUser(userId);

        if (userId.equals("db")) {
            throw new Exception("Database error");
        }

        if (user == null) {
            throw new UserException("User not found", "Użytkownik nie został znaleziony", "http://docu.pl/errors/user-not-found");
        }

        return user;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create user", notes = "Create user", response = User.class)
    public ResponseEntity createUser(@RequestBody User user, HttpServletRequest request) {
        User dbUser = new User(
                "",
                user.getFirstName(),
                user.getLastName(),
                user.getLegia()
        );

        User createdUser = getDatabase().createUser(dbUser);

        return ResponseEntity.created(URI.create(request.getPathInfo() + "/" + createdUser.getId())).body(createdUser);
    }
}
