package pl.edu.uam.restapi.storage.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.*;
import javax.validation.constraints.NotNull;

@ApiModel(value = "User")
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String legia;
    private String name;
    private String pass;
    private String phoneNumber;
    private String email;

    public User() {
    }

    public User(String id, String name, String pass, String email) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.email = email;
    }


    @ApiModelProperty(value = "User id", required = false)
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "User login", required = true)
    @NotNull
    public String getName() {
        return name;
    }


    @ApiModelProperty(value = "User password", required = true)
    @NotNull(message = "Password cannot be ommitted")
    public String getPass() {
        return pass;
    }

    @ApiModelProperty(value = "User email", required = true)
    @NotNull @Email
    public String getEmail() {
        return email;
    }
/*
    @ApiModelProperty(value = "User first name", required = false)
    public String getFirstName() {
        return name;
    }

    @ApiModelProperty(value = "Legia", required = false)
    public String getLegia() {
        return pass;
    }

    @ApiModelProperty(value = "User last name", required = false)
    public String getLastName() {
        return email;
    }
    */
}
