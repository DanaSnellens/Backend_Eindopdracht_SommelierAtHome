package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;

import java.io.File;

@Data
public class WineAdviceInputDto {
    public String personalMessage;
    public File adviceExplanation;
}
