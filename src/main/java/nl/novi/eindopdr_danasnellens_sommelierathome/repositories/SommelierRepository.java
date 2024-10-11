package nl.novi.eindopdr_danasnellens_sommelierathome.repositories;

import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SommelierRepository extends JpaRepository<Sommelier, String> {
    Optional<Sommelier> findSommelierByUserName(String userName);

    void deleteSommelierByUserName(String userName);
}
