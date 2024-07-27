package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.RecipeInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RecipeOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeMapper {
    public static Recipe recipeFromInputDtoToModel(RecipeInputDto recipeInputDto) {
        Recipe recipe = new Recipe();
        recipe.setRecipeName(recipeInputDto.getRecipeName());
        recipe.setCourse(recipeInputDto.getCourse());
        recipe.setMainIngredient(recipeInputDto.getMainIngredient());
        recipe.setOtherIngredients(recipeInputDto.getOtherIngredients());
        recipe.setServings(recipeInputDto.getServings());
        recipe.setPreparationTime(recipeInputDto.getPreparationTime());
        recipe.setWinePairing(recipeInputDto.getWinePairing());
        recipe.setImageLink(recipeInputDto.getImageLink());
        recipe.setImageAlt(recipeInputDto.getImageAlt());
        recipe.setPreparationShortDescription(recipeInputDto.getPreparationShortDescription());
        recipe.setPreparationLongDescription(recipeInputDto.getPreparationLongDescription());
        return recipe;
    }

    public static RecipeOutputDto recipeFromModelToOutputDto(Recipe recipe) {
        RecipeOutputDto recipeOutputDto = new RecipeOutputDto();
        recipeOutputDto.setId(recipe.getId());
        recipeOutputDto.setRecipeName(recipe.getRecipeName());
        recipeOutputDto.setCourse(recipe.getCourse());
        recipeOutputDto.setMainIngredient(recipe.getMainIngredient());
        recipeOutputDto.setOtherIngredients(recipe.getOtherIngredients());
        recipeOutputDto.setServings(recipe.getServings());
        recipeOutputDto.setPreparationTime(recipe.getPreparationTime());
        recipeOutputDto.setWinePairing(recipe.getWinePairing());
        recipeOutputDto.setImageLink(recipe.getImageLink());
        recipeOutputDto.setImageAlt(recipe.getImageAlt());
        recipeOutputDto.setPreparationShortDescription(recipe.getPreparationShortDescription());
        recipeOutputDto.setPreparationLongDescription(recipe.getPreparationLongDescription());
        return recipeOutputDto;
    }

    public static List<RecipeOutputDto> recipeModelListToOutputList(List<Recipe> recipeList) {
        List<RecipeOutputDto> recipeOutputDtoList = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            recipeOutputDtoList.add(recipeFromModelToOutputDto(recipe));
        }
        return recipeOutputDtoList;
    }
}
