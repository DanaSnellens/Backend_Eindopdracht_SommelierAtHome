package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;
import nl.novi.eindopdr_danasnellens_sommelierathome.utils.Membership;

import java.util.Set;

@Data
public class ClientInputDto {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    //TODO moet password wel public zijn? Of uberhaupt in de input dto staan?
    private String password;
    private String profilePictureUrl;
    private Membership membership;

    //relaties --> Niet nodig in input want client maakt deze niet zelf aan

}
