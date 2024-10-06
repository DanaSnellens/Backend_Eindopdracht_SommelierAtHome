package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;

import java.util.Set;

@Data
public class SommelierOutputDtoShort {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePictureUrl;
    private Set<Role> roleSet;

    //relaties
    //TODO of moeten dit output dto sets van id's zijn? + moet WAR hier ook gekoppeld zijn of gebeurt dat automatisch via WA koppeling met WAR?
//    private Set<Long> wineAdviceRequestIdSet;
    private Set<Long> wineAdviceIdSet;

}
