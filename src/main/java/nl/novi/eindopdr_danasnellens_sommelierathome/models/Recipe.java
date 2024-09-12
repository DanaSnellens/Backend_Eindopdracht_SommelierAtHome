package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipes")
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

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

    //relaties
        //Wine
    @ManyToMany//TODO Welke cascadeType? All is rigoreus, maar welke wel?(cascade = CascadeType.ALL)
    @JoinTable(name = "wine_recipe",
            joinColumns = @JoinColumn(name = "wine_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "wine_id",
                    referencedColumnName = "id"))
    private Set<Wine> wineSet = new HashSet<>();

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
