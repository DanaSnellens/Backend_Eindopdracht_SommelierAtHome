package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;

import java.util.Set;
@Data
public class AddRolesInputDto {
    private Set<Long> roleIdSet;
}
