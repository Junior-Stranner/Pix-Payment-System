package br.com.jujubaprojects.paymentsystem.dto;

import br.com.jujubaprojects.paymentsystem.Entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequestDTO(
     @NotBlank()
     String name ,
     @Email(message = "formato do e-mail está invalido", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
     String email, 
     @NotBlank
     @Size(min = 6, max = 6, message = "A ssenha deve conter no minimo 6 caracteres e no máximo 6 !")
     String password,

     String role) {
    
    

    public User toModel(){
        return new User(name,email,password,role);
    }
}
