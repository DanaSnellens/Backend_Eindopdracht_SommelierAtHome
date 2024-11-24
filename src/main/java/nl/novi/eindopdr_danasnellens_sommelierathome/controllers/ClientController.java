package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.ClientInputDto;
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
        if (username.equals(userDetails.getUsername()) || userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
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
//TODO Hier @AuthenticationPrincipal toegevoegd, maar nog niet getest. Als het werkt:zelfde aan somm toevoegen
    @PutMapping("/{username}")
    public ResponseEntity<ClientOutputDto> updateClientByUsername(@PathVariable ("username") String username, @AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody ClientInputDto updatedClient) {
        if (username.equals(userDetails.getUsername()) || userDetails.getAuthorities().equals("ROLE_ADMIN")) {
            return ResponseEntity.ok().body(clientService.getClientByUsername(username));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Object> deleteClientByUsername(@PathVariable ("username") String username ) {
        clientService.deleteClientByUsername(username);
        return ResponseEntity.noContent().build();
    }
}
