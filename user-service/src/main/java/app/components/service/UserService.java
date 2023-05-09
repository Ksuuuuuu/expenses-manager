package app.components.service;

import app.components.Status;
import app.components.entity.User;
import app.components.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Status registry(User user){
        if (userRepository.create(user) == 1){
            return Status.Success;
        }
        return Status.ServerError;
    }

    public Status login(String login, String password){
        Optional<User> user = userRepository.findUserByLogin(login);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(password))
                return Status.Success;
        }
        return Status.ClientError;
    }

    public Status update(User user){
        if (userRepository.update(user) == 1){
            return Status.Success;
        }
        return Status.ServerError;
    }

    public Status delete(long id){
        if (userRepository.deleteById(id) == 1){
            return Status.Success;
        }
        return Status.ServerError;
    }

}
