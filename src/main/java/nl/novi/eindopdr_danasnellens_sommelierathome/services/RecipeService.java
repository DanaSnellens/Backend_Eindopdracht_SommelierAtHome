package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public RecipeOutputDto getRecipeById(Long id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            return recipeFromModelToOutputDto(optionalRecipe.get());
        } else throw new RuntimeException("No recipe found with id: " + id);
    }

    public RecipeOutputDto createRecipe(RecipeInputDto recipeInputDto) {
        Recipe r = recipeRepository.save(recipeFromInputDtoToModel(recipeInputDto));
        return recipeFromModelToOutputDto(r);
    }

    public RecipeOutputDto updateRecipe(Long id, RecipeInputDto updatedRecipe) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            return RecipeMapper.recipeFromModelToOutputDto(optionalRecipe.get());
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
