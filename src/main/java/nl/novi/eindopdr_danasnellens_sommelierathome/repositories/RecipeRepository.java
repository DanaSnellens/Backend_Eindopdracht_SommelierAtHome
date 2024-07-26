package nl.novi.eindopdr_danasnellens_sommelierathome.repositories;

import nl.novi.eindopdr_danasnellens_sommelierathome.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
