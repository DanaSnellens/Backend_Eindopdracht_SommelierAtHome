package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "wineAdvices")
@Data
public class WineAdvice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String personalMessage;
    private String adviceExplanation;

    //relaties
        // Wine
    //TODO Welke cascadeType? All is rigoreus, maar welke wel?Ook toevoegen/wijzigen bij anderen
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "wineAdvice_wine",
            joinColumns = @JoinColumn(name = "wine_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "wineAdvice_id", referencedColumnName = "id"))
    private Set<Wine> wineSet = new HashSet<>();

    //Deze kunnen weg, want zijn al in WineAdviceRequest
    /*
        // Sommelier
    @ManyToOne
    @JoinColumn(name = "sommelier_id")
    private Sommelier sommelier;

        //Client
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;*/

        // WineAdviceRequest
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wineAdvice_id")
    private WineAdviceRequest wineAdviceRequest;
}
