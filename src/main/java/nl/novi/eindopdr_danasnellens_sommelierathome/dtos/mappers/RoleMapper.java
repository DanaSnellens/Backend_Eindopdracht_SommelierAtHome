package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.RoleInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RoleOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


        if (role.getSommeliers() != null) {
            Set<Long> sommelierIdSet = new HashSet<>();

            for (Sommelier sommelier : role.getSommeliers()) {
                sommelierIdSet.add(sommelier.getId());
            }
            roleOutputDto.setSommelierIdSet(sommelierIdSet);
        }

        if (role.getClients() != null) {
            Set<Long> clientIdSet = new HashSet<>();

            for (Client client : role.getClients()) {
                clientIdSet.add(client.getId());
            }
            roleOutputDto.setClientIdSet(clientIdSet);
        }

        return roleOutputDto;
    }

    public static List<RoleOutputDto> roleModelListToOutputList(List<Role> roleList) {
        List<RoleOutputDto> roleOutputDtoList = new ArrayList<>();
        roleList.forEach(role -> roleOutputDtoList.add(roleModelToOutput(role)));
        return roleOutputDtoList;
    }
}
