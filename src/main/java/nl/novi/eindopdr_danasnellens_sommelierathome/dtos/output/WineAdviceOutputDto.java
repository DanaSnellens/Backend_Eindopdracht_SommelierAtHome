package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;

import java.util.Set;

@Data
public class WineAdviceOutputDto {
    private Long id;
    private String personalMessage;
    private String adviceExplanation;

    //relaties
    private Set<Long> wineIdSet;

    //TODO versimpelde outputDTO voor somm (en client?) maken? CV etc hoeft niet meegestuurd te worden met wineadvice
    private Long sommelierId;
    //TODO is clientId en somm ID nodig? Of zit die automatisch gekoppeld via WAR?
    private Long clientId;
    private Long wineAdviceRequestId;
}
