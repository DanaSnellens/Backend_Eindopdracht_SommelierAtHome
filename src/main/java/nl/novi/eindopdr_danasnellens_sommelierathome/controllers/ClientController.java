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
//TODO Deze kan weg? Zie ook ClientService.java. Zo niet: dan ook auth toevoegen
/*    @GetMapping("/{id}")
    public ResponseEntity<ClientOutputDto> getClientById(@PathVariable ("id") Long id) {
        return ResponseEntity.ok().body(clientService.getClientById(id));
    }*/

    @GetMapping("/{username}")
    public ResponseEntity<ClientOutputDto> getClientByUsername(@PathVariable("username") String clientUsername, @AuthenticationPrincipal UserDetails userDetails) {
        if (clientUsername.equals(userDetails.getUsername())) {
            return ResponseEntity.ok().body(clientService.getClientByUsername(clientUsername));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping
    public ResponseEntity<ClientOutputDto> createClient(@Valid @RequestBody ClientInputDto clientInputDto) {
        ClientOutputDto clientOutputDto = clientService.createClient(clientInputDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientOutputDto.getId()).toUri();
        return ResponseEntity.created(uri).body(clientOutputDto);
    }

    @PutMapping("/{clientUsername}")
    public ResponseEntity<ClientOutputDto> updateClientByUsername(@PathVariable String clientUsername, @Valid @RequestBody ClientInputDto updatedClient) {
        ClientOutputDto clientOutputDto = clientService.updateClientByUsername(clientUsername, updatedClient);
        return ResponseEntity.ok().body(clientOutputDto);
    }

    @DeleteMapping("/{clientUsername}")
    public ResponseEntity<Object> deleteClientByUsername(@PathVariable String clientUsername) {
        clientService.deleteClientByUsername(clientUsername);
        return ResponseEntity.noContent().build();
    }
}
