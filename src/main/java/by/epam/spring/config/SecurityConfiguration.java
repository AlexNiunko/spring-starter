package by.epam.spring.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @SneakyThrows
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http.csrf(csrf -> csrf.disable())
                .formLogin(form -> form.loginPage("/login")
                        .defaultSuccessUrl("/users")
                        .permitAll())
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .build();
    }

}
