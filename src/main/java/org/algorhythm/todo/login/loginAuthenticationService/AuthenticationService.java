package org.algorhythm.todo.login.loginAuthenticationService;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String username, String password){
        return username.equals("Sunandan") && password.equals("Phalke");
    }
}
