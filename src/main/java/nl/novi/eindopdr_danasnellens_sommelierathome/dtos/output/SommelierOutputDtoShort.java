package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;

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
    private Set<Long> wineAdviceIdOutputDtoSet;
    private Set<Long> wineAdviceRequestIdOutputDtoSet;

}
