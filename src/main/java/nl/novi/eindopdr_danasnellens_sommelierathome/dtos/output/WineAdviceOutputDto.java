package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Wine;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;

import java.util.Set;

@Data
public class WineAdviceOutputDto {
    private Long id;
    private String personalMessage;
    private String adviceExplanation;

    //relaties
    private Set<WineOutputDto> wineOutputDtoSet;

    //TODO versimpelde outputDTO voor somm (en client?) maken? CV etc hoeft niet meegestuurd te worden met wineadvice
    private SommelierOutputDtoShort sommelierOutputDtoShort;
    private ClientOutputDtoShort clientOutputDtoShort;
    private Long WineAdviceRequestIdOutputDto wineAdviceRequestIdOutputDto;

    public void setWineAdviceRequestId(Long id) {
    }
}
