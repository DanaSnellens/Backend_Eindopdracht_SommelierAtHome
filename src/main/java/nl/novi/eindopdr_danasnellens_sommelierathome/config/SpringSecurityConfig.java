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
    public /*static*/ PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
               .cors().and()

                .authorizeRequests(auth -> auth
/*                        //TODO Onderstaande inkorten
                                .requestMatchers("/**").permitAll() //TODO dit weghalen*/
                                .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                                .requestMatchers("/authenticated").authenticated()

                                //TODO POST, put en delete request nog toevoegen aan roles
                                .requestMatchers("/roles").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/clients").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/clients/{username}").hasAnyRole("ADMIN", "ROLE_CLIENT")
                                .requestMatchers(HttpMethod.POST, "/clients").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/clients/**").hasRole("CLIENT")
                                .requestMatchers(HttpMethod.DELETE, "/clients/**").hasAnyRole("ADMIN", "ROLE_CLIENT")

                                .requestMatchers(HttpMethod.GET, "/sommeliers").permitAll()
                                .requestMatchers(HttpMethod.GET, "/sommeliers/**").permitAll()

                                 //TODO POST aanpassen naar alleen admin

                                .requestMatchers(HttpMethod.POST, "/sommeliers").hasAnyRole("ROLE_ADMIN", "ROLE_CLIENT")
                                .requestMatchers(HttpMethod.PUT, "/sommeliers/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/sommeliers/**").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/wines").permitAll()
                                .requestMatchers(HttpMethod.GET, "/wines/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/wines").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/wines/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/wines/**").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/recipes").permitAll()
                                .requestMatchers(HttpMethod.GET, "/recipes/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/recipes").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/recipes/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/recipes/**/addwines").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/recipes/**").hasRole("ADMIN")

                                //TODO Klopt dit?? authenticated? Sommelier moet alles in kunnen zien, client moet alleen zijn eigen wineAdvice kunnen zien
                                .requestMatchers(HttpMethod.GET, "/wineadvices").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/wineadvices/**").hasAnyRole("CLIENT", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/wineadvices").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/wineadvices/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/wineadvices/**/addwines").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/wineadvices/**").hasRole("ADMIN")

                                //TODO Klopt dit?? authenticated? Sommelier moet alles in kunnen zien, client moet alleen zijn eigen wineAdviceRequest kunnen zien
                                .requestMatchers(HttpMethod.GET, "/wineadvicerequests").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/wineadvicerequests/**").hasAnyRole("CLIENT", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/wineadvicerequests").hasRole("CLIENT")
                                .requestMatchers(HttpMethod.PUT, "/wineadvicerequests/**").hasAnyRole("CLIENT", "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/wineadvicerequests/**/sommelier").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/wineadvicerequests/**").hasAnyRole("ROLE_CLIENT", "ADMIN")

                                .anyRequest().denyAll()

                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

                //TODO Of zoals in voorbeeld les17jwt: .csrf(csrf. -> csrf.disable())
        //                                .addFilterBefore(new JwtRequestFilter(jwtRequestFilter, myUserDetailService()), UsernamePasswordAuthenticationFilter.class;

        return http.build();
    }
}
