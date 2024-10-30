package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Wine;

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

    //relaties
    private Set<Long> wineIdSet;
}
