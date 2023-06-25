package ua.goit.vic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecuritySet {
    @Bean
    public UserDetailsService uds (PasswordEncoder passEnc){
        UserDetails uD = User.withUsername("user")
                .password(passEnc.encode("default"))
                .roles("user")
                .build();
        return new InMemoryUserDetailsManager(uD);
    }

    @Bean
    public PasswordEncoder passEnc(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain sfc(HttpSecurity security) throws Exception{
        return security.csrf().disable()
                .authorizeRequests()
                .antMatchers("/hello", "/login").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                .and()
                .build();
    }
}