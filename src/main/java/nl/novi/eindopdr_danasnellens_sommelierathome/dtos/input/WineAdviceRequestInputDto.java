package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;

import java.io.File;
@Data
public class WineAdviceRequestInputDto {

    private String dinnerOccasion;
    private String requestMessage;
    private File recipeDocument;
    private String recipeLink;
    private Double minPricePerBottle;
    private Double maxPricePerBottle;

    //relaties
    private Client client;
    private Sommelier sommelier;
    private WineAdvice wineAdvice;

}
