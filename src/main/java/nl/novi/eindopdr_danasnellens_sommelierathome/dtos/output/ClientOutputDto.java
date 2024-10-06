package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Membership;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;

import java.util.Set;

@Data
public class ClientOutputDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePictureUrl;
    private Set<Role> roleSet;

    private Membership membership;

    //TODO Of OutputDto hier weg? Dus gewoon WineAdviceRequestIdSet? Zelfde voor overige output DTOs
    private Set<Long> WineAdviceRequestIdOutputDtoSet;

    //TODO Is onderstaande nodig? Want automatisch gekoppeld aan request
    //private Set<Long> wineAdviceIdOutputDtoSet;
}
