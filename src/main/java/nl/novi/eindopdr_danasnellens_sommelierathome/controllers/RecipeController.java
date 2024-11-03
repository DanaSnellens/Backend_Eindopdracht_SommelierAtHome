package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.AddWinesInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.RecipeInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RecipeOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<RecipeOutputDto>> getAllRecipes() {
        return ResponseEntity.ok().body(recipeService.getAllRecipes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RecipeOutputDto> getRecipeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(recipeService.getRecipeById(id));
    }

    @PostMapping
    public ResponseEntity<RecipeOutputDto> createRecipe
            (@Valid @RequestBody RecipeInputDto recipeInputDto) {
            RecipeOutputDto recipeOutputDto = recipeService.createRecipe(recipeInputDto);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(recipeOutputDto.getId()).toUri();
            return ResponseEntity.created(uri).body(recipeOutputDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeOutputDto> updateRecipeById(@PathVariable Long id, @Valid @RequestBody RecipeInputDto updatedRecipe) {
        RecipeOutputDto recipeOutputDto = recipeService.updateRecipeById(id, updatedRecipe);
        return ResponseEntity.ok().body(recipeOutputDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRecipeById(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);
        return ResponseEntity.ok("Recipe with id " + id + " has been successful deleted");
    }

    @PutMapping("/{id}/addwines")
    public ResponseEntity<RecipeOutputDto> addWinesToRecipe(@PathVariable Long id, @RequestBody AddWinesInputDto addWinesInputDto) {
        RecipeOutputDto recipeOutputDto = recipeService.addWinesToRecipe(id, addWinesInputDto);
        return ResponseEntity.ok().body(recipeOutputDto);
    }
}
