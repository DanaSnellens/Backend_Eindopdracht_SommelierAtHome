package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;

import java.util.Set;

@Data
public class UserOutputDtoShort {
    private Long id;
    private String username;
    //TODO Of hier ook alleen id of naam?
    private Set<RoleOutputDto> roleOutputDtoSet;
}
