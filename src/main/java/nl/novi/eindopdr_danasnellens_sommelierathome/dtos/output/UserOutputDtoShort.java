package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;

import java.util.Set;

@Data
public class UserOutputDtoShort {
    private Long id;
    private String userName;
    //TODO Of hier ook alleen id of naam?
    private Set<RoleOutputDto> roleOutputDtoSet;
}
