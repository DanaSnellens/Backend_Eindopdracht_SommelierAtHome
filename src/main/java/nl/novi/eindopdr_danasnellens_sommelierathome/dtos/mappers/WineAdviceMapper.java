package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WineAdviceMapper {

    public static WineAdvice wineAdviceInputToModel(WineAdviceInputDto waInputDto, WineAdviceRequest war, /*Sommelier somm*/ Set<Wine> wineSet) {
        WineAdvice wa = new WineAdvice();
        wa.setPersonalMessage(waInputDto.getPersonalMessage());
        wa.setAdviceExplanation(waInputDto.getAdviceExplanation());

        wa.setWineAdviceRequest(war);
        wa.setWineSet(wineSet);
        return wa;
    }

    public static WineAdviceOutputDto wineAdviceModelToOutput(WineAdvice wineAdvice) {
        WineAdviceOutputDto wineAdviceOutputDto = new WineAdviceOutputDto();
        wineAdviceOutputDto.setId(wineAdvice.getId());
        wineAdviceOutputDto.setPersonalMessage(wineAdvice.getPersonalMessage());
        wineAdviceOutputDto.setAdviceExplanation(wineAdvice.getAdviceExplanation());

        if (wineAdvice.getWineAdviceRequest() != null) {
            wineAdviceOutputDto.setWineAdviceRequestId(wineAdvice.getWineAdviceRequest().getId());
        }

        if (wineAdvice.getWineSet() != null) {
            Set<Long> wineIdSet = new HashSet<>();
            for (Wine wine : wineAdvice.getWineSet()) {
                wineIdSet.add(wine.getId());
            }
            wineAdviceOutputDto.setWineIdSet(wineIdSet);
        }
        return wineAdviceOutputDto;
    }


    public static List<WineAdviceOutputDto> wineAdviceModelListToOutputList(List<WineAdvice> wineAdviceList) {
        List<WineAdviceOutputDto> wineAdviceOutputDtoList = new ArrayList<>();
        for (WineAdvice wineAdvice : wineAdviceList) {
            wineAdviceOutputDtoList.add(wineAdviceModelToOutput(wineAdvice));
        }
        return wineAdviceOutputDtoList;
    }
}
