package pl.edu.uam.restapi.storage.entity;

import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.Table;

@Entity
//@Table(name = "users")
@Table(name = "Users")
@NamedQueries({
        @NamedQuery(name = "users.findAll", query = "SELECT u FROM UserEntity u")
})
public class UserEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserEntity.class);

    // auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //fields can be renamed
    //@Column(name = "first")
    @Column(name = "name")
    private String name;

    //fields can be renamed
    //@Column(name = "last")
    @Column(name = "pass")
    private String pass;

    @Column(name = "email")
    private String email;

    //fields can be indexed for better performance
    private boolean active = false;

    //Lifecycle methods -- Pre/PostLoad, Pre/PostPersist...
    @PostLoad
    private void postLoad() {
        LOGGER.info("postLoad: {}", toString());
    }

    public UserEntity() {
    }

    public UserEntity(String name, String pass, boolean active, String email) {
        this.name = name;
        this.pass = pass;
        this.email = email;
    }
    public UserEntity(String name, String pass, String email) {
        this.name = name;
        this.pass = pass;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return name;
    }

    public String getLastName() {
        return pass;
    }

    public boolean isActive() {
        return active;
    }

    public String getName() { return name;}

    public String getPass() { return pass;}

    public String getEmail() { return email;}

    public String getLegia() {return email;}

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("pass", pass)
                .add("email", email)
                .toString();
    }
}
