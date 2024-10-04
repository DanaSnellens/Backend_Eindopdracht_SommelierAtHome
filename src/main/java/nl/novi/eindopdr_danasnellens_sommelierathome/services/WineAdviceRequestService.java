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
            return wineAdviceRequestModelToOutput(optionalWineAdviceRequest.get());
        }
        else throw new RuntimeException("No wine advice request found with id: " + id);
    }

    public WineAdviceRequestOutputDto createWineAdviceRequest(WineAdviceRequestInputDto wineAdviceRequestInputDto, MyUserDetails userDetails) {
        WineAdviceRequest war = wineAdviceRequestInputToModel(wineAdviceRequestInputDto, userDetails);
        WineAdviceRequest.setClient(userDetails.getUsername());
        WineAdviceRequest savedWar = wineAdviceRequestRepository.save(war);
        return wineAdviceRequestModelToOutput(savedWar);
    }

    public WineAdviceRequestOutputDto updateWineAdviceRequest(Long id, WineAdviceRequestInputDto updatedWineAdviceRequest) {
        Optional<WineAdviceRequest> optionalWineAdviceRequest = wineAdviceRequestRepository.findById(id);
        if (optionalWineAdviceRequest.isPresent()) {
            return WineAdviceRequestMapper.wineAdviceRequestModelToOutput(optionalWineAdviceRequest.get());
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
    public void assignSommelierToWineAdviceRequest(Long WarId, Long sommelierId) {
        Optional<WineAdviceRequest> optionalWineAdviceRequest = wineAdviceRequestRepository.findById(WarId);
        Optional<Sommelier> optionalSommelier = sommelierRepository.findById(sommelierId);

        if (optionalWineAdviceRequest.isPresent() && optionalSommelier.isPresent()) {
            Sommelier sommelier = optionalSommelier.get();
            WineAdviceRequest war = optionalWineAdviceRequest.get();

            war.setSommelier(sommelier);
            wineAdviceRequestRepository.save(war);
        } else throw new RuntimeException("No wine advice request found with id: " + WarId + " or no sommelier found with id: " + sommelierId);
    }
}



