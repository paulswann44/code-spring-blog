package com.codeup.codeupspringblog;

import com.codeup.codeupspringblog.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity

@Configuration
public class SecurityConfiguration {

        private UserDetailsLoader usersLoader;


        public SecurityConfiguration(UserDetailsLoader usersLoader) {
            this.usersLoader = usersLoader;
        }

        @Bean
        //This hashes and decodes our password
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        //manage our AuthenticationManager and checks against the database
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean

        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    /* Login configuration */
                    //Lets us use this login page and if successful it lets ut go to the ads page
                    .formLogin()
                    .loginPage("/login")

                    //CHECK WITH JAY ABOUT BOOKS
//                    .defaultSuccessUrl("/ads")
                    .defaultSuccessUrl("/books")
                    .permitAll()


                    /* Logout configuration */
                    .and()
                    .logout()
                    //redirect to home.  As of now, this is broken due to Spring Boot update
                    .logoutSuccessUrl("/")



                    /* Pages that can be viewed without having to log in */
                    .and()
                    .authorizeHttpRequests()

                    .requestMatchers("/", "/books", "/sign-up")
                    .permitAll()
                    /* Pages that require authentication */
                    .and()
                    .authorizeHttpRequests()
                    .requestMatchers(
                            "/books/create", // only authenticated users can create ads
                            "/books/{id}/edit", // only authenticated users can edit ads
                            "/posts/create",
                            "/posts/**" //"**" anything after post is required to authenticate
                    )
                    .authenticated()
            ;
            return http.build();
        }

    }


