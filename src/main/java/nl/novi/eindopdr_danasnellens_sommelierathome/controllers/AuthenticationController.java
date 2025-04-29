package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.AuthInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.AuthOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.utils.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
//TODO Register toegevoegd, maar werkt nog niet. Moet nog getest worden
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody @Validated AuthInputDto authInputDto) {
        try {
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(authInputDto.getUsername(), authInputDto.getPassword());
            Authentication auth = authenticationManager.authenticate(upToken);
        }
        catch (AuthenticationException authException) {
            return new ResponseEntity<>(authException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok().body(new AuthOutputDto("User registered"));
    }

//TODO getMapping nog checken Authentication weghalen als parameter?
    @GetMapping(value = "/authenticated")
    public ResponseEntity<Object> authenticated(//TODO WEG?? /*Authentication authentication, */
                                                 Principal principal) {
        return ResponseEntity.ok().body(principal);
    }
//TODO Onderstaande overgenomen uit Les17-jwt, maar is dit volledig? Tini heeft een andere manier. Sowieso de exception nog specifieceren + message
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> signIn(@RequestBody AuthInputDto authInputDto) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(authInputDto.getUsername(), authInputDto.getPassword());
        try {
            Authentication auth = authenticationManager.authenticate(upToken);

            UserDetails ud = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(ud);

/*            return ResponseEntity.ok(new AuthOutputDto(token));*/

                        return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .body(Collections.singletonMap("token", token));
        }
        catch (AuthenticationException authException) {
            return new ResponseEntity<>(authException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
