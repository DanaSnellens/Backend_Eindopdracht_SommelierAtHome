package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

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
    private String sommelierUsername; // Als deze is assigned
    private Long wineAdviceId; // Als deze is assigned



}

