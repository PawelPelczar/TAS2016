package pl.edu.uam.restapi.storage.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.mongodb.morphia.annotations.Embedded;

import java.io.Serializable;
import java.util.*;

@ApiModel(value = "Question")
public class Question{
    private int idPyt;
    private String pytanie;
    private String rodzaj;
    private List<String> odpowiedzi;

    public Question(){

    }

    public Question(int idPyt, String pytanie, String rodzaj, List<String> odpowiedzi){
        this.idPyt = idPyt;
        this.pytanie = pytanie;
        this.rodzaj = rodzaj;
        this.odpowiedzi = odpowiedzi;
    }
    @ApiModelProperty(value = "Question id", required = true)
    public int  getId() {
        return idPyt;
    }

    @ApiModelProperty(value = "Question pytanie", required = true)
    public String  getPytanie() {
        return pytanie;
    }

    @ApiModelProperty(value = "Question rodzaj", required = true)
    public String  getRodzaj() {
        return rodzaj;
    }

    @ApiModelProperty(value = "Question odpowiedzi", required = true)
    public List<String>  getOdpowiedzi() { return odpowiedzi; }
}
