package pl.amu.service.rest.dao;

import java.util.List;

/**
 * Created by s407283 on 18.11.2016.
 */
public class Survey {
    private String name;
    private int ID;
    private List<Question> questions;


    public Survey(String name, int ID, List<Question> questions) {
        this.name = name;
        this.ID = ID;
        this.questions = questions;
    }

    public Survey(String name, int ID, )

    public Survey(){}

    public void setName (String name) {this.name = name;}

    public String getName () {return name;}

    public int getID () {return ID;}

    public void setID (int iD) {this.ID = iD;}

    public void setQuestions (List<Question> questions) {this.questions = questions;}

    public List<Question> getQuestions() {return questions;}

    @Override
    public String toString() {
        String pytania = questions.toString();
        /*for(int i=0; i<questions.size(); i++)
        {
            pytania = pytania + "," + questions.get(i).toString();
        }*/
        return
        "{" +
        "\"id\": " + ID +
        "\"title\": " + name +
        "\"pytania\": " + pytania +
        "}";
    }





}
