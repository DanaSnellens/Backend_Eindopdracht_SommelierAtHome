package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.AuthenticationRequest;
import nl.novi.eindopdr_danasnellens_sommelierathome.utils.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
//TODO getMapping nog checken
    @GetMapping(value = "/authenticated")
    public ResponseEntity<Object> authenticated(Authentication authentication, Principal principal) {
        return ResponseEntity.ok().body(principal);
    }
//TODO Onderstaande overgenomen uit Les17-jwt, maar is dit volledig? Tini heeft een andere manier. Sowieso de exception nog specifieceren + message
    @PostMapping(value = "/authenticate")
    public ResponseEntity<Object> signIn(@RequestBody AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword());
        try {
            Authentication auth = authenticationManager.authenticate(upToken);

            UserDetails ud = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(ud);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .body("Token generated");
        }
        catch (AuthenticationException authException) {
            return new ResponseEntity<>(authException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
