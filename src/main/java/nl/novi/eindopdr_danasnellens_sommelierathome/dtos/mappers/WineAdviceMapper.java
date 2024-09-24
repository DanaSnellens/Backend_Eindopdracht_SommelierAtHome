package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.SommelierOutputDtoShort;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceOutputDto;
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

    private final WineAdviceRequestRepository wineAdviceRequestRepository;

    public WineAdviceMapper(WineAdviceRequestRepository wineAdviceRequestRepository) {
        this.wineAdviceRequestRepository = wineAdviceRequestRepository;
    }

    public static WineAdvice wineAdviceFromInputDtoToModel(WineAdviceInputDto wineAdviceInputDto) {
        WineAdvice wineAdvice = new WineAdvice();
        wineAdvice.setPersonalMessage(wineAdviceInputDto.getPersonalMessage());
        wineAdvice.setAdviceExplanation(wineAdviceInputDto.getAdviceExplanation());

        //relaties
//TODO Worden in de servicelaag gedaan??? Klopt dit?
        return wineAdvice;
    }

    public static WineAdviceOutputDto wineAdviceModelToOutput(WineAdvice wineAdvice) {
        WineAdviceOutputDto wineAdviceOutputDto = new WineAdviceOutputDto();
        wineAdviceOutputDto.setId(wineAdvice.getId());
        wineAdviceOutputDto.setPersonalMessage(wineAdvice.getPersonalMessage());
        wineAdviceOutputDto.setAdviceExplanation(String.valueOf(wineAdvice.getAdviceExplanation()));

        //relaties
        if (wineAdvice.getWineSet() != null) {
            Set<WineOutputDto> wineOutputDtoSet = new HashSet<>();

            Set<Wine> WineSet = wineAdvice.getWineSet();
            for (Wine wine : WineSet) {
                wineOutputDtoSet.add(wineModelToOutput(wine));
            }
            wineAdviceOutputDto.setWineOutputDtoSet(wineOutputDtoSet);
        }

        //TODO Naar onderstaande (en andere mappers?) nog kijken - 24-9-2024: DTO's even laten rusten voor nu
/*        if (wineAdvice.getSommelier() != null) {
            Sommelier sommelier = wineAdvice.getSommelier();
            wineAdviceOutputDto.setSommelier(wineAdvice.(new SommelierOutputDtoShort().getId());
        }

        if (wineAdvice.getClient() != null) {
            wineAdviceOutputDto.setClientOutputDtoShort(wineAdvice.getClient());
        }*/

        if (wineAdvice.getWineAdviceRequest() != null) {
            wineAdviceOutputDto.setWineAdviceRequestIdOutputDto(wineAdvice.getWineAdviceRequest().getId());
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
