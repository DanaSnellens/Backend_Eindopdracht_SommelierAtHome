package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;

@Data
public class WineAdviceRequestOutputDto {

    private Long id;
    private String dinnerOccasion;
    private String requestMessage;
    private String recipeDocument;
    private String recipeLink;
    private Double minPricePerBottle;
    private Double maxPricePerBottle;

    //relaties
    private Client client;
    private Sommelier sommelier;
    private WineAdvice wineAdvice;
}
