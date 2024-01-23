package org.algorhythm.todo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager(){
        UserDetails user1 = addUser("Sunandan", "ph");
        UserDetails user2 = addUser("Admin", "ph");
        return new InMemoryUserDetailsManager(user1, user2);
    }


    public UserDetails addUser(String username, String password){
        UserDetails user = User
                .builder()
                .passwordEncoder(input -> passwordEncoder().encode(input))
                .username(username)
                .password(password)
                .roles("USER")
                .build();
        return user;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain createFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(req -> req.anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults());
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }

}
