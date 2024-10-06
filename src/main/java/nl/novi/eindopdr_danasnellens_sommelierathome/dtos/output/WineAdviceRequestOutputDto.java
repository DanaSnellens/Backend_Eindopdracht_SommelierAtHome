package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;

import java.util.Set;

@Data
public class WineAdviceRequestOutputDto {

    private Long id;
    private String dinnerOccasion;
    private String requestMessage;
    private String recipeLink;
    private Double minPricePerBottle;
    private Double maxPricePerBottle;

    //TODO versimpelde outputDTO voor somm (en client?) maken? CV etc hoeft niet meegestuurd te worden met wineadvice
    private Long sommelierId;
    //TODO is clientId en somm ID nodig? Of zit die automatisch gekoppeld via WAR?
    private Long clientId;
    private Long wineAdviceRequestId;
}

