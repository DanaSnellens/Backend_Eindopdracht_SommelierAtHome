package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Wine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Data
public class RecipeOutputDto {
    private Long id;
    private String recipeName;
    private String course;
    private String mainIngredient;
    private String otherIngredients;
    private Integer servings;
    private Integer preparationTime;
    private String winePairing;
    private String imageLink;
    private String imageAlt;
    private String preparationShortDescription;
    private String preparationLongDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeOutputDto that = (RecipeOutputDto) o;
        return Objects.equals(id, that.id) && Objects.equals(recipeName, that.recipeName) && Objects.equals(course, that.course) && Objects.equals(mainIngredient, that.mainIngredient) && Objects.equals(otherIngredients, that.otherIngredients) && Objects.equals(servings, that.servings) && Objects.equals(preparationTime, that.preparationTime) && Objects.equals(winePairing, that.winePairing) && Objects.equals(imageLink, that.imageLink) && Objects.equals(imageAlt, that.imageAlt) && Objects.equals(preparationShortDescription, that.preparationShortDescription) && Objects.equals(preparationLongDescription, that.preparationLongDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipeName, course, mainIngredient, otherIngredients, servings, preparationTime, winePairing, imageLink, imageAlt, preparationShortDescription, preparationLongDescription);
    }

    //TODO onderstaande verwijderen?
/*    private Set<Long> wineIdSet;*/
    private Map<Long, String> wineIdSet = new HashMap<>();
}
