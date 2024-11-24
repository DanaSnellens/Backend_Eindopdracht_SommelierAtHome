package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.ClientInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;
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
        client.setProfilePictureAlt(clientInputDto.getProfilePictureAlt());
        //ROLE?
        client.setMembership(clientInputDto.getMembership());

        return client;
    }

    //TODO: UpdateDTO maken (net als bij wine en recipe)

    public static ClientOutputDto clientModelToOutput(Client client) {
        ClientOutputDto clientOutputDto = new ClientOutputDto();
        clientOutputDto.setId(client.getId());
        clientOutputDto.setUsername(client.getUsername());
        clientOutputDto.setFirstName(client.getFirstName());
        clientOutputDto.setLastName(client.getLastName());
        clientOutputDto.setEmail(client.getEmail());
        clientOutputDto.setProfilePictureUrl(client.getProfilePictureUrl());
        clientOutputDto.setMembership(client.getMembership());

        if (client.getRoleSet() != null) {
            Set<String> roleNameSet = new HashSet<>();
            for (Role r : client.getRoleSet()) {
                roleNameSet.add(r.getRoleName());
            }
            clientOutputDto.setRoleNameSet(roleNameSet);
        }

        if (client.getWineAdviceRequestSet() != null) {
            Set<Long> wineAdviceRequestIdSet = new HashSet<>();
            for (WineAdviceRequest war : client.getWineAdviceRequestSet()) {
                wineAdviceRequestIdSet.add(war.getId());
            }
            clientOutputDto.setWineAdviceRequestIdSet(wineAdviceRequestIdSet);
        }
        return clientOutputDto;
    }

    public static List<ClientOutputDto> clientModelListToOutputList(List<Client> clientList) {
        List<ClientOutputDto> clientOutputDtoList = new ArrayList<>();
        clientList.forEach((client) -> clientOutputDtoList.add(clientModelToOutput(client)));
        return clientOutputDtoList;
    }
}
