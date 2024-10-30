package nl.novi.eindopdr_danasnellens_sommelierathome.repositories;

import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WineAdviceRepository extends JpaRepository<WineAdvice, Long> {
    Optional<WineAdvice> findWineAdviceById(Long id);
}
