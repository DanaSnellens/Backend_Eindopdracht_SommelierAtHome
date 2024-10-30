package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceRequestInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceRequestOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRequestRepository;

import java.util.ArrayList;
import java.util.List;

public class WineAdviceRequestMapper {

    public static WineAdviceRequest wineAdviceRequestInputToModel(WineAdviceRequestInputDto warInputDto, Client client) {
        WineAdviceRequest war = new WineAdviceRequest();
        war.setDinnerOccasion(warInputDto.getDinnerOccasion());
        war.setRequestMessage(warInputDto.getRequestMessage());
        war.setRecipeLink(warInputDto.getRecipeLink());
        war.setMinPricePerBottle(warInputDto.getMinPricePerBottle());
        war.setMaxPricePerBottle(warInputDto.getMaxPricePerBottle());

        //TODO Vervangen door outputDTO? En if statement toevoegen om te checken of authenticated?
        war.setClient(client); // set authenticated client
//        war.setSommelier(sommelier); // set authenticated sommelier

        return war;
    }

    public static WineAdviceRequestOutputDto wineAdviceRequestModelToOutput(WineAdviceRequest war) {
        WineAdviceRequestOutputDto dto = new WineAdviceRequestOutputDto();
            //No need to set clientId or SommelierId, userDetails will be set in the service layer {
        dto.setId(war.getId());
        dto.setDinnerOccasion(war.getDinnerOccasion());
        dto.setRequestMessage(war.getRequestMessage());
        dto.setRecipeLink(war.getRecipeLink());
        dto.setMinPricePerBottle(war.getMinPricePerBottle());
        dto.setMaxPricePerBottle(war.getMaxPricePerBottle());

        //TODO Of moet onderstaande if statement in de service layer/vervangen door JWT?UserDetails?
/*        dto.setSommelierUsername(war.getSommelier().getUsername());
        dto.setClientUsername(war.getClient().getUsername());*/

        if (war.getWineAdvice() != null) {
            dto.setWineAdviceId(war.getWineAdvice().getId());
        }
        if (war.getClient() != null) {
            dto.setClientUsername(war.getClient().getUsername());
        }
        return dto;
    }

    public static List<WineAdviceRequestOutputDto> wineAdviceRequestModelListToOutputList(List<WineAdviceRequest> wineAdviceRequestList) {
        List<WineAdviceRequestOutputDto> wineAdviceRequestOutputDtoList = new ArrayList<>();
        for (WineAdviceRequest war : wineAdviceRequestList) {
            wineAdviceRequestOutputDtoList.add(wineAdviceRequestModelToOutput(war));
        }
        return wineAdviceRequestOutputDtoList;
    }
}
