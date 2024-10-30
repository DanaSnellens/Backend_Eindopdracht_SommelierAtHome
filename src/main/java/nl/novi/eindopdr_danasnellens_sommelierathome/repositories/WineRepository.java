package nl.novi.eindopdr_danasnellens_sommelierathome.repositories;

import nl.novi.eindopdr_danasnellens_sommelierathome.models.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WineRepository extends JpaRepository<Wine, Long> {
    Optional<Wine> findWineByWineName(String wineName);

    boolean existsWineByWineName(String wineName);
/*    List<Wine> findAllById(Iterable<Long> wineIds);
    Optional<Wine> findWineByWineName(String wineName);*/
}