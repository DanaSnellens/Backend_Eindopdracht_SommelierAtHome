package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceRequestInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineAdviceRequestMapper;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceRequestOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.SommelierRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineAdviceRequestMapper.*;

@Service
public class WineAdviceRequestService {
    private final WineAdviceRequestRepository wineAdviceRequestRepository;
    private final SommelierRepository sommelierRepository;

    public WineAdviceRequestService(WineAdviceRequestRepository wineAdviceRequestRepository, SommelierRepository sommelierRepository) {
        this.wineAdviceRequestRepository = wineAdviceRequestRepository;
        this.sommelierRepository = sommelierRepository;
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

    //RELATIES
    //TODO moet ik hier geen gebruik maken van DTO en mapper?
    public void assignSommelierToWineAdviceRequest(Long id, Long sommelierId) {
        Optional<WineAdviceRequest> optionalWineAdviceRequest = wineAdviceRequestRepository.findById(id);
        Optional<Sommelier> optionalSommelier = sommelierRepository.findById(sommelierId);

        if (optionalWineAdviceRequest.isPresent() && optionalSommelier.isPresent()) {
            Sommelier sommelier = optionalSommelier.get();
            WineAdviceRequest war = optionalWineAdviceRequest.get();

            war.setSommelier(sommelier);
            wineAdviceRequestRepository.save(war);
        } else throw new RuntimeException("No wine advice request found with id: " + id + " or no sommelier found with id: " + sommelierId);
    }
}



