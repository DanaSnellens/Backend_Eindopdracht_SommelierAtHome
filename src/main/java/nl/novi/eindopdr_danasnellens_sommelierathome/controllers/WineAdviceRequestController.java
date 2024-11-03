package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.ClientInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceRequestInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.ClientOutputDtoShort;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceRequestOutputDto;

import nl.novi.eindopdr_danasnellens_sommelierathome.services.ClientService;
import nl.novi.eindopdr_danasnellens_sommelierathome.services.WineAdviceRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
        //Als er een WAR wordt aangemaakt, wordt deze automatisch gekoppeld aan de ingelogde client
        WineAdviceRequestOutputDto warOutputDto = wineAdviceRequestService.createWineAdviceRequest(wineAdviceRequestInputDto, userDetails.getUsername());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(warOutputDto.getId()).toUri();
        return ResponseEntity.created(uri).body(warOutputDto);
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
    @PutMapping("/{warId}/sommelier/{sommelierId}")
    public ResponseEntity<String> assignSommelierToWineAdviceRequest(@PathVariable ("warId")Long warId, @PathVariable ("sommelierId") String sommelierUsername) {
        wineAdviceRequestService.assignSommelierToWineAdviceRequest(warId, sommelierUsername);
        return ResponseEntity.ok("Sommelier " + sommelierUsername + " assigned to wineadvice request " + warId);
    }

    //Niet nodig? Want bij create wa wordt dit automatisch gedaan
/*
    @PutMapping("/{warId}/addwineadvice")
    public ResponseEntity<String> addWineAdviceToWineAdviceRequest(@PathVariable ("warId")Long warId, @Valid @RequestBody Long wineAdviceId) {
        wineAdviceRequestService.addWineAdviceToWineAdviceRequest(warId, wineAdviceId);
        return ResponseEntity.ok("Wineadvice " + wineAdviceId + " added to wineadvice request " + warId);
    }*/
}

