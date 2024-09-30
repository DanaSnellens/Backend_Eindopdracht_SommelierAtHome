package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipes")
@Data
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
    @JoinTable(name = "recipe_wine",
            joinColumns = @JoinColumn(name = "wine_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"))
    private Set<Wine> wineSet = new HashSet<>();

}
