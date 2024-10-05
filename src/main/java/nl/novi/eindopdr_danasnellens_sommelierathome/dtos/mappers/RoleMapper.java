package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.RoleInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RoleOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.UserOutputDtoShort;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.User;

import java.util.HashSet;
import java.util.Set;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.UserMapper.userFromModelToOutputDtoShort;

public class RoleMapper {
    public static Role roleFromInputDtoToModel(RoleInputDto roleInputDto) {
        Role role = new Role();
        role.setRoleName(roleInputDto.getRoleName());

        return role;
    }

    public static RoleOutputDto roleFromModelToOutputDto(Role role) {
        RoleOutputDto roleOutputDto = new RoleOutputDto();
        roleOutputDto.setId(role.getId());
        roleOutputDto.setRoleName(role.getRoleName());

        if (role.getUserSet() != null) {
            Set<UserOutputDtoShort> userOutputDtoShortSet = new HashSet<>();

            Set<User> userSet = role.getUserSet();
            for (User user : userSet) {
                userOutputDtoShortSet.add(userFromModelToOutputDtoShort(user));
            }
            roleOutputDto.setUserOutputDtoShortSet(userOutputDtoShortSet);
        }

        return roleOutputDto;
    }
}
