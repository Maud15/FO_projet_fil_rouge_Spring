package com.m2i.calendar.security;

import com.m2i.calendar.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // soit on crée une class qui implémente l'interface UserDetailsService et on peut l'injecter avec autowired,
    // soit on crée l'implémentation directement ici
    @Autowired
    private UserDetailsService userDetailsService;
    /*private UserDetailsService userDetailsServiceImpl = new UserDetailsService() {
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return null;
        }
    };*/

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Cette annotation permet de définir la méthode comme un bean et donc de permettre d'injecter ce SecurityFilterChain retourné par la méthode
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //on désactive le CSRF = Cross Site Request Forgery https://fr.wikipedia.org/wiki/Cross-site_request_forgery
        http.cors()
                .and().csrf().disable()
                //on va gérer nous meme les droits et donc l'affichage dépendant du statut et des droits de l'utilisateur, donc on désactive le STATEFULL
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //maintenant on s'occcupe des requètes
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                );

        http.addFilterBefore(authenticationJwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    AuthTokenFilter authenticationJwtFilter() {
        return new AuthTokenFilter();
    }

}
