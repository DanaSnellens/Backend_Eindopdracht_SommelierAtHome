package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class WineAdviceOutputDto {
    private Long id;
    private String personalMessage;
    private String adviceExplanation;

    private Set<Long> wineIdSet = new HashSet<>();

    //Kunnen weg, want verloopt via WAR
/*    private Long sommelierId;
    private Long clientId;*/
    private Long wineAdviceRequestId;
}
