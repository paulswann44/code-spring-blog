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

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
// allow security for application

    // Fetch user from DB checks against DB and assigns roles
    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }


    // Hash passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
                /* Login configuration */
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/ads") // user's home page, it can be any URL
                .permitAll() // Anyone can go to the login page


                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/") // append a query string value
                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/", "/ads", "/posts","/sign-up") // anyone can see the home and the ads pages
                .permitAll()
                /* Pages that require authentication */
                .and()
                .authorizeHttpRequests()

                //
                .requestMatchers(
                        "/posts/**", // only authenticated users can create ads
                        "/ads/**", // only authenticated users can edit ads
                        "/ads/create"
                )
                // These mappings need to be authenticated
                .authenticated();
        return http.build();
    }
}