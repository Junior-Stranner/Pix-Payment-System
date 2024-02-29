package br.com.jujubaprojects.paymentsystem.Service;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.paymentsystem.Entity.User;
import br.com.jujubaprojects.paymentsystem.Repository.UserRepository;
import br.com.jujubaprojects.paymentsystem.Utils.RandomString;
import br.com.jujubaprojects.paymentsystem.dto.UserResponseDTO;
import jakarta.mail.MessagingException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;


    public UserResponseDTO registerUser(User user) throws UnsupportedEncodingException, MessagingException {
        
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("This email already exists");
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            String randomCode = RandomString.generateRandomString(64);
            user.setVerificationCode(randomCode);
            user.setEnabled(false);

            UserResponseDTO userResponse = new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword());

            mailService.sendVerificationEmail(user);
            this.userRepository.save(user);
            return userResponse;
    }
}

    public boolean verify(String verificationCode){

        User user = userRepository.findByVerificationCode(verificationCode);

        if(user == null || user.isEnabled()){
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);

            return true;
        }
    }
}
