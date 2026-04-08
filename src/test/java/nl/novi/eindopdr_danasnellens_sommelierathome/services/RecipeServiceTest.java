package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.RecipeInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RecipeOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.exceptions.EntityAlreadyExistsException;
import nl.novi.eindopdr_danasnellens_sommelierathome.exceptions.RecordNotFoundException;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Recipe;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.RecipeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    private Recipe recipe;
    private RecipeInputDto recipeInputDto;

    @BeforeEach
    void setUp() {
        recipe = Recipe.builder()
                .id(1L)
                .recipeName("Spaghetti Bolognese")
                .course("Main Course")
                .mainIngredient("Beef")
                .otherIngredients("Tomato, Onion, Garlic, Olive oil, Spaghetti")
                .servings(4)
                .preparationTime(60)
                .winePairing("Chianti")
                .imageLink("https://example.com/images/spaghetti-bolognese.jpg")
                .imageAlt("Image of Spaghetti Bolognese dish")
                .preparationShortDescription("A classic Italian pasta dish with a rich and savory meat sauce.")
                .preparationLongDescription("Spaghetti Bolognese is a beloved Italian dish that features a hearty meat sauce made with ground beef, tomatoes, onions, garlic, and olive oil. The sauce is simmered to perfection and served over al dente spaghetti. This comforting meal is perfect for family dinners and pairs wonderfully with a glass of Chianti wine.")
                .build();

        recipeInputDto = RecipeInputDto.builder()
                .recipeName("Spaghetti Bolognese")
                .course("Main Course")
                .mainIngredient("Beef")
                .otherIngredients("Tomato, Onion, Garlic, Olive oil, Spaghetti")
                .servings(4)
                .preparationTime(60)
                .winePairing("Chianti")
                .imageLink("https://example.com/images/spaghetti-bolognese.jpg")
                .imageAlt("Image of Spaghetti Bolognese dish")
                .preparationShortDescription("A classic Italian pasta dish with a rich and savory meat sauce.")
                .preparationLongDescription("Spaghetti Bolognese is a beloved Italian dish that features a hearty meat sauce made with ground beef, tomatoes, onions, garlic, and olive oil. The sauce is simmered to perfection and served over al dente spaghetti. This comforting meal is perfect for family dinners and pairs wonderfully with a glass of Chianti wine.")
                .build();
    }

    @AfterEach
    void tearDown() {
        recipe = null;
        recipeInputDto = null;
    }

    @Test
    @DisplayName("Should return list of recipes")
    void testGetAllWines_shouldReturnListOfRecipes() {
        // arrange
        when(recipeRepository.findAll()).thenReturn(List.of(recipe));

        // act
        List<RecipeOutputDto> recipeDtoList = recipeService.getAllRecipes();

        // assert
        assertEquals(1, recipeDtoList.size());
        assertEquals(1L, recipeDtoList.get(0).getId());
        assertEquals("Spaghetti Bolognese", recipeDtoList.getFirst().getRecipeName());
        verify(recipeRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Should return recipeOutputDto by ID")
    void testGetRecipeById_shouldReturnRecipeOutputDto() {
        // arrange
        when(recipeRepository.findById(1L)).thenReturn(java.util.Optional.of(recipe));

        // act
        RecipeOutputDto recipeOutputDto = recipeService.getRecipeById(1L);

        // assert
        assertEquals(1L, recipeOutputDto.getId());
        assertEquals("Spaghetti Bolognese", recipeOutputDto.getRecipeName());
        //assertequals DTO/object:     assertEquals(recipeOutputDto, recipeOutputDto);
        verify(recipeRepository,times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Should throw exception with ID when recipe not found")
    void testGetRecipeById_shouldThrowExceptionWithIdWhenNotFound() {
        // arrange
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // act & assert
        assertThatThrownBy(() -> recipeService.getRecipeById(1L))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("No recipe found with id: %d", 1L);
    }

    @Test
    @DisplayName("Should add a new recipe and return recipeOutputDto")
    void testCreateRecipe_shouldAddNewRecipeAndReturnRecipeOutputDto() {
        // arrange
        when(recipeRepository.existsByRecipeName(anyString())).thenReturn(false);
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);

        // act
        RecipeOutputDto createdRecipeDto = recipeService.createRecipe(recipeInputDto);

        // assert
        assertEquals("Spaghetti Bolognese", createdRecipeDto.getRecipeName());
        verify(recipeRepository,times(1)).existsByRecipeName(anyString());
        verify(recipeRepository,times(1)).save(any(Recipe.class));
    }

    @Test
    @DisplayName("Should throw exception with recipe name when trying to add a recipe with an existing name")
    void testCreateRecipe_shouldThrowExceptionWhenAlreadyExists() {
        // arrange
        when(recipeRepository.existsByRecipeName(anyString())).thenReturn(true);

        // act & assert
        assertThatThrownBy(() -> recipeService.createRecipe(recipeInputDto))
                .isInstanceOf(EntityAlreadyExistsException.class)
                .hasMessageContaining("Recipe with name: %s already exists.", recipeInputDto.getRecipeName());
        verify(recipeRepository,never()).save(any(Recipe.class));
    }

    @Test
    @DisplayName("Should update existing recipe and return updated recipeOutputDto")
    void testUpdateRecipeById_shouldUpdateAndReturnRecipeOutputDto() {
        // arrange
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);

        // act
        RecipeOutputDto updatedRecipeDto = recipeService.updateRecipeById(1L, recipeInputDto);

        // assert
        assertEquals("Spaghetti Bolognese", updatedRecipeDto.getRecipeName());
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,times(1)).save(any(Recipe.class));
    }

    @Test
    @DisplayName("Should throw exception with ID when trying to update a non-existing recipe")
    void testUpdateRecipeById_shouldThrowExceptionWhenNotFound() {
        // arrange
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // act & assert
        assertThatThrownBy(() -> recipeService.updateRecipeById(1L, recipeInputDto))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("No recipe found with id: %d", 1L);
        verify(recipeRepository,never()).save(any(Recipe.class));
        verify(recipeRepository,times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Should delete existing recipe by ID")
    void testDeleteRecipeById_shouldDeleteExistingRecipe() {
        // arrange
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        doNothing().when(recipeRepository).deleteById(anyLong());

        // act
        recipeService.deleteRecipeById(1L);

        // assert
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("Should throw exception with ID when trying to delete a non-existing recipe")
    void testDeleteRecipeById_shouldThrowExceptionWhenNotFound() {
        // arrange
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // act & assert
        assertThatThrownBy(() -> recipeService.deleteRecipeById(1L))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("No recipe found with id: %d", 1L);
        verify(recipeRepository,never()).deleteById(anyLong());
        verify(recipeRepository,times(1)).findById(anyLong());
    }
}