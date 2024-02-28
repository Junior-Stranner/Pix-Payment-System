package br.com.jujubaprojects.paymentsystem.dto;

import br.com.jujubaprojects.paymentsystem.Entity.User;

public record UserRequest(String name , String email, String password) {
    

    public User toModel(){
        return new User(name,email,password);
    }
}
