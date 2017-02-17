package pl.edu.uam.restapi.dokumentacjaibledy.model;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import javax.xml.ws.soap.AddressingFeature;

public class ExceptionMessage extends WebApplicationException {
    private String message;
    private String userMessage;
    private String info;

    public ExceptionMessage(String message, String userMessage, String info) {
        this.message = message;
        this.userMessage = userMessage;
        this.info = info;
    }

    public String getMessage() {
        return message;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getInfo() {
        return info;
    }
}
