package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;

import java.util.Set;

@Data
public class AuthInputDto {
    private String username;
    private String password;
    private Set<String> roleNameSet;
}
