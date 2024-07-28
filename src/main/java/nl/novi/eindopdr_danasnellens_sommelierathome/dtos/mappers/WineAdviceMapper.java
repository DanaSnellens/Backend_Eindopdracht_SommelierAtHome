package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;

import java.util.ArrayList;
import java.util.List;

public class WineAdviceMapper {

    public static WineAdvice wineAdviceFromInputDtoToModel(WineAdviceInputDto wineAdviceInputDto) {
        WineAdvice wineAdvice = new WineAdvice();
        wineAdvice.setPersonalMessage(wineAdviceInputDto.getPersonalMessage());
        wineAdvice.setAdviceExplanation(wineAdviceInputDto.getAdviceExplanation());
        return wineAdvice;
    }

    public static WineAdviceOutputDto wineAdviceFromModelToOutputDto(WineAdvice wineAdvice) {
        WineAdviceOutputDto wineAdviceOutputDto = new WineAdviceOutputDto();
        wineAdviceOutputDto.setId(wineAdvice.getId());
        wineAdviceOutputDto.setPersonalMessage(wineAdvice.getPersonalMessage());
        wineAdviceOutputDto.setAdviceExplanation(String.valueOf(wineAdvice.getAdviceExplanation()));
        return wineAdviceOutputDto;
    }

    public static List<WineAdviceOutputDto> wineAdviceModelListToOutputList(List<WineAdvice> wineAdviceList) {
        List<WineAdviceOutputDto> wineAdviceOutputDtoList = new ArrayList<>();
        for (WineAdvice wineAdvice : wineAdviceList) {
            wineAdviceOutputDtoList.add(wineAdviceFromModelToOutputDto(wineAdvice));
        }
        return wineAdviceOutputDtoList;
    }
}
