package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;

import java.io.File;

@Data
public class WineAdviceInputDto {
    //relaties
    //winesList<Wine>
    //sommelier
    //client
    //wineAdviceRequest
    private String personalMessage;
    private File adviceExplanation;
}
