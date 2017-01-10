package pl.edu.uam.restapi.storage.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User")
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String legia;
    private String login;
    private String password;
    private String phoneNumber;
    private String email;

    public User() {
    }

    public User(String id, String firstName, String lastName, String legia) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.legia = legia;
    }

    @ApiModelProperty(value = "User id", required = true)
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "User first name", required = true)
    public String getFirstName() {
        return firstName;
    }

    @ApiModelProperty(value = "Legia", required = true)
    public String getLegia() {
        return legia;
    }

    @ApiModelProperty(value = "User last name", required = true)
    public String getLastName() {
        return lastName;
    }
}
