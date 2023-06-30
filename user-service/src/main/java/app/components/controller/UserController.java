package app.components.controller;

import app.components.Status;
import app.components.entity.LoginUser;
import app.components.entity.RegistryUser;
import app.components.entity.User;
import app.components.exception.AppResponse;
import app.components.exception.IncorrectDataException;
import app.components.service.UserService;
import com.google.gson.Gson;
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
    public ResponseEntity<?> loginUser(@RequestBody String loginUser){
        try {
            Long id = userService.login(new Gson().fromJson(loginUser, LoginUser.class));
            return new ResponseEntity<>(new AppResponse(id, HttpStatus.OK.value(), "login success"), HttpStatus.OK);
        } catch (IncorrectDataException e) {
            return new ResponseEntity<>(new AppResponse(HttpStatus.BAD_REQUEST.value(), "incorrect data for login"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/registry")
    public ResponseEntity<?> registryUser(@RequestBody String user){

        RegistryUser registryUser = new Gson().fromJson(user, RegistryUser.class);
        Long id = userService.registry(registryUser);
        if (id == null){
            return new ResponseEntity<>(new AppResponse(HttpStatus.BAD_REQUEST.value(), "failed to register"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new AppResponse(id, HttpStatus.OK.value(), "registry success"), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody String user){
        Status status = userService.update(new Gson().fromJson(user, User.class));
        if (status.equals(Status.ServerError)){
            return new ResponseEntity<>(new AppResponse(HttpStatus.BAD_REQUEST.value(), "failed update user"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new AppResponse(HttpStatus.OK.value(), "update success"), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")//
    public ResponseEntity<?> delete(@PathVariable Long id){
        if (id == null){
            return new ResponseEntity<>(new AppResponse(HttpStatus.BAD_REQUEST.value(), "no parameter id"),
                    HttpStatus.BAD_REQUEST);
        }
        Status status = userService.delete(id);
        if (status.equals(Status.ServerError)){
            return new ResponseEntity<>(new AppResponse(HttpStatus.BAD_REQUEST.value(), "failed delete user"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new AppResponse(HttpStatus.OK.value(), "delete success"), HttpStatus.OK);
    }

}
