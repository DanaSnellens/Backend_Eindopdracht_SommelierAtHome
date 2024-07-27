package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;

@Data
public class WineAdviceRequestOutputDto {

    //relaties
        //client
        //sommelier
        //wineAdvice

    private Long id;
    private String dinnerOccasion;
    private String requestMessage;
    private String recipeDocument;
    private String recipeLink;
    private Double minPricePerBottle;
    private Double maxPricePerBottle;
}
