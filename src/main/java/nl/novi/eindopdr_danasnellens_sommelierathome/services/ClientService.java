package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import jakarta.validation.Valid;
import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.ClientInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceRequestInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDtoShort;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.ClientRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRequestRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.ClientMapper.*;

@Service
@Data
public class ClientService {

    private final ClientRepository clientRepository;
    private final WineAdviceRequestRepository wineAdviceRequestRepository;
    private final WineAdviceRepository wineAdviceRepository;

    public List<ClientOutputDto> getAllClients() {
        List<Client> clientList = clientRepository.findAll();
        return clientModelListToOutputList(clientList);
    }

    public ClientOutputDto getClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            return clientModelToOutput(optionalClient.get());
        }
        else throw new UsernameNotFoundException("No user found with id: " + id);
    }

    //TODO Deze verwijderen? Rowan heeft die eronder toegevoegd (optionalclient ipv clientModelToOutput)
    public ClientOutputDto getClientByUsername(String userName) {
        Optional<Client> optionalClient = clientRepository.findClientByUserName(userName);
        if (optionalClient.isPresent()) {
            return clientModelToOutput(optionalClient.get());
        }
        else throw new UsernameNotFoundException("No user found with the username " + userName);
    }
/*    public Client getClientByUsernameClient(String userName) {
        Optional<Client> optionalClient = clientRepository.findClientByUserName(userName);
        if (optionalClient.isPresent()) {
            return optionalClient.get();
        }
        else throw new UsernameNotFoundException("No user found with the username " + userName);
    }*/

    // Create
    //@AuthenticationPrincipal UserDetails userDetails nog fixen (ook in controller). Zie huiswerkklas 16; 52 minuten
    public ClientOutputDto createClient(ClientInputDto clientInputDto, String userName) {
        Optional<Client> optionalClient = clientRepository.findClientByUserName(userName);
        if (optionalClient.isEmpty()) {
            Client client = clientRepository.save(clientInputDtoToModel(clientInputDto, userName));
            return clientModelToOutput(client);
        }
        else {
            throw new UsernameNotFoundException("User with username: " + userName + "already exists" );
        }
    }

    // Update
    public ClientOutputDto updateClientById(Long id, ClientInputDto updatedClient) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            return clientModelToOutput(optionalClient.get());
        }
        else throw new UsernameNotFoundException("No client found with id: " + id);
    }
    public ClientOutputDto updateClientByUserName (String userName, ClientInputDto updatedClient) {
        Optional<Client> optionalClient = clientRepository.findClientByUserName(userName);
        if (optionalClient.isPresent()) {
            return clientModelToOutput(optionalClient.get());
        }
        else throw new UsernameNotFoundException("No client found with username: " + userName);
    }

    // Delete
    public void deleteClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            clientRepository.deleteById(id);
        }
        else throw new UsernameNotFoundException("No user found with id: " + id);
    }

    public void deleteClientByUserName(String userName) {
        Optional<Client> optionalClient = clientRepository.findClientByUserName(userName);
        if (optionalClient.isPresent()) {
            clientRepository.deleteByUserName(userName);
        }
        else throw new UsernameNotFoundException("No user found with username: " + userName);
    }


    public void assignWineAdviceRequestToClient(Long id, Long wineAdviceRequestId) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        Optional<WineAdviceRequest> optionalWineAdviceRequest = wineAdviceRequestRepository.findById(wineAdviceRequestId);

        if (optionalClient.isPresent() && optionalWineAdviceRequest.isPresent()) {
            //TODO klopt dit? In de techItEasy uitwerkingen wordt var gebruikt
            Client c = optionalClient.get();
            WineAdviceRequest war = optionalWineAdviceRequest.get();

            c.setWineAdviceRequest()
        }
    }

    }


}

