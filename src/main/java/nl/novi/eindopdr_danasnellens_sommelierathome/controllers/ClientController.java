package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.ClientInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.ClientUpdateDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientOutputDto>> getAllClients() {
        return ResponseEntity.ok().body(clientService.getAllClients());
    }

    @GetMapping("/{username}")
    public ResponseEntity<ClientOutputDto> getClientByUsername(@PathVariable("username") String username, @AuthenticationPrincipal UserDetails userDetails) {
        boolean isAdmin = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        boolean isClient = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_CLIENT"));
        boolean isSelf = username.equals(userDetails.getUsername());

        if ((isSelf && isClient) || isAdmin) {
            return ResponseEntity.ok().body(clientService.getClientByUsername(username));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping
    public ResponseEntity<ClientOutputDto> createClient(@Valid @RequestBody ClientInputDto clientInputDto) {
        ClientOutputDto clientOutputDto = clientService.createClient(clientInputDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}").buildAndExpand(clientOutputDto.getUsername()).toUri();
        return ResponseEntity.created(uri).body(clientOutputDto);
    }

    @PutMapping("/{username}")
    public ResponseEntity<ClientOutputDto> updateClientByUsername(@PathVariable("username") String username,
                                                                  @AuthenticationPrincipal UserDetails userDetails,
                                                                  @Valid @RequestBody ClientUpdateDto updatedClient) {
        boolean isAdmin = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        boolean isClient = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_CLIENT"));
        boolean isSelf = username.equals(userDetails.getUsername());

        if (isSelf && isClient || isAdmin) {
            ClientOutputDto dto = clientService.updateClientByUsername(username, updatedClient);
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Object> deleteClientByUsername(@PathVariable ("username") String username,
                                                        @AuthenticationPrincipal UserDetails userDetails) {
        boolean isAdmin = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        boolean isClient = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_CLIENT"));
        boolean isSelf = username.equals(userDetails.getUsername());

        if (isSelf && isClient || isAdmin) {
            clientService.deleteClientByUsername(username);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
