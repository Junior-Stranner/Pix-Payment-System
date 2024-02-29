package br.com.jujubaprojects.paymentsystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.jujubaprojects.paymentsystem.Entity.User;

public interface UserRepository extends JpaRepository<User , Long>{
    

    UserDetails findByEmail(String email);
    User findByVerificationCode(String verificationCode);

}
