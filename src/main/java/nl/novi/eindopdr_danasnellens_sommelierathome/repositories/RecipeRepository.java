package nl.novi.eindopdr_danasnellens_sommelierathome.repositories;

import nl.novi.eindopdr_danasnellens_sommelierathome.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
/*
    Optional<Recipe> findRecipeByRecipeId(String recipeId);
*/

    boolean existsByRecipeName(String recipeName);
    Optional<Recipe> findRecipeByRecipeName(String recipeName);
}

