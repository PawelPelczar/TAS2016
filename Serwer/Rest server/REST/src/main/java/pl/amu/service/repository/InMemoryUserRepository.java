package pl.amu.service.repository;

import org.springframework.stereotype.Component;
import pl.amu.service.rest.dao.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian.perek@amu.edu.pl
 */
@Component
public class InMemoryUserRepository implements UserRepository {

    private final List<User> users;

    public InMemoryUserRepository() {
        this.users = new ArrayList<>();
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User save(User user) {
        User dbUser = findByLogin(user.getLogin());
        if (dbUser != null) {
            dbUser.setTags(user.getTags());
            dbUser.setName(user.getName());
            dbUser.setEmail(user.getEmail());
        } else {
            users.add(user);
        }
        return user;
    }

    @Override
    public User findByLogin(String login) {
        for (User user : users) {
            if (login.equalsIgnoreCase(user.getLogin())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void remove(String login) {
        User user = findByLogin(login);
        if (user != null) {
            users.remove(user);
        }
    }

}
