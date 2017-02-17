package pl.edu.uam.restapi.storage.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.edu.uam.restapi.storage.model.Question;
import org.hibernate.validator.*;

import javax.validation.constraints.*;
import java.util.*;
@CrossOrigin(origins = "150.254.79.31:8080")
@ApiModel(value = "Survey")
/**
 * Created by s407283 on 13.12.2016.
 */
public class Survey {
    private String id;
    private String title;
    private List<Question> questions;

    public Survey(){

    }

    public Survey(String id, String title, List<Question> pytania) {
        this.id = id;
        this.title = title;
        /*List<String> nova = new ArrayList<>();
        for (String string : questions) {
            string.replace("\\", "");
            nova.add(string);
        }
        this.questions = nova;*/
        this.questions = pytania;
    }

    @ApiModelProperty(value = "Survey id", required = true)
    @NotNull(message = "Cannot be left blank") @Size(min = 24, max = 24, message = "Must be a valid ObjectID")
    public String  getId() {
        return id;
    }

    @ApiModelProperty(value = "Survey title", required = true)
    @NotNull(message = "Title cannot be left blank") @Size(min = 4, max = 60, message = "Title must be between 4 and 60 characters long")@Pattern(regexp = "^[A-Z].*(!|/?|.)$", message = "Question must begin with a capitla letter and and with either a question mark, a full stop or and exclamation point")
    public String getTitle() {
        return title;
    }

    @ApiModelProperty(value = "Survey questions", required = true)
    //@NotNull(message = "Survey must contain questions!") @Length(min = 1, max = 10, message = "Survey must comprise of 1 to 10 questions.")
    public List<Question> getQuestions() {
        return questions;
    }
}



