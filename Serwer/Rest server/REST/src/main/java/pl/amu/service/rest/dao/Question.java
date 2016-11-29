package pl.amu.service.rest.dao;

import java.util.List;

/**
 * Created by s407283 on 18.11.2016.
 */
public class Question {
    private int surveyID;
    private int questionID;
    private String content;
    private String isMultiple;
    private List<String> answers;

    public Question(String content, String isMultiple, int surveyID, int questionID, List<String> answers) {this.content = content; this.answers = answers; this.surveyID = surveyID; this.questionID = questionID; this.isMultiple = isMultiple;}

    public Question(){}

    public int getSurveyID() {return surveyID;}

    public int getQuestionID() {return questionID;}

    public void setContent(String content) {this.content = content;}

    public void setAnswers(List<String> answers) {this.answers = answers;}

    public void setSurveyID(int surveyID) {this.surveyID = surveyID;}

    public void setQuestionID(int questionID) {this.questionID = questionID;}

    public void setIsMultiple(String isMultiple) {this.isMultiple = isMultiple;}

    public String getIsMultiple() {return isMultiple;}

    public List<String> getAnswers() {return answers;}

    public String getContent() {return content;}

    @Override
    public String toString(){
        return "{" +
                "\"idPyt\": " + questionID +
                "\"pytanie\": " + content +
                "\"rodzaj\": " + isMultiple +
                "\"odpowiedzi\": " + answers +
                "}";

    }







}

