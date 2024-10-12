package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.ClientInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Component
public class ClientMapper {

    public static Client clientInputDtoToModel(ClientInputDto clientInputDto) {
        Client client = new Client();
        client.setUsername(clientInputDto.getUsername());
        client.setFirstName(clientInputDto.getFirstName());
        client.setLastName(clientInputDto.getLastName());
        client.setEmail(clientInputDto.getEmail());
        client.setPassword(clientInputDto.getPassword());
        client.setProfilePictureUrl(clientInputDto.getProfilePictureUrl());
        client.setMembership(clientInputDto.getMembership());

        return client;
    }

    public static ClientOutputDto clientModelToOutput(Client client) {
        ClientOutputDto clientOutputDto = new ClientOutputDto();
        clientOutputDto.setId(client.getId());
        clientOutputDto.setUsername(client.getUsername());
        clientOutputDto.setFirstName(client.getFirstName());
        clientOutputDto.setLastName(client.getLastName());
        clientOutputDto.setEmail(client.getEmail());
        clientOutputDto.setProfilePictureUrl(client.getProfilePictureUrl());
        //TODO Dit nog aanpassen naar roleName of roleId? En waar wordt die rol geassigned?
        /*        clientOutputDto.setRoleSet(client.getRoleSet());*/
        clientOutputDto.setMembership(client.getMembership());

        if (client.getWineAdviceRequestSet() != null) {
            Set<Long> wineAdviceRequestIdSet = new HashSet<>();

            for (WineAdviceRequest war : client.getWineAdviceRequestSet()) {
                wineAdviceRequestIdSet.add(war.getId());
            }
            clientOutputDto.setWineAdviceRequestIdSet(wineAdviceRequestIdSet);
        }
        return clientOutputDto;
    }

    //from list to list
    public static List<ClientOutputDto> clientModelListToOutputList(List<Client> clientList) {
        List<ClientOutputDto> clientOutputDtoList = new ArrayList<>();
        clientList.forEach( (client) -> clientOutputDtoList.add(clientModelToOutput(client)));
        return clientOutputDtoList;
    }
}
