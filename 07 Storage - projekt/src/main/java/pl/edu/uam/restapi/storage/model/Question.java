package pl.edu.uam.restapi.storage.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.mongodb.morphia.annotations.Embedded;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.*;

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
    @NotNull(message = "Title cannot be left blank")@Size(min = 4, max = 60, message = "Title must be between 4 and 60 characters long")@Pattern(regexp = "^[A-Z].*(!|/?|.)$", message = "Question must begin with a capitaa letter and end with either a question mark, a full stop or and exclamation point")
    public String  getPytanie() {
        return pytanie;
    }

    @ApiModelProperty(value = "Question rodzaj", required = true)
    @NotNull(message = "Question must be of either SINGLE or MULTIPLE variety")@Pattern(regexp = "^(SINGLE|MULTIPLE)$", message = "Question must be of either SINGLE or MULTIPLE variety")
    public String  getRodzaj() {
        return rodzaj;
    }

    @ApiModelProperty(value = "Question odpowiedzi", required = true)
    @NotNull(message = "Question must contain answers")@Size(min = 1, max = 10, message = "Question must contain between 1 and 10 different answers")
    public List<String>  getOdpowiedzi() { return odpowiedzi; }
}
