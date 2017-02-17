package pl.edu.uam.restapi.storage.resources;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.uam.restapi.storage.database.UserDatabase;
import pl.edu.uam.restapi.storage.exceptions.UserDbException;
import pl.edu.uam.restapi.storage.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.edu.uam.restapi.dokumentacjaibledy.exceptions.UserException;
import pl.edu.uam.restapi.dokumentacjaibledy.exceptions.*;
import pl.edu.uam.restapi.dokumentacjaibledy.model.ExceptionMessage;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response.Status;
import javax.validation.*;
import org.hibernate.validator.*;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import  pl.edu.uam.restapi.dokumentacjaibledy.exceptions.mappers.InternalErrorsMapper;
import java.net.URI;
import java.util.Collection;

@RestController
public abstract class AbstractUsersResource {

    protected abstract UserDatabase getDatabase();
    @CrossOrigin(origins = "*")
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiOperation(value = "Get users collection", notes = "Get users collection", response = User.class, responseContainer = "LIST")
    public Collection<User> list() {

        return getDatabase().getUsers();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ApiOperation(value = "Get user by id", notes = "[note]Get user by id", response = User.class)
    public User getUser(@PathVariable("userId") String userId) throws Exception {
        User user = getDatabase().getUser(userId);

        if (userId.equals("db")) {
            throw new UserException("Uzytkownik nie istnieje", "404", "Nie znaleziono uzytkownika o id: " +userId);
        }

        if (user == null) {
            //return Response.status(Response.Status.NOT_FOUND)
            //        .entity("Błąd 404").type("application/json").build();
            //throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return user;
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create user", notes = "Create user", response = User.class)
    public ResponseEntity createUser(@RequestBody @Valid User user, HttpServletRequest request) {
        User dbUser = new User(
                "",
                user.getName(),
                user.getPass(),
                user.getEmail()
        );

        User createdUser = getDatabase().createUser(dbUser);

        return ResponseEntity.created(URI.create(request.getPathInfo() + "/" + createdUser.getId())).body(createdUser);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping (value ="/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create user", notes = "Create survey", response = String.class)
    public String token() {
        return "{name: name, token: 2047fe0928fue20fe2f32047fe0928fue20fe2f3}";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete user by id", notes = "[note]Delete user by id", response = User.class)
    public User deleteUser(@PathVariable("userId") String userId) throws Exception {
        User user = getDatabase().getUser(userId);

        if (user == null) {
            throw new UserException("Uzytkownik nie istnieje", "404", "Nie znaleziono uzytkownika o id: " + userId);
        } else {
            return getDatabase().deleteUser(userId);
        }
    }




    }

