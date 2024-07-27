package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceRequestInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineAdviceRequestMapper;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceRequestOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineAdviceRequestMapper.*;

@Service
public class WineAdviceRequestService {
    private final WineAdviceRequestRepository wineAdviceRequestRepository;

    public WineAdviceRequestService(WineAdviceRequestRepository wineAdviceRequestRepository) {
        this.wineAdviceRequestRepository = wineAdviceRequestRepository;
    }

    public List<WineAdviceRequestOutputDto> getAllWineAdviceRequests() {
        List<WineAdviceRequest> wineAdviceRequestList = wineAdviceRequestRepository.findAll();
        return wineAdviceRequestModelListToOutputList(wineAdviceRequestList);
    }

    public WineAdviceRequestOutputDto getWineAdviceRequestById(Long id) {
        Optional<WineAdviceRequest> optionalWineAdviceRequest = wineAdviceRequestRepository.findById(id);
        if (optionalWineAdviceRequest.isPresent()) {
            return wineAdviceRequestFromModelToOutputDto(optionalWineAdviceRequest.get());
        }
        else throw new RuntimeException("No wine advice request found with id: " + id);
    }

    public WineAdviceRequestOutputDto createWineAdviceRequest(WineAdviceRequestInputDto wineAdviceRequestInputDto) {
        WineAdviceRequest war = wineAdviceRequestRepository.save(wineAdviceRequestFromInputDtoToModel(wineAdviceRequestInputDto));
        return wineAdviceRequestFromModelToOutputDto(war);
    }

    public WineAdviceRequestOutputDto updateWineAdviceRequest(Long id, WineAdviceRequestInputDto updatedWineAdviceRequest) {
        Optional<WineAdviceRequest> optionalWineAdviceRequest = wineAdviceRequestRepository.findById(id);
        if (optionalWineAdviceRequest.isPresent()) {
            return WineAdviceRequestMapper.wineAdviceRequestFromModelToOutputDto(optionalWineAdviceRequest.get());
        }
        else throw new RuntimeException("No wine advice request found with id: " + id);
    }

    public void deleteWineAdviceRequestById(Long id) {
        Optional<WineAdviceRequest> optionalWineAdviceRequest = wineAdviceRequestRepository.findById(id);
        if (optionalWineAdviceRequest.isPresent()) {
            wineAdviceRequestRepository.deleteById(id);
        } else throw new RuntimeException("No wine advice request found with id: " + id);
    }
}



