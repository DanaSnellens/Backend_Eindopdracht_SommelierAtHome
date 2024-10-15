package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceRequestInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineAdviceRequestMapper;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceRequestOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.ClientRepository;
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
    private final ClientRepository clientRepository;

    public WineAdviceRequestService(WineAdviceRequestRepository wineAdviceRequestRepository, SommelierRepository sommelierRepository, ClientRepository clientRepository) {
        this.wineAdviceRequestRepository = wineAdviceRequestRepository;
        this.sommelierRepository = sommelierRepository;
        this.clientRepository = clientRepository;
    }

    public List<WineAdviceRequestOutputDto> getAllWineAdviceRequests() {
        List<WineAdviceRequest> wineAdviceRequestList = wineAdviceRequestRepository.findAll();
        return wineAdviceRequestModelListToOutputList(wineAdviceRequestList);
    }

    public WineAdviceRequestOutputDto getWineAdviceRequestById(Long warId) {
        Optional<WineAdviceRequest> optionalWineAdviceRequest = wineAdviceRequestRepository.findById(warId);
        if (optionalWineAdviceRequest.isPresent()) {
            return wineAdviceRequestModelToOutput(optionalWineAdviceRequest.get());
        }
        else throw new RuntimeException("No wine advice request found with warId: " + warId);
    }

    public WineAdviceRequestOutputDto createWineAdviceRequest(WineAdviceRequestInputDto warInputDto, String clientUsername) {
        Optional<Client> optionalClient = clientRepository.findByUsername(clientUsername);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            WineAdviceRequest war = wineAdviceRequestInputToModel(warInputDto, client);
            WineAdviceRequest savedWar = wineAdviceRequestRepository.save(war);
            return wineAdviceRequestModelToOutput(savedWar);
        }
        else throw new RuntimeException("No client found with clientUsername: " + clientUsername);
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
    public void assignSommelierToWineAdviceRequest(Long warId, String sommelierUsername) {
        Optional<WineAdviceRequest> optionalWineAdviceRequest = wineAdviceRequestRepository.findById(warId);
        Optional<Sommelier> optionalSommelier = sommelierRepository.findById(sommelierUsername);

        if (optionalWineAdviceRequest.isPresent() && optionalSommelier.isPresent()) {
            Sommelier sommelier = optionalSommelier.get();
            WineAdviceRequest war = optionalWineAdviceRequest.get();
            war.setSommelier(sommelier);
            wineAdviceRequestRepository.save(war);
        } else throw new RuntimeException("No wine advice request found with id: " + warId + " or no sommelier found with id: " + sommelierUsername);
    }
}



