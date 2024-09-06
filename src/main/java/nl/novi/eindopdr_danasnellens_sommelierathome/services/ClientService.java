package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.ClientRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Get All
//    @Override
    public List<ClientOutputDto> getAllClients() {
        List<Client> clientList = clientRepository.findAll();
        return clientModelListToOutputList(clientList);
    }

    // Get One
//    @Override
    public ClientOutputDto getClientByUsername(String userName) {
        Optional<Client> optionalClient = clientRepository.findByUserName(userName);
        if (optionalClient.isPresent()) {
            return clientFromModelToOutputDto(optionalClient.get());
        }
        else throw new UsernameNotFoundException("No user found with the username " + userName);
    }

//    @Override
    public ClientOutputDto getClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            return clientFromModelToOutputDto(optionalClient.get());
        }
        else throw new UsernameNotFoundException("No user found with id: " + id);
    }

    // Create
    //@AuthenticationPrincipal UserDetails userDetails nog fixen (ook in controller). Zie huiswerkklas 16; 52 minuten
//    @Override
    public ClientOutputDto createClient(ClientInputDto clientInputDto, String userName  ) {
        Client c = clientRepository.save(clientFromInputDtoToModel(clientInputDto, userName));
        return clientFromModelToOutputDto(c);
    }

    // Update
//    @Override
    public ClientOutputDto updateClient (String userName, ClientInputDto updatedClient) {
        Optional<Client> optionalClient =clientRepository.findByUserName(userName);
        if (optionalClient.isPresent()) {
            return clientFromModelToOutputDto(optionalClient.get());
        }
        else throw new UsernameNotFoundException("No user found with username: " + userName);
    }

    // Delete
    public void deleteClientByUserName(String userName) {
        Optional<Client> optionalClient = clientRepository.findByUserName(userName);
        if (optionalClient.isPresent()) {
            clientRepository.deleteByUserName(userName);
        }
        else throw new UsernameNotFoundException("No user found with username: " + userName);
    }
}

