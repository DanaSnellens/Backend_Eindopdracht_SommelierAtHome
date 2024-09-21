package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.*;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRequestRepository;

import java.util.ArrayList;
import java.util.List;

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
        wineAdvice.setWineSet(wineAdviceInputDto.getWineSet());
/*        wineAdvice.setSommelier(wineAdviceInputDto.getWineAdviceRequest().getSommelier());
        wineAdvice.setClient(wineAdviceInputDto.getWineAdviceRequest().getClient());*/
/*        wineAdvice.setWineAdviceRequest(wineAdviceRequestRepository.findById());*/


        return wineAdvice;
    }

    public static WineAdviceOutputDto wineAdviceFromModelToOutputDto(WineAdvice wineAdvice) {
        WineAdviceOutputDto wineAdviceOutputDto = new WineAdviceOutputDto();
        wineAdviceOutputDto.setId(wineAdvice.getId());
        wineAdviceOutputDto.setPersonalMessage(wineAdvice.getPersonalMessage());
        wineAdviceOutputDto.setAdviceExplanation(String.valueOf(wineAdvice.getAdviceExplanation()));

        //relaties
        wineAdviceOutputDto.setWineSet(wineAdvice.getWineSet());
        wineAdviceOutputDto.setSommelier(wineAdvice.getSommelier());
        wineAdviceOutputDto.setClient(wineAdvice.getClient());
        wineAdviceOutputDto.setWineAdviceRequest(wineAdvice.getWineAdviceRequest());

        return wineAdviceOutputDto;
    }

    public static List<WineAdviceOutputDto> wineAdviceModelListToOutputList(List<WineAdvice> wineAdviceList) {
        List<WineAdviceOutputDto> wineAdviceOutputDtoList = new ArrayList<>();
        for (WineAdvice wineAdvice : wineAdviceList) {
            wineAdviceOutputDtoList.add(wineAdviceFromModelToOutputDto(wineAdvice));
        }
        return wineAdviceOutputDtoList;
    }
}
