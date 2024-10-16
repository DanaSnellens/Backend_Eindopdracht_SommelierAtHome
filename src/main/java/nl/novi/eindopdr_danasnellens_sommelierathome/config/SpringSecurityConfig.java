package nl.novi.eindopdr_danasnellens_sommelierathome.config;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.filter.JwtRequestFilter;
import nl.novi.eindopdr_danasnellens_sommelierathome.services.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Data
public class SpringSecurityConfig {

    //TODO Klopt onderstaande? Tm @Bean? Nav Tini, maar niet hetzelfde als lessen
    private final JwtRequestFilter jwtRequestFilter;
    private final MyUserDetailService myUserDetailService;
    private final static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    public PasswordEncoder passwordEncoder() {
        // TODO Dit vervangen naar JWT
        return new BCryptPasswordEncoder() {
        };
    }

    //TODO Klopt dit zo? is nu een samenvoeging van verschillende lessen/methodes. Echte usernames en passwords toevoegen (+ roles aanpassen???)Zie ook WineController.java Postmapping + Mss Exception nog specificieren?
    @Bean
    public AuthenticationManager authManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(myUserDetailService)
                .passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    protected SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
 /*               .cors().and()*/
                .authorizeRequests(auth ->
                        auth
                                .requestMatchers("/**").permitAll() //TODO dit weghalen
/*                                .requestMatchers(HttpMethod.GET, "/public/**").permitAll()
                                .requestMatchers("authenticate").permitAll()
                                .requestMatchers("/authenticated").authenticated()
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
                                .requestMatchers(HttpMethod.PUT, "/wineadvicerequests/{id}").hasAnyRole("CLIENT", "SOMMELIER")
                                .requestMatchers(HttpMethod.DELETE, "/wineadvicerequests/{id}").hasAnyRole("CLIENT", "SOMMELIER")

                                .requestMatchers(HttpMethod.GET, "/roles").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/clients").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/clients/**").authenticated()
                                //TODO Klopt dit?? authenticated? Sommelier moet alles in kunnen zien, client moet alleen zijn eigen account kunnen zien
                                .requestMatchers(HttpMethod.POST, "/clients").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.PUT, "/clients/**").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.DELETE, "/clients/**").hasAnyRole("ADMIN", "CLIENT")

                                .requestMatchers(HttpMethod.GET, "/sommeliers").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.GET, "/sommeliers/**").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.POST, "/sommeliers").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/sommeliers/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/sommeliers/**").hasRole("ADMIN")



                                .anyRequest().denyAll()*/
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

                //TODO Of zoals in voorbeeld les17jwt: .csrf(csrf. -> csrf.disable())
        //                                .addFilterBefore(new JwtRequestFilter(jwtRequestFilter, myUserDetailService()), UsernamePasswordAuthenticationFilter.class;

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager (HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(myUserDetailService)
                .passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }
}
