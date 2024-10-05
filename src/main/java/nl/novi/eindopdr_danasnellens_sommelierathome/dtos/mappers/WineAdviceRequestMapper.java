package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceRequestInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceRequestOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRequestRepository;

import java.util.ArrayList;
import java.util.List;

public class WineAdviceRequestMapper {
    private static WineAdviceRequestRepository wineAdviceRequestRepository;

    public static WineAdviceRequest wineAdviceRequestInputToModel(WineAdviceRequestInputDto warInputDto, MyUserDetails userDetails) {
        WineAdviceRequest war = new WineAdviceRequest();
        war.setDinnerOccasion(warInputDto.getDinnerOccasion());
        war.setRequestMessage(warInputDto.getRequestMessage());
        war.setRecipeLink(warInputDto.getRecipeLink());
        war.setMinPricePerBottle(warInputDto.getMinPricePerBottle());
        war.setMaxPricePerBottle(warInputDto.getMaxPricePerBottle());

        //relaties
        war.setClient(userDetails.getUsername());
/*        war.setSommelier(warInputDto.getSommelier());
        war.setWineAdvice(warInputDto.getWineAdvice());*/

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

        //relaties
        warOutputDto.setClientId(war.getClientId());
        warOutputDto.setSommelier(wineAdviceRequest.getSommelier());
        warOutputDto.setWineAdviceId(wineAdviceRequest.getWineAdvice());

        return warDto;
    }

    public static List<WineAdviceRequestOutputDto> wineAdviceRequestModelListToOutputList(List<WineAdviceRequest> wineAdviceRequestList) {
        List<WineAdviceRequestOutputDto> wineAdviceRequestOutputDtoList = new ArrayList<>();
        for (WineAdviceRequest wineAdviceRequest : wineAdviceRequestList) {
            wineAdviceRequestOutputDtoList.add(wineAdviceRequestModelToOutput(wineAdviceRequest));
        }
        return wineAdviceRequestOutputDtoList;
    }

}
