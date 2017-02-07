package pl.edu.uam.restapi.storage.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.edu.uam.restapi.storage.model.Question;
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
    public String  getId() {
        return id;
    }

    @ApiModelProperty(value = "Survey title", required = true)
    public String getTitle() {
        return title;
    }

    @ApiModelProperty(value = "Survey questions", required = true)
    public List<Question> getQuestions() {
        return questions;
    }
}



