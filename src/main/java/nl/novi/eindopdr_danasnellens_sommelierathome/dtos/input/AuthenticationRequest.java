package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;

import java.util.Set;

@Data
public class AuthenticationRequest {
    private String userName;
    private String email;
    private String password;
    private Set<Role> roleSet;
}
