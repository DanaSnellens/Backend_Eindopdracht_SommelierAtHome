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

    public static WineAdviceRequest wineAdviceRequestInputToModel(WineAdviceRequestInputDto warInputDto, Client client) {
        WineAdviceRequest war = new WineAdviceRequest();
        war.setDinnerOccasion(warInputDto.getDinnerOccasion());
        war.setRequestMessage(warInputDto.getRequestMessage());
        war.setRecipeLink(warInputDto.getRecipeLink());
        war.setMinPricePerBottle(warInputDto.getMinPricePerBottle());
        war.setMaxPricePerBottle(warInputDto.getMaxPricePerBottle());

        war.setClient(client); // Automatisch koppelen aan de client die ingelogd is

        return war;
    }

    public static WineAdviceRequestOutputDto wineAdviceRequestModelToOutput(WineAdviceRequest war) {
        WineAdviceRequestOutputDto warOutputDto = new WineAdviceRequestOutputDto();
        warOutputDto.setId(war.getId());
        warOutputDto.setDinnerOccasion(war.getDinnerOccasion());
        warOutputDto.setRequestMessage(war.getRequestMessage());
        warOutputDto.setRecipeLink(war.getRecipeLink());
        warOutputDto.setMinPricePerBottle(war.getMinPricePerBottle());
        warOutputDto.setMaxPricePerBottle(war.getMaxPricePerBottle());

        warOutputDto.setClientId(war.getClient().getId());

        if (war.getSommelier() != null) {
            warOutputDto.setSommelierId(war.getSommelier().getId());
        }
        if (war.getWineAdvice() != null) {
            warOutputDto.setWineAdviceId(war.getWineAdvice().getId());
        }

        return warOutputDto;
    }

    public static List<WineAdviceRequestOutputDto> wineAdviceRequestModelListToOutputList(List<WineAdviceRequest> wineAdviceRequestList) {
        List<WineAdviceRequestOutputDto> wineAdviceRequestOutputDtoList = new ArrayList<>();
        for (WineAdviceRequest wineAdviceRequest : wineAdviceRequestList) {
            wineAdviceRequestOutputDtoList.add(wineAdviceRequestModelToOutput(wineAdviceRequest));
        }
        return wineAdviceRequestOutputDtoList;
    }

}
