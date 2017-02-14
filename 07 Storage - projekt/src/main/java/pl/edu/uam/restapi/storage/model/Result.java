package pl.edu.uam.restapi.storage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.edu.uam.restapi.storage.model.Question;
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
    public String  getSurveyId() {
        return surveyId;
    }

    @ApiModelProperty(value = "Question id", required = true)
    public String  getQuestionId() {
        return questionId;
    }

    @ApiModelProperty(value = "Survey title", required = true)
    public String getTitle() {
        return title;
    }

    @ApiModelProperty(value = "Survey answers", required = true)
    public List<String> getAnswers() {
        return answers;
    }

    @ApiModelProperty(value = "Answer values", required = true)
    public List<Integer> getQuantity() { return quantity; }
}

