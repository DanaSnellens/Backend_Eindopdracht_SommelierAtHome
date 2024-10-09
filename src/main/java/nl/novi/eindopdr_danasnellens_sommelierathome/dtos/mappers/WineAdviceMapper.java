package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceRequestInputDto;
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
    //TODO WAR vervangen door DTO of warId? Zelfde voor WineSet?
    public static WineAdvice wineAdviceInputToModel(WineAdviceInputDto wineAdviceInputDto, WineAdviceRequest wineAdviceRequest, Set<Wine> wineSet) {
        WineAdvice wineAdvice = new WineAdvice();
        wineAdvice.setPersonalMessage(wineAdviceInputDto.getPersonalMessage());
        wineAdvice.setAdviceExplanation(wineAdviceInputDto.getAdviceExplanation());
//TODO vervangen door outputDTO of id? En if statement toevoegen?
        wineAdvice.setWineAdviceRequest(wineAdviceRequest);
        //sommelier zit al gekoppeld aan de war, dus hoeft hier niet meer ingevoerd te worden.

        wineAdvice.setWineSet(wineSet);

        return wineAdvice;
    }


    public static WineAdviceOutputDto wineAdviceModelToOutput(WineAdvice wineAdvice) {
        WineAdviceOutputDto wineAdviceOutputDto = new WineAdviceOutputDto();
        wineAdviceOutputDto.setId(wineAdvice.getId());
        wineAdviceOutputDto.setPersonalMessage(wineAdvice.getPersonalMessage());
        wineAdviceOutputDto.setAdviceExplanation(wineAdvice.getAdviceExplanation());


        if (wineAdvice.getWineSet() != null) {
            Set<Long> wineIdSet = new HashSet<>();

            for (Wine wine : wineAdvice.getWineSet()) {
                wineIdSet.add(wine.getId());
            }
            wineAdviceOutputDto.setWineIdSet(wineIdSet);
        }

        if (wineAdvice.getSommelier() != null) {
            wineAdviceOutputDto.setSommelierId(wineAdvice.getSommelier().getId());
        }

        if (wineAdvice.getClient() != null) {
            wineAdviceOutputDto.setClientId(wineAdvice.getClient().getId());
        }

        if (wineAdvice.getWineAdviceRequest() != null) {
            wineAdviceOutputDto.setWineAdviceRequestId(wineAdvice.getWineAdviceRequest().getId());
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
