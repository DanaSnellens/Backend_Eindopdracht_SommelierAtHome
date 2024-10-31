package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.RecipeInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RecipeOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Recipe;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Wine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecipeMapper {
    public static Recipe recipeInputToModel(RecipeInputDto recipeInputDto) {
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

    public static Recipe updateRecipeMapper(Recipe savedRecipe, RecipeInputDto updatedRecipeInputDto) {
        savedRecipe.setRecipeName(updatedRecipeInputDto.getRecipeName());
        savedRecipe.setCourse(updatedRecipeInputDto.getCourse());
        savedRecipe.setMainIngredient(updatedRecipeInputDto.getMainIngredient());
        savedRecipe.setOtherIngredients(updatedRecipeInputDto.getOtherIngredients());
        savedRecipe.setServings(updatedRecipeInputDto.getServings());
        savedRecipe.setPreparationTime(updatedRecipeInputDto.getPreparationTime());
        savedRecipe.setWinePairing(updatedRecipeInputDto.getWinePairing());
        savedRecipe.setImageLink(updatedRecipeInputDto.getImageLink());
        savedRecipe.setImageAlt(updatedRecipeInputDto.getImageAlt());
        savedRecipe.setPreparationShortDescription(updatedRecipeInputDto.getPreparationShortDescription());
        savedRecipe.setPreparationLongDescription(updatedRecipeInputDto.getPreparationLongDescription());

        if (savedRecipe.getWineSet() != null) {
            savedRecipe.setWineSet(null);
        }
        return savedRecipe;
    }

    public static RecipeOutputDto recipeModelToOutput(Recipe recipe) {
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

        if (recipe.getWineSet() != null) {
            Set<Long> wineIdSet = new HashSet<>();

            for (Wine wine : recipe.getWineSet()) {
                wineIdSet.add(wine.getId());
            }
            recipeOutputDto.setWineIdSet(wineIdSet);
        }
        return recipeOutputDto;
    }

    public static List<RecipeOutputDto> recipeModelListToOutputList(List<Recipe> recipeList) {
        List<RecipeOutputDto> recipeOutputDtoList = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            recipeOutputDtoList.add(recipeModelToOutput(recipe));
        }
        return recipeOutputDtoList;
    }
}
