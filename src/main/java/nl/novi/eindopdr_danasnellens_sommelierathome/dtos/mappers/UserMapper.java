package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RoleOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.UserOutputDtoShort;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.User;

import java.util.HashSet;
import java.util.Set;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.RoleMapper.roleFromModelToOutputDto;

public class UserMapper {
    public static UserOutputDtoShort userFromModelToOutputDtoShort (User user) {
        UserOutputDtoShort userOutputDtoShort = new UserOutputDtoShort();
        userOutputDtoShort.setId(user.getId());
        userOutputDtoShort.setUserName(user.getUserName());

        //TODO: Klopt het dat ik het zowel bij user als bij role zo aanpak, of creeert dit een infinite loop?
        if (user.getRoleSet() != null) {
            Set<RoleOutputDto> roleOutputDtoSet = new HashSet<>();

            Set<Role> roleSet = user.getRoleSet();
            for (Role role : roleSet) {
                roleOutputDtoSet.add(roleFromModelToOutputDto(role));
            }
            userOutputDtoShort.setRoleOutputDtoSet(roleOutputDtoSet);
        }

        return userOutputDtoShort;

    }
}
