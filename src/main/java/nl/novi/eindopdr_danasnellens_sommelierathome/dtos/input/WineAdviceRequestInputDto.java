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
    private String recipeLink;
    private File recipeFile;
    private Double minPricePerBottle;
    private Double maxPricePerBottle;

    //Kan weg ivm userdetails
//    private Long clientId;  // Deze nog automatisch koppelen aan de client die ingelogd is
    // private Long sommelierId; // Deze nog automatisch koppelen aan de sommelier die ingelogd is
}
