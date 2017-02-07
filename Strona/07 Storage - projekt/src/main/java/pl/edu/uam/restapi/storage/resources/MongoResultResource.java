package pl.edu.uam.restapi.storage.resources;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.uam.restapi.storage.database.MongoDB;
import pl.edu.uam.restapi.storage.database.UserDatabase;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import pl.edu.uam.restapi.storage.model.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.edu.uam.restapi.dokumentacjaibledy.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.*;
/**
 * Created by s407283 on 09.01.2017.
 */@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mongo/results")
@Api(value = "/mongo/results", description = "Operations regarding results using mongoDB")
@Component
public class MongoResultResource extends AbstractResultResource {
    private static final UserDatabase database = new MongoDB();

    @Override
    protected UserDatabase getDatabase() {
        return database;
    }
}


