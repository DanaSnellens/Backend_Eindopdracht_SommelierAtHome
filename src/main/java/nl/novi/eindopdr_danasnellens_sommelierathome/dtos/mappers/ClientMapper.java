package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.ClientInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientMapper {
    //from dto to model
    public static Client clientInputDtoToModel(ClientInputDto clientInputDto, String userName) {
        Client client = new Client();
        //UserName krijgen we mee vanuit het object (via security) Zie hw-klas votes (16) 28 min.
        client.setUserName(userName);
        client.setFirstName(clientInputDto.getFirstName());
        client.setLastName(clientInputDto.getLastName());
        client.setEmail(clientInputDto.getEmail());
        client.setPassword(clientInputDto.getPassword());
        client.setProfilePictureUrl(clientInputDto.getProfilePictureUrl());
        client.setMembership(clientInputDto.getMembership());
        return client;
    }
    //from model to dto
    public static ClientOutputDto clientModelToOutput(Client client) {
        ClientOutputDto clientOutputDto = new ClientOutputDto();
        clientOutputDto.setId(client.getId());
        clientOutputDto.setUserName(client.getUserName());
        clientOutputDto.setFirstName(client.getFirstName());
        clientOutputDto.setLastName(client.getLastName());
        clientOutputDto.setEmail(client.getEmail());
        clientOutputDto.setPassword(client.getPassword());
        clientOutputDto.setProfilePictureUrl(client.getProfilePictureUrl());
        clientOutputDto.setMembership(client.getMembership());
        return clientOutputDto;
    }
    //from list to list
    public static List<ClientOutputDto> clientModelListToOutputList(List<Client> clientList) {
        List<ClientOutputDto> clientOutputDtoList = new ArrayList<>();
        //for loop
        for (Client c : clientList) {
            clientOutputDtoList.add(clientModelToOutput(c));
        }
        // lambda
        // clientList.forEach(c -> clientOutputDtoList.add(clientFromModelToOutput(c)));
        return clientOutputDtoList;
    }
}
