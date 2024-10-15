package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceRequestInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.SommelierOutputDtoShort;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceRequestOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.*;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRequestRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.services.ClientService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineAdviceRequestMapper.wineAdviceRequestInputToModel;
import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineMapper.wineModelListToOutputList;
import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineMapper.wineModelToOutput;

public class WineAdviceMapper {
    private final WineAdviceRepository wineAdviceRepository;

    public WineAdviceMapper(WineAdviceRepository wineAdviceRepository, ClientService clientService) {
        this.wineAdviceRepository = wineAdviceRepository;
    }

    //TODO WAR vervangen door DTO of warId? Zelfde voor WineSet?
    public static WineAdvice wineAdviceInputToModel(WineAdviceInputDto waInputDto, WineAdviceRequest war, Set<Wine> wineSet) {
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

        if (wineAdvice.getWineSet() != null) {
            Set<Long> wineIdSet = new HashSet<>();

            for (Wine wine : wineAdvice.getWineSet()) {
                wineIdSet.add(wine.getId());
            }
            wineAdviceOutputDto.setWineIdSet(wineIdSet);
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
