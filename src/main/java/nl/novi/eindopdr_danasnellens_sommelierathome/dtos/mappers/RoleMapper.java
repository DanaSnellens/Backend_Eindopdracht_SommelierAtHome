package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.RoleInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RoleOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.UserOutputDtoShort;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.UserMapper.userFromModelToOutputDtoShort;

public class RoleMapper {
    public static Role roleInputToModel(RoleInputDto roleInputDto) {
        Role role = new Role();
        role.setRoleName(roleInputDto.getRoleName());

        return role;
    }

    public static RoleOutputDto roleModelToOutput (Role role) {
        RoleOutputDto roleOutputDto = new RoleOutputDto();
        roleOutputDto.setId(role.getId());
        roleOutputDto.setRoleName(role.getRoleName());

        if (role.getUserSet() != null) {
            Set<Long> userIdSet = new HashSet<>();

            for (User user : role.getUserSet()) {
                userIdSet.add(user.getId());
            }
            roleOutputDto.setUserIdSet(userIdSet);
        }

        return roleOutputDto;
    }

    public static Set<RoleOutputDto> roleModelSetToOutputSet(Set<Role> roleSet) {
        Set<RoleOutputDto> roleOutputDtoSet = new HashSet<>();

        for (Role r : roleSet) {
            roleOutputDtoSet.add(roleModelToOutput(r));
        }
        return roleOutputDtoSet;
    }
}
