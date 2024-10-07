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

    private Set<Long> WineAdviceRequestIdOutputDtoSet;
    private Set<Long> wineAdviceIdOutputDtoSet;
}
