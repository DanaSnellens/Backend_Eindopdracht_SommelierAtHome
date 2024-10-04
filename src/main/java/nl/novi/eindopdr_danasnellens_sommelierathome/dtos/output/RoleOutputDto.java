package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;
import lombok.Setter;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.User;

import java.util.Set;

@Data
public class RoleOutputDto {
    private Long id;
    private String roleName;
    private Set<UserOutputDtoShort> userOutputDtoShortSet;
}
