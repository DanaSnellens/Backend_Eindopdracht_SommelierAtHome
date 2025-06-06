package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Setter;

import java.util.Set;

@Data
public class AuthInputDto {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;
    private Set<String> roleNameSet;
}
