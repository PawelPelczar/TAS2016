package pl.amu.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import pl.amu.service.rest.dao.User;
import pl.amu.service.service.UserService;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @POST
    public User registerUser(@Valid User user){
        if (userService.findByLogin(user.getLogin()) == null) {
            return userService.save(user);
        }
        return null;
    }

    @PUT
    public User updateUser(User user){
        User dbUser = userService.save(user);
        return dbUser;
    }
    
    @GET
    @Path("/{login}")
    public User getUser(@PathParam("login") final String login){
        User user = userService.findByLogin(login);
        return user;
   }

    @DELETE
    @Path("/{login}")
    public void deleteUser(@PathParam("login") final String login){
        userService.remove(login);
    }

}
