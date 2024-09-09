package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.ClientInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/clients")
public class ClientController {

    //service
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //get All
    @GetMapping
    public ResponseEntity<List<ClientOutputDto>> getAllClients() {
        return ResponseEntity.ok().body(clientService.getAllClients());
    }

    //get One
    @GetMapping("/{id}")
    public ResponseEntity<ClientOutputDto> getClientById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(clientService.getClientById(id));
    }

    @GetMapping("/{userName}")
    public ResponseEntity<ClientOutputDto> getClientByUsername(@PathVariable("userName") String userName) {
        return ResponseEntity.ok().body(clientService.getClientByUsername(userName));
    }

    //create
    @PostMapping
    public ResponseEntity<ClientOutputDto> createClient(@RequestBody ClientInputDto clientInputDto, @AuthenticationPrincipal UserDetails userDetails) {
        ClientOutputDto clientOutputDto = clientService.createClient(clientInputDto, userDetails.getUsername());

        //TODO URI toevoegen
        return ResponseEntity.created(null).body(clientOutputDto);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<ClientOutputDto> updateClientById(@PathVariable Long id, @Valid @RequestBody ClientInputDto updatedClient) {
        ClientOutputDto clientOutputDto = clientService.updateClientById(id, updatedClient);
        return ResponseEntity.ok().body(clientOutputDto);
    }
    @PutMapping("/{userName}")
    public ResponseEntity<ClientOutputDto> updateClientByUserName(@PathVariable String userName, @Valid @RequestBody ClientInputDto updatedClient) {
        ClientOutputDto clientOutputDto = clientService.updateClientByUserName(userName, updatedClient);
        return ResponseEntity.ok().body(clientOutputDto);
    }
    //Delete

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClientById(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<Object> deleteClientByUserName(@PathVariable String userName) {
        clientService.deleteClientByUserName(userName);
        return ResponseEntity.noContent().build();
    }
}
