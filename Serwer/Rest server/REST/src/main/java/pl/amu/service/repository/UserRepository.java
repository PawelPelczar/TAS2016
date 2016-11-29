package pl.amu.service.repository;

import pl.amu.service.rest.dao.User;

import java.util.List;

/**
 * Created by adrian.perek@amu.edu.pl
 */
public interface UserRepository {

    List<User> getUsers();
    User save(User user);
    User findByLogin(String login);
    void remove(String login);
}
