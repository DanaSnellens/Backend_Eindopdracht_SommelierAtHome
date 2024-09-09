package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    //relaties
        //client
        //sommelier
        //wineAdvice


    private String dinnerOccasion;
    private String requestMessage;
    private File recipeDocument;
    private String recipeLink;
    private Double minPricePerBottle;
    private Double maxPricePerBottle;

    public WineAdviceRequest() {
    }

    public WineAdviceRequest(Long id, String dinnerOccasion, String requestMessage, File recipeDocument, String recipeLink, Double minPricePerBottle, Double maxPricePerBottle) {
        this.id = id;
        this.dinnerOccasion = dinnerOccasion;
        this.requestMessage = requestMessage;
        this.recipeDocument = recipeDocument;
        this.recipeLink = recipeLink;
        this.minPricePerBottle = minPricePerBottle;
        this.maxPricePerBottle = maxPricePerBottle;
    }
}
