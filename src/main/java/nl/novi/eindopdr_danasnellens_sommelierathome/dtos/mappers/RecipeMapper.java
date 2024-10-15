/*package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.RecipeInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RecipeOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Recipe;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Wine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineMapper.wineModelToOutput;

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

        //relaties
        if (recipe.getWineSet() != null) {
            Set<WineOutputDto> wineOutputDtoSet = new HashSet<>();
//TODO Klopt dit? Of moet ik hier ook outputdto gebruiken?
            Set<Wine> WineSet = recipe.getWineSet();
            for (Wine wine : WineSet) {
                wineOutputDtoSet.add(wineModelToOutput(wine));
            }
            recipeOutputDto.setWineOutputDtoSet(wineOutputDtoSet);
        }

        return recipeOutputDto;
    }

    public static List<RecipeOutputDto> recipeModelListToOutputList(List<Recipe> recipeList) {
        List<RecipeOutputDto> recipeOutputDtoList = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            recipeOutputDtoList.add(recipeFromModelToOutputDto(recipe));
        }
        return recipeOutputDtoList;
    }
}*/
