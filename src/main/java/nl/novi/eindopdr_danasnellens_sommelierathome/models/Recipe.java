package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipes_sequence_2021")
    @SequenceGenerator(name = "recipes_sequence_2021", sequenceName = "recipes_sequence_2021", initialValue = 2021, allocationSize = 1)
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "recipes_wines",
            joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "wine_id", referencedColumnName = "id"))
    private Set<Wine> wineSet = new HashSet<>();
}
