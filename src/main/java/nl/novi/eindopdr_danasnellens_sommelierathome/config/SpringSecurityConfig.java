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
public class SpringSecurityConfig {

    //TODO Klopt onderstaande? Tm @Bean? Nav Tini, maar niet hetzelfde als lessen
    private final JwtRequestFilter jwtRequestFilter;
    private final MyUserDetailService myUserDetailService;

    public SpringSecurityConfig(JwtRequestFilter jwtRequestFilter, MyUserDetailService myUserDetailService) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.myUserDetailService = myUserDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(myUserDetailService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    protected SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
/*               .cors().and()*/

                .authorizeRequests(auth -> auth
/*                        //TODO Onderstaande inkorten
                                .requestMatchers("/**").permitAll() //TODO dit weghalen*/
                                .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                                .requestMatchers("/authenticated").authenticated()

                                //TODO POST, put en delete request nog toevoegen aan roles
                                .requestMatchers("/roles").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/clients").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/clients/{username}").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.POST, "/clients").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/clients/{username}").hasRole("CLIENT")
                                .requestMatchers(HttpMethod.DELETE, "/clients/{username}").hasAnyRole("ADMIN", "CLIENT") //TODO OF AUTHENTICATED?

                                .requestMatchers(HttpMethod.GET, "/sommeliers").permitAll()
                                .requestMatchers(HttpMethod.GET, "/sommeliers/{username}").permitAll()

                                 //TODO POST aanpassen naar alleen admin

                                .requestMatchers(HttpMethod.POST, "/sommeliers").hasAnyRole("ADMIN", "CLIENT")
                                .requestMatchers(HttpMethod.PUT, "/sommeliers/{username}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/sommeliers/{username}").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/wines").permitAll()
                                .requestMatchers(HttpMethod.GET, "/wines/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/wines").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/wines/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/wines/{id}").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/recipes").permitAll()
                                .requestMatchers(HttpMethod.GET, "/recipes/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/recipes").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/recipes/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/recipes/{id}/addwines").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/recipes/{id}").hasRole("ADMIN")

                                //TODO Klopt dit?? authenticated? Sommelier moet alles in kunnen zien, client moet alleen zijn eigen wineAdvice kunnen zien
                                .requestMatchers(HttpMethod.GET, "/wineadvices").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/wineadvices/{id}").hasAnyRole("CLIENT", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/wineadvices").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/wineadvices/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/wineadvices/{id}/addwines").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/wineadvices/{id}").hasRole("ADMIN")

                                //TODO Klopt dit?? authenticated? Sommelier moet alles in kunnen zien, client moet alleen zijn eigen wineAdviceRequest kunnen zien
                                .requestMatchers(HttpMethod.GET, "/wineadvicerequests").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/wineadvicerequests/{id}").hasAnyRole("CLIENT", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/wineadvicerequests").hasRole("CLIENT")
                                .requestMatchers(HttpMethod.PUT, "/wineadvicerequests/{id}").hasAnyRole("CLIENT", "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/wineadvicerequests/{id}/sommelier").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/wineadvicerequests/{id}").hasAnyRole("CLIENT", "ADMIN")

                                .anyRequest().denyAll()

                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
