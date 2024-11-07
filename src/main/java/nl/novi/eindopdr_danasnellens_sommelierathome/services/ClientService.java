package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.AddRolesInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.ClientInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.RecipeInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RecipeOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.exceptions.EntityAlreadyExistsException;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Recipe;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.ClientRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.RoleRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRequestRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import nl.novi.eindopdr_danasnellens_sommelierathome.config.SpringSecurityConfig.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.ClientMapper.*;
import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.RecipeMapper.recipeInputToModel;
import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.RecipeMapper.recipeModelToOutput;

@Service
@Data
public class ClientService {

    private final ClientRepository clientRepository;
    private final WineAdviceRequestRepository wineAdviceRequestRepository;
    private final WineAdviceRepository wineAdviceRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

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
    @Transactional
    public ClientOutputDto getClientByUsername(String username) {
        Optional<Client> optionalClient = clientRepository.findClientByUsername(username);
        if (optionalClient.isPresent()) {
            return clientModelToOutput(optionalClient.get());
        }
        else throw new UsernameNotFoundException("No user found with the username " + username);
    }

    public ClientOutputDto createClient(ClientInputDto clientInputDto) {

        Optional<Client> optionalClient = clientRepository.findClientByUsername(clientInputDto.getUsername());
        if (optionalClient.isEmpty()) {
            Client newClient = clientInputDtoToModel(clientInputDto);
            newClient.setPassword(passwordEncoder.encode(clientInputDto.getPassword()));

            Set<Role> clientRoleSet = newClient.getRoleSet();
            // Fetch the ROLE_CLIENT from the database
            Optional<Role> clientRoleOptional = Optional.ofNullable(roleRepository.findRoleByRoleName("ROLE_CLIENT"));
            Role clientRole = clientRoleOptional
                    .orElseThrow(() -> new EntityNotFoundException("ROLE_CLIENT not found"));

/*            // Convert the input DTO to a Client model
            Client client = clientInputDtoToModel(clientInputDto);*/

            // Add the ROLE_CLIENT to the client's roles
            newClient.getRoleSet().add(clientRole);

            // Save the client to the database
            Client savedClient = clientRepository.save(newClient);

            return clientModelToOutput(savedClient);
        } else {
            throw new EntityAlreadyExistsException("User with clientUsername: " + clientInputDto.getUsername() + "  already exists");
        }

/*        boolean clientExists = clientRepository.existsByUsername(clientInputDto.getUsername());
        if (clientExists) {
            throw new EntityAlreadyExistsException("User with clientUsername: " + clientInputDto.getUsername() + "  already exists");
        } else {
            clientInputDto.setPassword(passwordEncoder.encode(clientInputDto.getPassword()));
            boolean roleExists = roleRepository.existsByRoleName("ROLE_CLIENT");
            if (!roleExists) {
                Role clientRole = client.getRoleSet().add(clientRole);
                client.getRoleSet().add(clientRole);
                role.setRoleName("ROLE_CLIENT");
                roleRepository.save(role);
            }
            Client client = clientInputDtoToModel(clientInputDto);
            Client savedClient = clientRepository.save(client);
            return clientModelToOutput(savedClient);
        }*/



    }

    //@AuthenticationPrincipal UserDetails userDetails nog fixen (ook in controller). Zie huiswerkklas 16; 52 minuten
/*    public ClientOutputDto createClient(ClientInputDto clientInputDto, AddRolesInputDto addRolesInputDto) {
        Optional<Client> optionalClient = clientRepository.findClientByUsername(clientInputDto.getUsername());
        if (optionalClient.isEmpty()) {
            clientInputDto.setPassword(passwordEncoder.encode(clientInputDto.getPassword()));
            



            //Give the client a ROLE_cLIENT automaticly when created without having using the input dto

            Client client = clientRepository.save(clientInputDtoToModel(clientInputDto));
            return clientModelToOutput(client);
        }
        else throw new EntityAlreadyExistsException("User with clientUsername: " + clientInputDto.getUsername() + "already exists" );
    }*/

/*    public ClientOutputDto updateClientById(Long id, ClientInputDto updatedClient) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            return clientModelToOutput(optionalClient.get());
        }
        else throw new UsernameNotFoundException("No client found with id: " + id);
    }*/
    //TODO nog iets doen met updatedClient
    public ClientOutputDto updateClientByUsername(String clientUsername, ClientInputDto clientInputDto) {
        Optional<Client> optionalClient = clientRepository.findClientByUsername(clientUsername);
        if (optionalClient.isPresent()) {
            Client updatedClient = clientInputDtoToModel(clientInputDto);
            return clientModelToOutput(updatedClient);
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

    @Transactional
    public void deleteClientByUsername(String clientUsername) {
        Optional<Client> optionalClient = clientRepository.findClientByUsername(clientUsername);
        if (optionalClient.isPresent()) {
            clientRepository.deleteByUsername(clientUsername);
        }
        else throw new UsernameNotFoundException("No client found with username: " + clientUsername);
    }

/*    public void addRoleToClient(String clientUsername, String roleName) {
        Optional<Client> optionalClient = clientRepository.findClientByUsername(clientUsername);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.addRole(roleRepository.findRoleByRoleName(roleName));
            clientRepository.save(client);
        }
        else throw new UsernameNotFoundException("No client found with username: " + clientUsername);
    }*/
}

