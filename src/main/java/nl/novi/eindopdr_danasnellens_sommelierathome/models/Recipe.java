package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Recipes")
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    //relaties

    private String recipeName;
    private String course;
    private String mainIngredient;
    private String otherIngredients;
    private Integer servings;
    private Integer preparationTime;
    private String winePairing;
    private String imageLink;
    private String imageAlt;
    private String preparationShortDescription;
    private String preparationLongDescription;

    public Recipe() {
    }

    public Recipe(Long id, String recipeName, String course, String mainIngredient, String otherIngredients, Integer servings, Integer preparationTime, String preparationShortDescription, String preparationLongDescription, String winePairing, String imageLink, String imageAlt) {
        this.id = id;
        this.recipeName = recipeName;
        this.course = course;
        this.mainIngredient = mainIngredient;
        this.otherIngredients = otherIngredients;
        this.servings = servings;
        this.preparationTime = preparationTime;
        this.winePairing = winePairing;
        this.imageLink = imageLink;
        this.imageAlt = imageAlt;
        this.preparationShortDescription = preparationShortDescription;
        this.preparationLongDescription = preparationLongDescription;
    }
}
