package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.AddWinesInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.RecipeInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RecipeOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.exceptions.EntityAlreadyExistsException;
import nl.novi.eindopdr_danasnellens_sommelierathome.exceptions.RecordNotFoundException;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Recipe;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Wine;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.RecipeRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.RecipeMapper.*;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final WineRepository wineRepository;
    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    public RecipeService(RecipeRepository recipeRepository, WineRepository wineRepository) {
        this.recipeRepository = recipeRepository;
        this.wineRepository = wineRepository;
    }

    public List<RecipeOutputDto> getAllRecipes() {
        List<Recipe> recipeList = recipeRepository.findAll();
        return recipeModelListToOutputList(recipeList);
    }
    public RecipeOutputDto getRecipeById(Long id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            return recipeModelToOutput(optionalRecipe.get());
        } else throw new RecordNotFoundException("No recipe found with id: " + id);
    }

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
    public RecipeOutputDto updateRecipeById(Long id, RecipeInputDto updatedRecipeInputDto) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            Recipe existingRecipe = optionalRecipe.get();
            updateRecipeMapper(existingRecipe, updatedRecipeInputDto);
            Recipe updatedRecipe = recipeRepository.save(existingRecipe);
            return recipeModelToOutput(updatedRecipe);
        }
        else throw new RecordNotFoundException("No recipe found with id: " + id);
    }

    public void deleteRecipeById(Long id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            recipeRepository.deleteById(id);
        }
        else throw new RecordNotFoundException("No recipe found with id: " + id);
    }

    public RecipeOutputDto addWinesToRecipe(Long recipeId, AddWinesInputDto addWinesInputDto) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            Set<Long> wineIdSet = addWinesInputDto.getWineIdSet();
            Set<Wine> wineSet = new HashSet<>();
            for (Long id : wineIdSet) {
                Optional<Wine> optionalWine = wineRepository.findById(id);
                if (optionalWine.isPresent()) {
                    wineSet.add(optionalWine.get());
                } else {
                    throw new RecordNotFoundException("No wine with " + id + " could not be found");
                }
            }
            recipe.getWineSet().addAll(wineSet);
            recipeRepository.save(recipe);
            return recipeModelToOutput(recipe);
        } else {
            throw new RecordNotFoundException("No recipe found with id: " + recipeId);
        }
    }
}
