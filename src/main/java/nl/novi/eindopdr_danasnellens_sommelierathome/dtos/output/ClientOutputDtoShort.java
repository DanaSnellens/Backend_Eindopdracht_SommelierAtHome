package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;

import java.util.Set;

@Data
public class ClientOutputDtoShort {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Role> roleSet;
    //membership??



    //relaties
    private Set<Long> wineAdviceRequestIdOutputDtoSet;
    //TODO Is onderstaande nodig? Want automatisch gekoppeld aan request
    //private Set<Long> wineAdviceIdOutputDtoSet;

}
