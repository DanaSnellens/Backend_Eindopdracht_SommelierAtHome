package nl.novi.eindopdr_danasnellens_sommelierathome.repositories;

import nl.novi.eindopdr_danasnellens_sommelierathome.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
