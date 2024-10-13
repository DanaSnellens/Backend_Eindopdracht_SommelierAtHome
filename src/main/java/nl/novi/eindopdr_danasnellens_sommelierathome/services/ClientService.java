package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.ClientInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.ClientRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRequestRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

/*    public ClientOutputDto getClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            return clientModelToOutput(optionalClient.get());
        }
        else throw new UsernameNotFoundException("No user found with id: " + id);
    }*/

    //TODO Deze verwijderen? Rowan heeft die eronder toegevoegd (optionalclient ipv clientModelToOutput)
    public ClientOutputDto getClientByUsername(String clientUsername) {
        Optional<Client> optionalClient = clientRepository.findClientByUsername(clientUsername);
        if (optionalClient.isPresent()) {
            return clientModelToOutput(optionalClient.get());
        }
        else throw new UsernameNotFoundException("No user found with the username " + clientUsername);
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
    public ClientOutputDto createClient(ClientInputDto clientInputDto, String clientUsername) {
        Optional<Client> optionalClient = clientRepository.findClientByUsername(clientUsername);
        if (optionalClient.isEmpty()) {
            Client client = clientRepository.save(clientInputDtoToModel(clientInputDto, clientUsername));
            return clientModelToOutput(client);
        }
        else {
            throw new UsernameNotFoundException("User with clientUsername: " + clientUsername + "already exists" );
        }
    }

/*    public ClientOutputDto updateClientById(Long id, ClientInputDto updatedClient) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            return clientModelToOutput(optionalClient.get());
        }
        else throw new UsernameNotFoundException("No client found with id: " + id);
    }*/
    //TODO nog iets doen met updatedClient
    public ClientOutputDto updateClientByUsername(String clientUsername, ClientInputDto updatedClient) {
        Optional<Client> optionalClient = clientRepository.findClientByUsername(clientUsername);
        if (optionalClient.isPresent()) {
            return clientModelToOutput(optionalClient.get());
        }
        else throw new UsernameNotFoundException("No client found with username: " + clientUsername);
    }

/*    public void deleteClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            clientRepository.deleteById(id);
        }
        else throw new UsernameNotFoundException("No user found with id: " + id);
    }*/

    public void deleteClientByUsername(String clientUsername) {
        Optional<Client> optionalClient = clientRepository.findClientByUsername(clientUsername);
        if (optionalClient.isPresent()) {
            clientRepository.deleteByUserName(clientUsername);
        }
        else throw new UsernameNotFoundException("No client found with username: " + clientUsername);
    }
}

