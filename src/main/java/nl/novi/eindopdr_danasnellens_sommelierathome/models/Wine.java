package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "wines")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wines_sequence_1021")
    @SequenceGenerator(name = "wines_sequence_1021", sequenceName = "wines_sequence_1021", initialValue = 1021, allocationSize = 1)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String wineName;
    private String country;
    private String region;
    private String grapeVarietal;
    private String producer;
    private String wineStyle;
    private String wineType;
    private String foodPairing;
    private String year;
    private Double price;
    private String aromas;
    private String imageLink;
    private String imageAlt;
    private String shortDescription;
    private String longDescription;

    @ManyToMany(mappedBy = "wineSet", fetch = FetchType.LAZY)
    private Set<WineAdvice> wineAdviceSet;

    @ManyToMany(mappedBy = "wineSet", fetch = FetchType.LAZY)
    private Set<Recipe> recipeSet;
}