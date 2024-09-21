package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.*;

import java.io.File;
import java.util.Set;

@Data
public class WineAdviceInputDto {
    private String personalMessage;
    private File adviceExplanation;
    private Long wineAdviceRequestId;
    private Long wineAdviceId;
    //relaties

//    private Sommelier sommelier;
//    private Client client;
    private Set<Wine> wineSet;


    //winesList<Wine>
    //sommelier
    //client
    //wineAdviceRequest
}
