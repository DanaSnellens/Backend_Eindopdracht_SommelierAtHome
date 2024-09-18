package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceRequestInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceRequestOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRequestRepository;

import java.util.ArrayList;
import java.util.List;

public class WineAdviceRequestMapper {
    private static WineAdviceRequestRepository wineAdviceRequestRepository;

    public static WineAdviceRequest wineAdviceRequestFromInputDtoToModel(WineAdviceRequestInputDto wineAdviceRequestInputDto) {
        WineAdviceRequest wineAdviceRequest = new WineAdviceRequest();
        wineAdviceRequest.setDinnerOccasion(wineAdviceRequestInputDto.getDinnerOccasion());
        wineAdviceRequest.setRequestMessage(wineAdviceRequestInputDto.getRequestMessage());
        wineAdviceRequest.setRecipeDocument(wineAdviceRequestInputDto.getRecipeDocument());
        wineAdviceRequest.setRecipeLink(wineAdviceRequestInputDto.getRecipeLink());
        wineAdviceRequest.setMinPricePerBottle(wineAdviceRequestInputDto.getMinPricePerBottle());
        wineAdviceRequest.setMaxPricePerBottle(wineAdviceRequestInputDto.getMaxPricePerBottle());

        //relaties
        wineAdviceRequest.setClient(wineAdviceRequestInputDto.getClient());
        wineAdviceRequest.setSommelier(wineAdviceRequestInputDto.getSommelier());
        wineAdviceRequest.setWineAdvice(wineAdviceRequestInputDto.getWineAdvice());

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

        //relaties
        wineAdviceRequestOutputDto.setClient(wineAdviceRequest.getClient());
        wineAdviceRequestOutputDto.setSommelier(wineAdviceRequest.getSommelier());
        wineAdviceRequestOutputDto.setWineAdvice(wineAdviceRequest.getWineAdvice());

        return wineAdviceRequestOutputDto;
    }

    public static List<WineAdviceRequestOutputDto> wineAdviceRequestModelListToOutputList(List<WineAdviceRequest> wineAdviceRequestList) {
        List<WineAdviceRequestOutputDto> wineAdviceRequestOutputDtoList = new ArrayList<>();
        for (WineAdviceRequest wineAdviceRequest : wineAdviceRequestList) {
            wineAdviceRequestOutputDtoList.add(wineAdviceRequestFromModelToOutputDto(wineAdviceRequest));
        }
        return wineAdviceRequestOutputDtoList;
    }
}
