package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;

import java.io.File;
@Data
public class WineAdviceRequestInputDto {

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
}
