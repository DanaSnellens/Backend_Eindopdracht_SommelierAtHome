package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.SommelierOutputDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


// Onderstaande ingevoegd n.a.v. TechItEasyFull Rowan
@Service
public class MyUserDetailService implements UserDetailsService {

    private final ClientService clientService;
    private final SommelierService sommelierService;

    public MyUserDetailService(ClientService clientService, SommelierService sommelierService) {
        this.clientService = clientService;
        this.sommelierService = sommelierService;
    }


    @Override
    public UserDetails loadUserByUserName(String UserName) {
        ClientOutputDto clientOutputDto = clientService.getClientByUsername(UserName);
        if (clientOutputDto == null) {
            throw new UsernameNotFoundException("User not found with username: " + UserName);
        }

        SommelierOutputDto sommelierOutputDto = sommelierService.getSommelierByUserName(UserName);
        if (sommelierOutputDto == null) {
            throw new UsernameNotFoundException("User not found with username: " + UserName);
        }

        String password = clientOutputDto.getPassword();

        Set<Authority> authorities = clientOutputDto.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(userName, password, grantedAuthorities);
    }
}

