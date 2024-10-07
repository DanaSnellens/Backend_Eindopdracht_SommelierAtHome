package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.SommelierOutputDtoShort;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceRequestOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.*;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRequestRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineMapper.wineModelListToOutputList;
import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineMapper.wineModelToOutput;

public class WineAdviceMapper {

    public static WineAdvice wineAdviceInputToModel(WineAdviceInputDto wineAdviceInputDto, WineAdviceRequest war) {
        WineAdvice wineAdvice = new WineAdvice();
        wineAdvice.setPersonalMessage(wineAdviceInputDto.getPersonalMessage());
        wineAdvice.setAdviceExplanation(wineAdviceInputDto.getAdviceExplanation());

        wineAdvice.setWineAdviceRequest(war);
        //wine(Id?)Set toevoegen?

        return wineAdvice;
    }


    public static WineAdviceOutputDto wineAdviceModelToOutput(WineAdvice wineAdvice) {
        WineAdviceOutputDto wineAdviceOutputDto = new WineAdviceOutputDto();
        wineAdviceOutputDto.setId(wineAdvice.getId());
        wineAdviceOutputDto.setPersonalMessage(wineAdvice.getPersonalMessage());
        wineAdviceOutputDto.setAdviceExplanation(wineAdvice.getAdviceExplanation());

        return wineAdviceOutputDto;
    }



/*        //relaties
        if (wineAdvice.getWineSet() != null) {
            Set<WineOutputDto> wineOutputDtoSet = new HashSet<>();

            Set<Wine> WineSet = wineAdvice.getWineSet();
            for (Wine wine : WineSet) {
                wineOutputDtoSet.add(wineModelToOutput(wine));
            }
            wineAdviceOutputDto.setWineOutputDtoSet(wineOutputDtoSet);
        }*/

//andere relaties? (sommelier, client, wineAdviceRequest)

    public static List<WineAdviceOutputDto> wineAdviceModelListToOutputList(List<WineAdvice> wineAdviceList) {
        List<WineAdviceOutputDto> wineAdviceOutputDtoList = new ArrayList<>();
        for (WineAdvice wineAdvice : wineAdviceList) {
            wineAdviceOutputDtoList.add(wineAdviceModelToOutput(wineAdvice));
        }
        return wineAdviceOutputDtoList;
    }
}
