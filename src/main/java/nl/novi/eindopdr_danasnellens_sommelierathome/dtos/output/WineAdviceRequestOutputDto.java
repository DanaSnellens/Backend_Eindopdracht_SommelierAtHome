package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class WineAdviceRequestOutputDto {

    private Long id;
    private String dinnerOccasion;
    private String requestMessage;
    private String recipeLink;
    private Double minPricePerBottle;
    private Double maxPricePerBottle;

    private String clientUsername;
    @Column(nullable = true)
    private String sommelierUsername; // Als deze is assigned
    @Column(nullable = true)
    private Long wineAdviceId; // Als deze is assigned



}

