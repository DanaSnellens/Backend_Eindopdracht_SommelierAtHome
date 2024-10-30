package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.RecipeInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RecipeOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.exceptions.EntityAlreadyExistsException;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Recipe;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.RecipeMapper.*;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeOutputDto> getAllRecipes() {
        List<Recipe> recipeList = recipeRepository.findAll();
        return recipeModelListToOutputList(recipeList);
    }
/*
    public RecipeOutputDto getRecipeById(Long id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            return recipeModelToOutput(optionalRecipe.get());
        } else throw new RuntimeException("No recipe found with id: " + id);
    }*/

    public RecipeOutputDto createRecipe(RecipeInputDto recipeInputDto) {
        //TODO userDetails toevoegen, alleen somm mag aanmaken{
        boolean exists = recipeRepository.existsByRecipeName(recipeInputDto.getRecipeName());
        if (exists) {
            throw new EntityAlreadyExistsException("Recipe with name: " + recipeInputDto.getRecipeName() + " already exists.");
        } else {
            Recipe recipe = recipeInputToModel(recipeInputDto);
            Recipe savedRecipe = recipeRepository.save(recipe);
            return recipeModelToOutput(savedRecipe);
        }
    }

    public RecipeOutputDto updateRecipeById(Long id, RecipeInputDto recipeInputDto) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            Recipe updatedRecipe = recipeRepository.save(recipeInputToModel(recipeInputDto));
            return recipeModelToOutput(updatedRecipe);
        }
        else throw new RuntimeException("No recipe found with id: " + id);
    }

    public void deleteRecipeById(Long id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            recipeRepository.deleteById(id);
        }
        else throw new RuntimeException("No recipe found with id: " + id);
    }
}
