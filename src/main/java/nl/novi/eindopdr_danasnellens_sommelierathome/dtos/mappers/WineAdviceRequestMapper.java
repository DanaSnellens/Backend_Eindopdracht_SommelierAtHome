package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.AssignSommInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceRequestInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineInputDto;
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
        war.setRecipeFile(warInputDto.getRecipeFile());
        war.setMinPricePerBottle(warInputDto.getMinPricePerBottle());
        war.setMaxPricePerBottle(warInputDto.getMaxPricePerBottle());

        //TODO Vervangen door outputDTO? En if statement toevoegen om te checken of authenticated?
        war.setClient(client); // set authenticated client
//        war.setSommelier(sommelier); // set authenticated sommelier

        return war;
    }

    public static WineAdviceRequest updateWarMapper(WineAdviceRequest savedWar, WineAdviceRequestInputDto updatedWarInputDto) {
        savedWar.setDinnerOccasion(updatedWarInputDto.getDinnerOccasion());
        savedWar.setRequestMessage(updatedWarInputDto.getRequestMessage());
        savedWar.setRecipeLink(updatedWarInputDto.getRecipeLink());
        savedWar.setRecipeFile(updatedWarInputDto.getRecipeFile());
        savedWar.setMinPricePerBottle(updatedWarInputDto.getMinPricePerBottle());
        savedWar.setMaxPricePerBottle(updatedWarInputDto.getMaxPricePerBottle());
        return savedWar;
    }

    public static WineAdviceRequest assignSommMapper(WineAdviceRequest savedWar, AssignSommInputDto assignSommInputDto) {
        savedWar.setSommelier(sommelier);
        return savedWar;
    }

    public static WineAdviceRequestOutputDto wineAdviceRequestModelToOutput(WineAdviceRequest war) {
        WineAdviceRequestOutputDto dto = new WineAdviceRequestOutputDto();
            //No need to set clientId or SommelierId, userDetails will be set in the service layer {
        dto.setId(war.getId());
        dto.setDinnerOccasion(war.getDinnerOccasion());
        dto.setRequestMessage(war.getRequestMessage());
        dto.setRecipeLink(war.getRecipeLink());
        dto.setRecipeFile(war.getRecipeFile());
        dto.setMinPricePerBottle(war.getMinPricePerBottle());
        dto.setMaxPricePerBottle(war.getMaxPricePerBottle());

        //TODO Of moet onderstaande if statement in de service layer/vervangen door JWT?UserDetails?
/*        dto.setSommelierUsername(war.getSommelier().getUsername());*/    //Als ik deze uitcomment doet createWar het niet meer
/*        dto.setClientUsername(war.getClient().getUsername());*/

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
