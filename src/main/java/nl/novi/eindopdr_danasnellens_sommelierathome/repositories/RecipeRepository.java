package nl.novi.eindopdr_danasnellens_sommelierathome.repositories;

import jakarta.persistence.Entity;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Recipe;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findById(Long id);
    boolean existsByRecipeName(String recipeName);

}

