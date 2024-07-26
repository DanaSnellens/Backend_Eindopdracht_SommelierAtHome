package nl.novi.eindopdr_danasnellens_sommelierathome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() {
        };
    }

    //Echter usernames en passwords toevoegen (+ roles aanpassen???)
    @Bean
    public authManager(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
                UserDetails user1 = User.withUsername("user1")
                        .password(passwordEncoder()
                                .encode("user1"))
                        .roles("USER")
                        .build();

                manager.createUser(user1);
// meerdere users creeeren 1:10 h huiswerkklas 16
                return manager;

        };
    }

    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.GET, "/wines").permitAll()
                                .requestMatchers(HttpMethod.GET, "/wines/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/wines").hasRole("ADMIN")

                                //ANDERE CRUD REQUESTS EN USERS TOEVOEGEN
                                .anyRequest().denyAll()
                ).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
}
