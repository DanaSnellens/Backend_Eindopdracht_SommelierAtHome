package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;

public class RecipeInputDto {
    public String recipeName;
    public String course;
    public String mainIngredient;
    public String otherIngredients;
    public Integer servings;
    public Integer preparationTime;
    public String winePairing;
    public String imageLink;
    public String imageAlt;
    public String preparationShortDescription;
    public String preparationLongDescription;
}
