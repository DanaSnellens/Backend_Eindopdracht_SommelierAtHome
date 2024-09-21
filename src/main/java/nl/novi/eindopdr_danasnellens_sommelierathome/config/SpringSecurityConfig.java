/*
package nl.novi.eindopdr_danasnellens_sommelierathome.config;

import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // TODO Dit vervangen naar JWT
        return new BCryptPasswordEncoder() {
        };
    }

    //Echte usernames en passwords toevoegen (+ roles aanpassen???)
    //Zie ook WineController.java Postmapping
    @Bean
    public UserDetailsService authManager(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager man = new InMemoryUserDetailsManager();

        UserDetails u1 = User.withUsername("user1")
                .password(passwordEncoder
                        .encode("user1"))
                .roles("USER")
                .build();
        man.createUser(u1);

        UserDetails u2 = User.withUsername("user2")
                .password(passwordEncoder
                        .encode("user2"))
                .roles("ADMIN")
                .build();
        man.createUser(u2);

        return man;
    }

    @Bean
    protected SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.GET, "/wines").permitAll()
                                .requestMatchers(HttpMethod.GET, "/wines/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/wines").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/wines/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/wines/{id}").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/recipes").permitAll()
                                .requestMatchers(HttpMethod.GET, "/recipes/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/recipes").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/recipes/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/recipes/{id}").hasRole("ADMIN")

                                //TODO Klopt dit?? authenticated? Sommelier moet alles in kunnen zien, client moet alleen zijn eigen wineAdvice kunnen zien
                                .requestMatchers(HttpMethod.GET, "/wineadvices").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/wineadvices/{id}").authenticated()
                                .requestMatchers(HttpMethod.POST, "/wineadvices").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/wineadvices/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/wineadvices/{id}").hasRole("ADMIN")

                                //TODO Klopt dit?? authenticated? Sommelier moet alles in kunnen zien, client moet alleen zijn eigen wineAdviceRequest kunnen zien
                                .requestMatchers(HttpMethod.GET, "/wineadvicerequests").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/wineadvicerequests/{id}").authenticated()
                                .requestMatchers(HttpMethod.POST, "/wineadvicerequests").hasRole("CLIENT")
                                .requestMatchers(HttpMethod.PUT, "/wineadvicerequests/{id}").hasRole("CLIENT")
                                .requestMatchers(HttpMethod.DELETE, "/wineadvicerequests/{id}").hasRole("CLIENT")

                                .requestMatchers(HttpMethod.GET, "/clients").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/clients/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/clients").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.PUT, "/clients/**").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.DELETE, "/clients/**").hasAnyRole("ADMIN", "CLIENT")

                                .requestMatchers(HttpMethod.GET, "/sommeliers").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.GET, "/sommeliers/**").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.POST, "/sommeliers").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/sommeliers/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/sommeliers/**").hasRole("ADMIN")

                                .anyRequest().denyAll()

                                .requestMatchers("/authenticated").authenticated()
                                .requestMatchers("authenticate").permitAll()
                                .anyRequest().denyAll()
                ).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
*/
