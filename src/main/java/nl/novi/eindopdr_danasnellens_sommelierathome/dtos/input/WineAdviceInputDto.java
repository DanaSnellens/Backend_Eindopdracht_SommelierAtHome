package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.*;

import java.io.File;
import java.util.Set;

@Data
public class WineAdviceInputDto {
    private String personalMessage;
    private File adviceExplanation;

    //relaties
    private Set<Wine> wineSet;
    private Sommelier sommelier;
    private Client client;
    private WineAdviceRequest wineAdviceRequest;


    //winesList<Wine>
    //sommelier
    //client
    //wineAdviceRequest
}
