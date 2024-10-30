package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RoleOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Membership;

import java.util.Set;

@Data
public class ClientInputDto {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    private String password;
    private String profilePictureUrl;
    private Membership membership;

    private Set<Long> roleIdSet;
}
