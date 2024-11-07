package nl.novi.eindopdr_danasnellens_sommelierathome.repositories;

import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findRoleByRoleName(String roleName);
    //TODO Verwijderen als niet gebruikt
    boolean existsByRoleName(String roleName);

}
