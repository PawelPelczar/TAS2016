package pl.amu.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import pl.amu.service.rest.dao.Question;
import pl.amu.service.service.QuestionService;


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


/**
 * Created by s407283 on 22.11.2016.
 */
@Path("/questions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class QuestionResource {

    private QuestionService questionService;

    @Autowired
    public QuestionResource(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GET
    public List<Question> getQuestions() { return questionService.getQuestions();
    }

    @POST
    public Question registerQuestion(@Valid Question question){
        if (questionService.findByID((question).getQuestionID()) == null) {
            return questionService.saveQuestion(question);
        }
        return null;
    }

    @PUT
    public Question updateQuestion(Question question){
        Question dbQuestion = questionService.saveQuestion(question);
        return dbQuestion;
    }

    @GET
    @Path("/{id}")
    public Question findQuestionByID(@PathParam("id") final int id){
        Question question = questionService.findByID(id);
        return question;
    }



}
