package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;

import java.io.File;
@Data
public class WineAdviceRequestInputDto {

    //relaties
        //client
        //sommelier
        //wineAdvice
    public String dinnerOccasion;
    public String requestMessage;
    public File recipeDocument;
    public String recipeLink;
    public Double minPricePerBottle;
    public Double maxPricePerBottle;
}
