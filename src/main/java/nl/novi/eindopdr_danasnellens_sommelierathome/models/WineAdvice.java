package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "wineAdvices")
@Getter
@Setter
public class WineAdvice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)               //KLOPT DIT?
    private Long id;
    private String personalMessage;
    private File adviceExplanation;

    //relaties
        // Wine
    @ManyToMany//TODO Welke cascadeType? All is rigoreus, maar welke wel?(cascade = CascadeType.ALL)
    @JoinTable(name = "wine_wineAdvice",
            joinColumns = @JoinColumn(name = "wine_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "wineAdvice_id",
                    referencedColumnName = "id"))
    private Set<Wine> wineSet = new HashSet<>();

        // Sommelier
    @ManyToOne
    @JoinColumn(name = "sommelier_id")
    private Sommelier sommelier;

        //Client
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

        // WineAdviceRequest
    @OneToOne
    private WineAdvice wineAdvice;

    public WineAdvice() {
    }

    public WineAdvice(Long id, String personalMessage, File adviceExplanation) {
        this.id = id;
        this.personalMessage = personalMessage;
        this.adviceExplanation = adviceExplanation;
    }
}
