package nl.novi.eindopdr_danasnellens_sommelierathome.repositories;

import jakarta.transaction.Transactional;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WineAdviceRequestRepository extends JpaRepository<WineAdviceRequest, Long> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM recipes_wines WHERE wine_id = :wineId", nativeQuery = true)
    void deleteReferencesFromRecipesWines(@Param("wineId") Long wineId);
    //TODO Verwijderen als niet gebruikt
    Optional<WineAdvice> findWineAdviceById(Long id);

}
