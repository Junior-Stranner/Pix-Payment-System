package br.com.jujubaprojects.paymentsystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.paymentsystem.Entity.User;
import br.com.jujubaprojects.paymentsystem.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired


    public User registerUser(User user){
        List<User> users = this.userRepository.findAll();
        boolean emailExists = users.stream().anyMatch(eExists -> eExists.getEmail().equals(user.getEmail()));
        if(userRepository.findByEmail(user.getEmail()) != null || emailExists){
            throw new RuntimeException("email already exists");
        }else{
            String encodedPassword = 
            return this.userRepository.save(user);

        }
        
    }
}
