package pl.amu.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.amu.service.repository.UserRepository;
import pl.amu.service.rest.dao.User;

import java.util.List;

/**
 * Created by adrian.perek@amu.edu.pl
 */
@Component
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void remove(String login) {
        userRepository.remove(login);
    }

}
