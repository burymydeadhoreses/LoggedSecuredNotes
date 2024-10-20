package io.github.burymydeadhoreses.loggedsecurednotes.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((authorize) -> authorize
                    .anyRequest().authenticated()
            )
            .logout(logout -> {
                logout.invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll();
            })
            .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        var manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withDefaultPasswordEncoder()
                .username("burymydeadhoreses")
                .password("qwerty")
                .roles("USER")
                .build());

        return manager;
    }
}