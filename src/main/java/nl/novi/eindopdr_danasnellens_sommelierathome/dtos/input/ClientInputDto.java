package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Membership;

@Data
public class ClientInputDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profilePictureUrl;
    private Membership membership;

    //    private Set<RoleOutputDto> roleOutputDtoSet;
}
