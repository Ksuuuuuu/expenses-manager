package app.components.service;

import app.components.Status;
import app.components.entity.City;
import app.components.entity.LoginUser;
import app.components.entity.RegistryUser;
import app.components.entity.User;
import app.components.exception.IncorrectDataException;
import app.components.repository.CityRepository;
import app.components.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    @Autowired
    public UserService(UserRepository userRepository, CityRepository cityRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Long registry(RegistryUser user) {
        City city = cityRepository.findCityByName(user.getCity()).orElse(null);
        Long idCity;
        if (city == null) {
            idCity = cityRepository.create(new City(user.getCity()));
        } else {
            idCity = city.getId();
        }
        return userRepository.create(new User(user.getLogin(),
                bCryptPasswordEncoder.encode(user.getPassword()), user.getName(), idCity));
    }

    public Long login(LoginUser loginUser) throws IncorrectDataException {
        User user = userRepository.findUserByLogin(loginUser.getLogin()).orElse(null);
        if (user == null || !bCryptPasswordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
            throw new IncorrectDataException("incorrect data");
        }
        return user.getId();
    }

    public Status update(User user) {
        if (userRepository.update(user) == 1) {
            return Status.Success;
        }
        return Status.ServerError;
    }

    public Status delete(long id) {
        if (userRepository.deleteById(id) == 1) {
            return Status.Success;
        }
        return Status.ServerError;
    }

}
