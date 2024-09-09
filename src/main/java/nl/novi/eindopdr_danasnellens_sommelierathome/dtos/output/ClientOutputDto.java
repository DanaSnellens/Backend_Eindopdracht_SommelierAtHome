package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.utils.Membership;

@Data
public class ClientOutputDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    //TODO moet password wel in de output dto staan?
    private String password;
    private String profilePictureUrl;

    private Membership membership;
}
