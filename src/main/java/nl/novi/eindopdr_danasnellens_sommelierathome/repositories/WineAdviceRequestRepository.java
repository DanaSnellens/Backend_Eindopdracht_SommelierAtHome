package nl.novi.eindopdr_danasnellens_sommelierathome.repositories;

import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WineAdviceRequestRepository extends JpaRepository<WineAdviceRequest, Long> {
    //TODO Verwijderen als niet gebruikt
    Optional<WineAdvice> findWineAdviceById(Long id);

}
