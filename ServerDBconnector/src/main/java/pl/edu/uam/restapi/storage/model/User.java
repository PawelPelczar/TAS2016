package pl.edu.uam.restapi.storage.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User")
public class User {
    private String id;
    private String firstName;
    private String lastName;

    public User() {
    }

    public User(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @ApiModelProperty(value = "User id", required = true)
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "User first name", required = true)
    public String getFirstName() {
        return firstName;
    }

    @ApiModelProperty(value = "User last name", required = true)
    public String getLastName() {
        return lastName;
    }
}
