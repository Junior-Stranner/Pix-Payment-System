package br.com.jujubaprojects.paymentsystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jujubaprojects.paymentsystem.Entity.User;
import br.com.jujubaprojects.paymentsystem.Service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user){
    return ResponseEntity.ok().build();
    }
}
