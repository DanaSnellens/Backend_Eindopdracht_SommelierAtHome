package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.ClientInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceRequestOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineAdviceMapper.wineAdviceModelToOutput;
import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineAdviceRequestMapper.wineAdviceRequestModelToOutput;
//@Component
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

        //relaties: niet nodig want deze set de client niet zelf. Of toch wel?
        // Set the IDs of related entities (would be mapped to actual entities in service layer)
/*        client.setWineAdviceRequestSet(clientInputDto.getWineAdviceRequestIds().stream()
                .map(id -> {
                    WineAdviceRequest wineAdviceRequest = new WineAdviceRequest();
                    wineAdviceRequest.setId(id);
                    return wineAdviceRequest;
                }).collect(Collectors.toSet()));

        client.setWineAdviceSet(clientInputDTO.getWineAdviceIds().stream()
                .map(id -> {
                    WineAdvice wineAdvice = new WineAdvice();
                    wineAdvice.setId(id);
                    return wineAdvice;
                }).collect(Collectors.toSet()));*/

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

        //relaties

        if (client.getWineAdviceRequestSet() != null) {
            Set<WineAdviceRequestOutputDto> wineAdviceRequestOutputDtoSet = new HashSet<>();

            Set<WineAdviceRequest> WineAdviceRequestSet = client.getWineAdviceRequestSet();
            for (WineAdviceRequest war : WineAdviceRequestSet) {
                wineAdviceRequestOutputDtoSet.add(wineAdviceRequestModelToOutput(war));
            }
            clientOutputDto.setWineAdviceRequestOutputDtoSet(wineAdviceRequestOutputDtoSet);
        }
        if (client.getWineAdviceSet() != null) {
            Set<WineAdviceOutputDto> wineAdviceOutputDtoSet = new HashSet<>();

            Set<WineAdvice> WineAdviceSet = client.getWineAdviceSet();
            for (WineAdvice wa : WineAdviceSet) {
                wineAdviceOutputDtoSet.add(wineAdviceModelToOutput(wa));
            }
            clientOutputDto.setWineAdviceOutputDtoSet(wineAdviceOutputDtoSet);
        }
        return clientOutputDto;
    }
    //from list to list
    public static List<ClientOutputDto> clientModelListToOutputList(List<Client> clientList) {
        List<ClientOutputDto> clientOutputDtoList = new ArrayList<>();

        for (Client c : clientList) {
            clientOutputDtoList.add(clientModelToOutput(c));
        }
        return clientOutputDtoList;
    }
}
