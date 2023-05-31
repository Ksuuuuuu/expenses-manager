package app.components.controller;

import app.components.Status;
import app.components.entity.LoginUser;
import app.components.entity.RegistryUser;
import app.components.entity.User;
import app.components.exception.AppError;
import app.components.exception.IncorrectDataException;
import app.components.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping ("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUser loginUser){
        try {
            Long id = userService.login(loginUser);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (IncorrectDataException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "incorrect data for login"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/registry")
    public ResponseEntity<?> registryUser(@RequestBody RegistryUser registryUser){
        Long id = userService.registry(registryUser);
        if (id == null){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "failed to register"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Status status = userService.update(user);
        if (status.equals(Status.ServerError)){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "failed update user"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")// TODO: почему делит гетом?
    public ResponseEntity<?> delete(@PathVariable Long id){
        if (id == null){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "no parameter id"),
                    HttpStatus.BAD_REQUEST);
        }
        Status status = userService.delete(id);
        if (status.equals(Status.ServerError)){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "failed delete user"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
