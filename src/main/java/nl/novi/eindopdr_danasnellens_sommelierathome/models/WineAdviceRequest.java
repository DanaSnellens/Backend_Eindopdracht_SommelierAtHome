package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Entity
@Table(name = "wineAdviceRequests")
@Getter
@Setter
public class WineAdviceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String dinnerOccasion;
    private String requestMessage;
    private String recipeLink;
    private Double minPricePerBottle;
    private Double maxPricePerBottle;

    //relaties

        //client
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    //sommelier
    @ManyToOne
    @JoinColumn(name = "sommelier_id)")
    private Sommelier sommelier;

    //wineAdvice
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wineAdvice_id")
    private WineAdvice wineAdvice;

    public WineAdviceRequest() {
    }

    public WineAdviceRequest(Long id, String dinnerOccasion, String requestMessage, File recipeDocument, String recipeLink, Double minPricePerBottle, Double maxPricePerBottle) {
        this.id = id;
        this.dinnerOccasion = dinnerOccasion;
        this.requestMessage = requestMessage;
        this.recipeLink = recipeLink;
        this.minPricePerBottle = minPricePerBottle;
        this.maxPricePerBottle = maxPricePerBottle;
    }
}
