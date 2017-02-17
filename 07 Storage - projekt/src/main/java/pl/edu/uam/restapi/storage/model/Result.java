package pl.edu.uam.restapi.storage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.edu.uam.restapi.storage.model.Question;
import org.hibernate.validator.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;
/**
 * Created by s407283 on 24.01.2017.
 */
public class Result {
    private String surveyId;
    private String questionId;
    private String title;
    private List<String> answers;
    private List<Integer> quantity;

    public Result(){

    }

    public Result(String surveyId, String questionId, String title, List<String> answers, List<Integer> quantity) {
        this.surveyId = surveyId;
        this.questionId = questionId ;
        this.title = title;
        /*List<String> nova = new ArrayList<>();
        for (String string : questions) {
            string.replace("\\", "");
            nova.add(string);
        }
        this.questions = nova;*/
        this.answers = answers;
        this.quantity = quantity;
    }

    public Result(Result result, Result dbResult)
    {
        this.surveyId = surveyId;
        this.questionId = questionId ;
        this.title = title;
    }

    @ApiModelProperty(value = "Survey id", required = true)
    @NotNull(message = "Cannot be left blank")@Size(min = 24, max = 24, message = "Must be a valid ObjectID of an existing survey")
    public String  getSurveyId() {
        return surveyId;
    }

    @ApiModelProperty(value = "Question id", required = true)
    @NotNull(message = "Cannot be left blank")@Pattern(regexp = "^(10|[1-9])$", message = "Must contain an ordinal of a question in aforementioned survey.")
    public String  getQuestionId() {
        return questionId;
    }

    @ApiModelProperty(value = "Survey title", required = true)
    @NotNull(message = "Title cannot be left blank")@Size(min = 4, max = 60, message = "Title must be between 4 and 60 characters long")@Pattern(regexp = "^[A-Z].*(!|/?|.)$", message = "Title must begin with a capital letter and end with either a question mark, a full stop or and exclamation point")
    public String getTitle() {
        return title;
    }

    @ApiModelProperty(value = "Survey answers", required = true)
    @NotNull(message = "Survey must contain questions!")@Length(min = 1, max = 10, message = "Survey must comprise of 1 to 10 questions.")
    public List<String> getAnswers() {
        return answers;
    }

    @ApiModelProperty(value = "Answer values", required = true)
    @NotNull(message = "Survey must contain questions!")@Length(min = 1, max = 10, message = "Survey must comprise of 1 to 10 questions.")
    public List<Integer> getQuantity() { return quantity; }
}

