package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.ClientInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDtoShort;
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

    public static Client clientInputDtoToModel(ClientInputDto clientInputDto) {
        Client client = new Client();
        client.setUserName(clientInputDto.getUserName());
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
        clientOutputDto.setUserName(client.getUserName());
        clientOutputDto.setFirstName(client.getFirstName());
        clientOutputDto.setLastName(client.getLastName());
        clientOutputDto.setEmail(client.getEmail());
        clientOutputDto.setRoleSet(client.getRoleSet());
        clientOutputDto.setMembership(client.getMembership());

        if (client.getWineAdviceRequestSet() != null) {
            Set<Long> wineAdviceRequestIdOutputDtoSet = new HashSet<>();

            for (WineAdviceRequest war : client.getWineAdviceRequestSet()) {
                wineAdviceRequestIdOutputDtoSet.add(war.getId());
            }
            clientOutputDto.setWineAdviceRequestIdOutputDtoSet(wineAdviceRequestIdOutputDtoSet);
        }
        return clientOutputDto;
    }



        //TODO Niet nodig want wordt niet direct aan de client gekoppeld maar aan de WAR??


/*        if (client.getWineAdviceSet() != null) {
            Set<Long> wineAdviceIdOutputDtoSet = new HashSet<>();

            for (WineAdvice wa : client.getWineAdviceSet()) {
                wineAdviceIdOutputDtoSet.add(wa.getId());
            }
            clientOutputDto.setWineAdviceIdOutputDtoSet(wineAdviceIdOutputDtoSet);
        }
        return clientOutputDto;
    }*/
// TODO Niet nodig want ik gebruik nu alleen ID's?

/*    public static ClientOutputDtoShort clientOutputDtoShort(Client client) {
        ClientOutputDtoShort clientOutputDtoShort = new ClientOutputDtoShort();
        clientOutputDtoShort.setId(client.getId());
        clientOutputDtoShort.setFirstName(client.getUserName());
        clientOutputDtoShort.setLastName(client.getLastName());
        clientOutputDtoShort.setRoleSet(client.getRoleSet());

        //relaties
        //TODO mapper toevoegen voor relaties clientOutputDtoShort
        return clientOutputDtoShort;
    }*/

    //from list to list
    public static List<ClientOutputDto> clientModelListToOutputList(List<Client> clientList) {
        List<ClientOutputDto> clientOutputDtoList = new ArrayList<>();

        for (Client c : clientList) {
            clientOutputDtoList.add(clientModelToOutput(c));
        }
        return clientOutputDtoList;
    }
}
