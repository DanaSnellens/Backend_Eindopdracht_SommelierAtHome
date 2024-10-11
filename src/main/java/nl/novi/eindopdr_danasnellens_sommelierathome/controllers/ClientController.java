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
    public ResponseEntity<ClientOutputDto> getClientById(@PathVariable("id") Long id,
                                                         @AuthenticationPrincipal UserDetails userDetails) {
        MyUserDetails myUserDetails = (MyUserDetails) userDetails;
        if (id.equals(myUserDetails.getId())) {
            return ResponseEntity.ok().body(clientService.getClientById(id));
        }
        else {
            //TODO vervangen door nettere exception
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<ClientOutputDto> getClientByUsername(@PathVariable("userName") String userName,
                                                               @AuthenticationPrincipal UserDetails userDetails) {
        if (userName.equals(userDetails.getUsername())) {
            return ResponseEntity.ok().body(clientService.getClientByUsername(userName));
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    //create
    @PostMapping
    public ResponseEntity<ClientOutputDto> createClient(@Valid @RequestBody ClientInputDto clientInputDto, @AuthenticationPrincipal UserDetails userDetails) {
        ClientOutputDto clientOutputDto = clientService.createClient(clientInputDto, userDetails.getUsername());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientOutputDto.getId()).toUri();
        return ResponseEntity.created(uri).body(clientOutputDto);
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
