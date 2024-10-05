package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.*;

import java.io.File;
import java.util.Set;

@Data
public class WineAdviceInputDto {
    private String personalMessage;
    private String adviceExplanation;

    //relaties
//TODO KLOPT DIT?
//    private Sommelier sommelier; : ZIT AL AAN WAR GEKOPPELD
//    private Client client; : ZIT AL AAN WAR GEKOPPELD

    private Long wineAdviceRequestId;
    private Set<Long> wineIdSet;
}
