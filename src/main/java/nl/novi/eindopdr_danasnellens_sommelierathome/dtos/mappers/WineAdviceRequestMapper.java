package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceRequestInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceRequestOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRequestRepository;

import java.util.ArrayList;
import java.util.List;

public class WineAdviceRequestMapper {
    private static WineAdviceRequestRepository wineAdviceRequestRepository;

    //TODO Client vervangen door clientOutputDto?
    public static WineAdviceRequest wineAdviceRequestInputToModel(WineAdviceRequestInputDto warInputDto, Client client) {
        WineAdviceRequest war = new WineAdviceRequest();
        war.setDinnerOccasion(warInputDto.getDinnerOccasion());
        war.setRequestMessage(warInputDto.getRequestMessage());
        war.setRecipeLink(warInputDto.getRecipeLink());
        war.setMinPricePerBottle(warInputDto.getMinPricePerBottle());
        war.setMaxPricePerBottle(warInputDto.getMaxPricePerBottle());

        //TODO Vervangen door outputDTO? En if statement toevoegen?
        war.setClient(client); // set authenticated client

        return war;
    }

    public static WineAdviceRequestOutputDto wineAdviceRequestModelToOutput(WineAdviceRequest war) {
        WineAdviceRequestOutputDto dto = new WineAdviceRequestOutputDto();
        dto.setId(war.getId());
        dto.setDinnerOccasion(war.getDinnerOccasion());
        dto.setRequestMessage(war.getRequestMessage());
        dto.setRecipeLink(war.getRecipeLink());
        dto.setMinPricePerBottle(war.getMinPricePerBottle());
        dto.setMaxPricePerBottle(war.getMaxPricePerBottle());

        dto.setClientUsername(war.getClient().getId());

        if (war.getSommelier() != null) {
            dto.setSommelierUserName(war.getSommelier().getId());
        }
        if (war.getWineAdvice() != null) {
            dto.setWineAdviceId(war.getWineAdvice().getId());
        }

        return dto;
    }

    public static List<WineAdviceRequestOutputDto> wineAdviceRequestModelListToOutputList(List<WineAdviceRequest> wineAdviceRequestList) {
        List<WineAdviceRequestOutputDto> wineAdviceRequestOutputDtoList = new ArrayList<>();
        for (WineAdviceRequest wineAdviceRequest : wineAdviceRequestList) {
            wineAdviceRequestOutputDtoList.add(wineAdviceRequestModelToOutput(wineAdviceRequest));
        }
        return wineAdviceRequestOutputDtoList;
    }

}
