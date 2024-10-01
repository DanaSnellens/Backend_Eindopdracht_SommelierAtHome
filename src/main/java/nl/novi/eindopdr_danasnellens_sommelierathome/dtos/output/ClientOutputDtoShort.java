package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;

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
    private Set<Long> wineAdviceIdOutputDtoSet;

}
