package nl.novi.eindopdr_danasnellens_sommelierathome.repositories;

import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findClientByUsername(String clientUsername);
    //TODO Verwijderen als niet gebruikt
    boolean existsByUsername(String clientUserName);
    void deleteByUsername(String clientUsername);




}
