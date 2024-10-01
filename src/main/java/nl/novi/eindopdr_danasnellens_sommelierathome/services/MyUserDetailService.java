package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDtoShort;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.SommelierOutputDtoShort;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.ClientRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.SommelierRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.ClientMapper.clientOutputDtoShort;
import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.SommelierMapper.sommelierOutputDtoShort;


// Onderstaande ingevoegd n.a.v. TechItEasyFull Rowan
@Service
@Data
public class MyUserDetailService implements UserDetailsService {

    private final ClientRepository clientRepository;
    private final SommelierRepository sommelierRepository;

//    private User user;

    @Override
    public UserDetails loadUserByUserName(String userName) throws UsernameNotFoundException {
        Optional<Client> optionalClient = clientRepository.findClientByUserName(userName);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            ClientOutputDtoShort clientOutputDtoShort = clientOutputDtoShort(client);
            return createUserDetails(clientOutputDtoShort);
        }

        Optional<Sommelier> optionalSommelier = sommelierRepository.findSommelierByUserName(userName);
        if (optionalClient.isPresent()) {
            Sommelier sommelier = optionalSommelier.get();
            SommelierOutputDtoShort sommelierOutputDtoShort = sommelierOutputDtoShort(sommelier);
            return createUserDetails(sommelierOutputDtoShort);
        }

        throw new UsernameNotFoundException("There is no user found with username: " + userName);
    }

    private UserDetails createUserDetails(ClientOutputDtoShort clientOutputDtoShort) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : clientOutputDtoShort.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new org.springframework.security.core.userdetails.User(
                clientOutputDtoShort.getUserName(),
                clientOutputDtoShort.getPassword(),
                authorities
        );
    }

    private UserDetails createUserDetails(SommelierOutputDtoShort sommelierOutputDtoShort) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : sommelierOutputDtoShort.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new org.springframework.security.core.userdetails.User(
                sommelierOutputDtoShort.getUserName(),
                sommelierOutputDtoShort.getPassword(),
                authorities
        );
    }
/*        User user = null;

        user = clientRepository.findClientByUserName(userName);
        if (user != null) {
            return createUserDetails(user);
        }

        user = sommelierRepository.findSommelierByUserName(userName);
        if (user != null) {
            return createUserDetails(user);
    }

}

    private UserDetails createUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                user.getAuthorities(user)
        );
    }*/


/*        ClientOutputDto clientOutputDto = clientService.getClientByUsername(userName);
        if (clientOutputDto == null) {
            throw new UsernameNotFoundException("There is no client found with username: " + userName);
        }

        SommelierOutputDto sommelierOutputDto = sommelierService.getSommelierByUserName(userName);
        if  (sommelierOutputDto == null) {
            throw new UsernameNotFoundException("There is no sommelier found with username: " + userName);
        }
        else {
            String password = clientOutputDto.getPassword();

            Set<Role> authorities = clientOutputDto.getAuthorities();
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Authority authority : authorities) {
                grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
            }

            return new org.springframework.security.core.userdetails.User(userName, password, grantedAuthorities);
        }*/


    }
}

