package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.RoleInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RoleOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.RoleMapper.roleModelSetToOutputSet;

@Service
@Data
public class RoleService {
    private final RoleRepository roleRepository;

    public Set<RoleOutputDto> getRoles() {
        //TODO Dit nog aanpassen? (naar list?)
        Set<Role> roleSet = (Set<Role>) roleRepository.findAll();
        return roleModelSetToOutputSet(roleSet);
    }

/*    public RoleInputDto createRole(RoleInputDto roleInputDto) {
        Optional<Role> optionalRole = roleRepository.findRoleByRoleName(roleInputDto.getRoleName());
        if (optionalRole.isEmpty()) {
            Role role = roleRepository.save(roleInputDtoToModel(roleInputDto));
        }
        Role role = roleRepository.save(roleInputDtoToModel(roleInputDto));
        return roleModelToInput(role);

    }*/
}
