package nl.novi.eindopdr_danasnellens_sommelierathome.repositories;

import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SommelierRepository extends JpaRepository<Sommelier, Long> {
    Optional<Sommelier> findSommelierByUsername(String username);

    void deleteSommelierByUsername(String username);
}
