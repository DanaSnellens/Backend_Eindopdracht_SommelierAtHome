package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import lombok.Data;
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

@Service
@Data
public class MyUserDetailService implements UserDetailsService {

    private final ClientRepository clientRepository;
    private final SommelierRepository sommelierRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Client> optionalClient = clientRepository.findClientByUserName(userName);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            return createUserDetails(client);
        }

        Optional<Sommelier> optionalSommelier = sommelierRepository.findSommelierByUserName(userName);
        if (optionalSommelier.isPresent()) {
            Sommelier sommelier = optionalSommelier.get();
            return createUserDetails(sommelier);
        }

        throw new UsernameNotFoundException("There is no user found with username: " + userName);
    }

    private UserDetails createUserDetails(Client client) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        client.getRoleSet().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new org.springframework.security.core.userdetails.User(
                client.getUserName(),
                client.getPassword(),
                authorities
        );
    }

    private UserDetails createUserDetails(Sommelier sommelier) {
        List<GrantedAuthority> authorities = new ArrayList<>();
       sommelier.getRoleSet().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new org.springframework.security.core.userdetails.User(
                sommelier.getUserName(),
                sommelier.getPassword(),
                authorities
        );
    }
}


