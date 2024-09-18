package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceRequestInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceRequestOutputDto;

import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.services.ClientService;
import nl.novi.eindopdr_danasnellens_sommelierathome.services.WineAdviceRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wineadvicerequests")
public class WineAdviceRequestController {
    private final WineAdviceRequestService wineAdviceRequestService;
    private final ClientService clientService;
    public WineAdviceRequestController(WineAdviceRequestService wineAdviceRequestService, ClientService clientService) {
        this.wineAdviceRequestService = wineAdviceRequestService;
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<WineAdviceRequestOutputDto>> getAllWineAdviceRequests() {
        return ResponseEntity.ok().body(wineAdviceRequestService.getAllWineAdviceRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WineAdviceRequestOutputDto> getWineAdviceRequestById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(wineAdviceRequestService.getWineAdviceRequestById(id));
    }

    @PostMapping
    public ResponseEntity<WineAdviceRequestOutputDto> createWineAdviceRequest
            (@Valid @RequestBody WineAdviceRequestInputDto wineAdviceRequestInputDto, @AuthenticationPrincipal UserDetails userDetails) {
        // TODO Is onderstaande wel nodig (met Rowan 13-9), want dit gebeurt al in de mapper of niet?
                /*Client c = clientService.getClientByUsernameClient(userDetails.getUsername());
                wineAdviceRequestInputDto.setClient(c);*/
                WineAdviceRequestOutputDto wineAdviceRequestOutputDto = wineAdviceRequestService.createWineAdviceRequest(wineAdviceRequestInputDto);
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(wineAdviceRequestOutputDto.getId()).toUri();
                return ResponseEntity.created(uri).body(wineAdviceRequestOutputDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WineAdviceRequestOutputDto> updateWineAdviceRequest(@PathVariable Long id, @Valid @RequestBody WineAdviceRequestInputDto updatedWineAdviceRequest) {
        WineAdviceRequestOutputDto wineAdviceRequestOutputDto = wineAdviceRequestService.updateWineAdviceRequest(id, updatedWineAdviceRequest);
        return ResponseEntity.ok().body(wineAdviceRequestOutputDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWineAdviceRequestById(@PathVariable Long id) {
        wineAdviceRequestService.deleteWineAdviceRequestById(id);
        return ResponseEntity.noContent().build();
    }

    //RELATIES
    //TODO is sommelierId een PathVariable of RequestParam? Dit ook checken bij WA-Wine.(@PathVariable: when the variable is part of the resource identity (eg /users/123) --> Extracts value from URI path. @RequestParam: when the variable is part of the request, like option/filter (eg /users?userId=123) --> Extracts value from the query string.
        @PutMapping("/{id}/sommelier/{sommelierId}")
    public ResponseEntity<String> assignSommelierToWineAdviceRequest(@PathVariable ("id")Long id, @PathVariable ("sommelierId") Long sommelierId) {
        wineAdviceRequestService.assignSommelierToWineAdviceRequest(id, sommelierId);
        return ResponseEntity.ok("Sommelier " + sommelierId + " assigned to wineadvice request " + id);
    }
}
