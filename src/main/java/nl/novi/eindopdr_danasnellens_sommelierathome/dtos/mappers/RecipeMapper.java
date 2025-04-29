package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.RecipeInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RecipeOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Recipe;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Wine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class RecipeMapper {
    private static final Logger logger = LoggerFactory.getLogger(RecipeMapper.class);

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

    //TODO wineIdSet is nu nog leeg, moet nog gevuld worden. Maar hoe?
        //TODO naam aanpassen van Set naar Map?
        if (recipe.getWineSet() != null) {
            Map<Long, String> wineIdSet = new HashMap<>();
            for (Wine wine : recipe.getWineSet()) {
                wineIdSet.put(wine.getId(), wine.getWineName());
                recipeOutputDto.setWineIdSet(wineIdSet);
            }
        }

        logger.debug("Mapped Recipe to RecipeOutputDto: {}", recipeOutputDto);

/*        Set<Long> wineIdSet = recipe.getWineSet() != null
                ? recipe.getWineSet().stream().map(Wine::getId).collect(Collectors.toSet())
                : new HashSet<>();
        recipeOutputDto.setWineIdSet(wineIdSet);*/

        return recipeOutputDto;
    }

    public static List<RecipeOutputDto> recipeModelListToOutputList(List<Recipe> recipeList) {
        List<RecipeOutputDto> recipeOutputDtoList = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            recipeOutputDtoList.add(recipeModelToOutput(recipe))  ;
        }
        return recipeOutputDtoList;
    }
}
