package br.com.jujubaprojects.paymentsystem.Controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jujubaprojects.paymentsystem.Entity.User;
import br.com.jujubaprojects.paymentsystem.Service.UserService;
import br.com.jujubaprojects.paymentsystem.dto.UserCreateRequestDTO;
import br.com.jujubaprojects.paymentsystem.dto.UserResponseDTO;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid UserCreateRequestDTO userCreateRequest) throws MessagingException, UnsupportedEncodingException {
        User user = userCreateRequest.toModel();
        UserResponseDTO userSaved = userService.registerUser(user);
        return ResponseEntity.ok().body(userSaved);
    }
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code){
        if(userService.verify(code)){
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }
}
