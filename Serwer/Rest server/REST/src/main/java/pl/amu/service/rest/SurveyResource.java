package pl.amu.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import pl.amu.service.rest.dao.Survey;
import pl.amu.service.service.SurveyService;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
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
@Path("/surveys")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SurveyResource {
    
    private SurveyService surveyService;

    @Autowired
    public SurveyResource(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GET
    public List<Survey> getSurveys(){
        return surveyService.getSurveys();
    }

    @POST
    public Survey registerSurvey(@Valid Survey survey){
        if (surveyService.findByID(survey.getID()) == null) {
            return surveyService.save(survey);
        }
        return null;
    }

    @PUT
    public Survey updateSurvey(Survey survey){
        Survey dbSurvey = surveyService.save(survey);
        return dbSurvey;
    }

    @GET
    @Path("/{ID}")
    public Survey getSurvey(@PathParam("ID") final int iD){
        Survey survey = surveyService.findByID(iD);
        return survey;
    }

    }



