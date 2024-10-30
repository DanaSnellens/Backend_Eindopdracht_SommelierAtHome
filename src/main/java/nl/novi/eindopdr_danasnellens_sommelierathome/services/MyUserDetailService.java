package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.User;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> optionalClient = clientRepository.findClientByUsername(username);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            return createUserDetails(client);
        }

        Optional<Sommelier> optionalSommelier = sommelierRepository.findSommelierByUsername(username);
        if (optionalSommelier.isPresent()) {
            Sommelier sommelier = optionalSommelier.get();
            return createUserDetails(sommelier);
        }

        throw new UsernameNotFoundException("There is no user found with username: " + username);
    }

//    private UserDetails createUserDetails(User user) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        user.getRoleSet().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" +role.getRoleName())));
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                authorities
//        );
//    }

    //Onderstaande vervangen door bovenstaande
    private UserDetails createUserDetails(Client client) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        client.getRoleSet().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new org.springframework.security.core.userdetails.User(
                client.getUsername(),
                client.getPassword(),
                authorities
        );
    }

    private UserDetails createUserDetails(Sommelier sommelier) {
        List<GrantedAuthority> authorities = new ArrayList<>();
       sommelier.getRoleSet().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new org.springframework.security.core.userdetails.User(
                sommelier.getUsername(),
                sommelier.getPassword(),
                authorities
        );
    }

}


