package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceRequestInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceRequestOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;

import java.util.ArrayList;
import java.util.List;

public class WineAdviceRequestMapper {

    //relaties toevoegen bij alle3
        //client
        //sommelier
        //wineAdvice
    public static WineAdviceRequest wineAdviceRequestFromInputDtoToModel(WineAdviceRequestInputDto wineAdviceRequestInputDto) {
        WineAdviceRequest wineAdviceRequest = new WineAdviceRequest();
        wineAdviceRequest.setDinnerOccasion(wineAdviceRequestInputDto.getDinnerOccasion());
        wineAdviceRequest.setRequestMessage(wineAdviceRequestInputDto.getRequestMessage());
        wineAdviceRequest.setRecipeDocument(wineAdviceRequestInputDto.getRecipeDocument());
        wineAdviceRequest.setRecipeLink(wineAdviceRequestInputDto.getRecipeLink());
        wineAdviceRequest.setMinPricePerBottle(wineAdviceRequestInputDto.getMinPricePerBottle());
        wineAdviceRequest.setMaxPricePerBottle(wineAdviceRequestInputDto.getMaxPricePerBottle());
        return wineAdviceRequest;
    }

    public static WineAdviceRequestOutputDto wineAdviceRequestFromModelToOutputDto(WineAdviceRequest wineAdviceRequest) {
        WineAdviceRequestOutputDto wineAdviceRequestOutputDto = new WineAdviceRequestOutputDto();
        wineAdviceRequestOutputDto.setId(wineAdviceRequest.getId());
        wineAdviceRequestOutputDto.setDinnerOccasion(wineAdviceRequest.getDinnerOccasion());
        wineAdviceRequestOutputDto.setRequestMessage(wineAdviceRequest.getRequestMessage());
        wineAdviceRequestOutputDto.setRecipeDocument(wineAdviceRequest.getRecipeDocument().toString());
        wineAdviceRequestOutputDto.setRecipeLink(wineAdviceRequest.getRecipeLink());
        wineAdviceRequestOutputDto.setMinPricePerBottle(wineAdviceRequest.getMinPricePerBottle());
        wineAdviceRequestOutputDto.setMaxPricePerBottle(wineAdviceRequest.getMaxPricePerBottle());
        return wineAdviceRequestOutputDto;
    }

    public static List<WineAdviceRequestOutputDto> wineAdviceRequestListToOutputList(List<WineAdviceRequest> wineAdviceRequestList) {
        List<WineAdviceRequestOutputDto> wineAdviceRequestOutputDtoList = new ArrayList<>();
        for (WineAdviceRequest wineAdviceRequest : wineAdviceRequestList) {
            wineAdviceRequestOutputDtoList.add(wineAdviceRequestFromModelToOutputDto(wineAdviceRequest));
        }
        return wineAdviceRequestOutputDtoList;
    }
}
