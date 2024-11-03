package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import jakarta.validation.Valid;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.AssignSommInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceRequestInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineAdviceRequestMapper;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceRequestOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.ClientRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.SommelierRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRequestRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineAdviceRequestMapper.*;

@Service
public class WineAdviceRequestService {
    private final WineAdviceRequestRepository wineAdviceRequestRepository;
    private final SommelierRepository sommelierRepository;
    private final ClientRepository clientRepository;
    private final MyUserDetailService myUserDetailService;
    public WineAdviceRequestService(WineAdviceRequestRepository wineAdviceRequestRepository, SommelierRepository sommelierRepository, ClientRepository clientRepository, MyUserDetailService myUserDetailService) {
        this.wineAdviceRequestRepository = wineAdviceRequestRepository;
        this.sommelierRepository = sommelierRepository;
        this.clientRepository = clientRepository;
        this.myUserDetailService = myUserDetailService;
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
     //TODO Get authenticated client username from JWT
     // String username = loadUserByUsername();

     //TODO Onderstaande aanpassen naar UserDetails(AService?)
     // Fetch the client by username (this assumes username is unique)
     Client client = clientRepository.findClientByUsername(clientUsername)
             .orElseThrow(() -> new RuntimeException("No client found with clientUsername: " + clientUsername));
     // Create a new WineAdviceRequest and associate it with the client
     WineAdviceRequest war = wineAdviceRequestInputToModel(warInputDto, client);

     //save the request
     WineAdviceRequest savedWar = wineAdviceRequestRepository.save(war);

     //return the saved request
     return wineAdviceRequestModelToOutput(savedWar);
 }
/*         WineAdviceRequest war = wineAdviceRequestInputToModel(warInputDto, optionalClient.get());
         war = wineAdviceRequestRepository.save(war);
         return wineAdviceRequestModelToOutput(war);
*//*            Client client = optionalClient.get();
            WineAdviceRequest war = wineAdviceRequestInputToModel(warInputDto, client);
            WineAdviceRequest savedWar = wineAdviceRequestRepository.save(war);
            return wineAdviceRequestModelToOutput(savedWar);*//**//*
        }
        else throw new RuntimeException("No client found with clientUsername: " + clientUsername);
    }*/

    public WineAdviceRequestOutputDto updateWineAdviceRequest(Long warId, WineAdviceRequestInputDto updatedWineAdviceRequest) {
        Optional<WineAdviceRequest> optionalWineAdviceRequest = wineAdviceRequestRepository.findById(warId);
        if (optionalWineAdviceRequest.isPresent()) {
            return wineAdviceRequestModelToOutput(optionalWineAdviceRequest.get());
        }
        else throw new RuntimeException("No wine advice request found with warId: " + warId);
    }

    public void deleteWineAdviceRequestById(Long warId) {
        Optional<WineAdviceRequest> optionalWineAdviceRequest = wineAdviceRequestRepository.findById(warId);
        if (optionalWineAdviceRequest.isPresent()) {
            wineAdviceRequestRepository.deleteById(warId);
        } else throw new RuntimeException("No wine advice request found with warId: " + warId);
    }

    //RELATIES
    //TODO moet ik hier geen gebruik maken van DTO en mapper?
    public void assignSommelierToWineAdviceRequest(Long warId, @Valid AssignSommInputDto assignSommInputDto) {
        Optional<WineAdviceRequest> optionalWineAdviceRequest = wineAdviceRequestRepository.findById(warId);
        Optional<Sommelier> optionalSommelier = sommelierRepository.findSommelierByUsername(assignSommInputDto.getSommelierUsername());

        if (optionalWineAdviceRequest.isPresent() && optionalSommelier.isPresent()) {
            Sommelier sommelier = optionalSommelier.get();
            WineAdviceRequest war = optionalWineAdviceRequest.get();
            war.setSommelier(sommelier);
            wineAdviceRequestRepository.save(war);
        } else throw new RuntimeException("No wine advice request found with id: " + warId + " or no sommelier found with id: " + assignSommInputDto.getSommelierUsername());
    }

    //Niet nodig? Want bij create wa wordt dit automatisch gedaan

/*    public void addWineAdviceToWineAdviceRequest(Long warId, @Valid Long wineAdviceId) {
        Optional<WineAdviceRequest> optionalWineAdviceRequest = wineAdviceRequestRepository.findById(warId);
    }*/
}



