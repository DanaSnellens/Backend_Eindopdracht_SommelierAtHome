package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Membership;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;

import java.util.Set;

@Data
public class ClientInputDto {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profilePictureUrl;
    private Membership membership;

    //    private Set<RoleOutputDto> roleOutputDtoSet;
}
