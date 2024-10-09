package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RoleOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
