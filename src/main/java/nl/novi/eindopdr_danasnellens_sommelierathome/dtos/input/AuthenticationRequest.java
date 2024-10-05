package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;

import java.util.Set;

@Data
public class AuthenticationRequest {
    private String userName;
    private String password;
}
