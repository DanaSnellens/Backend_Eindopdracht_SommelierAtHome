package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Client;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.ClientRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.services.ClientService;
import nl.novi.eindopdr_danasnellens_sommelierathome.services.WineAdviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wineadvices")
public class WineAdviceController {
    private final WineAdviceService wineAdviceService;
    private final ClientService clientService;
    public WineAdviceController(WineAdviceService wineAdviceService, ClientService clientService) {
        this.wineAdviceService = wineAdviceService;
        this.clientService = clientService;
    }
    @GetMapping
    public ResponseEntity<List<WineAdviceOutputDto>> getAllWineAdvices() {
        return ResponseEntity.ok().body(wineAdviceService.getAllWineAdvices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WineAdviceOutputDto> getWineAdviceById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(wineAdviceService.getWineAdviceById(id));
    }

    @PostMapping
    public ResponseEntity<WineAdviceOutputDto> createWineAdvice
            (@Valid @RequestBody WineAdviceInputDto wineAdviceInputDto, @AuthenticationPrincipal UserDetails userDetails) {
        //Als er een WA wordt aangemaakt, wordt deze automatisch gekoppeld aan de bijbehorende WAR (+ ingelogde client en ge-assignde sommelier)
        //De ge-assignde sommelier kan een Wine(set) toevoegen
        //Is de UserName van de ingelogde somm gelijk aan de sommelier van de WAR? --> helperfunctie
/*        WineAdviceRequest war = wineAdviceService.getWineAdviceRequestById(wineAdviceInputDto.getWineAdviceRequestId());

        wineAdviceInputDto.setWineAdviceRequest(war);*/
        WineAdviceOutputDto wineAdviceOutput = wineAdviceService.createWineAdvice(wineAdviceInputDto, userDetails.getUsername());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(wineAdviceOutput.getId()).toUri();
        return ResponseEntity.created(uri).body(wineAdviceOutput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WineAdviceOutputDto> updateWineAdvice(@PathVariable Long id, @Valid @RequestBody WineAdviceInputDto updatedWineAdvice) {
        WineAdviceOutputDto wineAdviceOutputDto = wineAdviceService.updateWineAdvice(id, updatedWineAdvice);
        return ResponseEntity.ok().body(wineAdviceOutputDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWineAdviceById(@PathVariable Long id) {
        wineAdviceService.deleteWineAdviceById(id);
        return ResponseEntity.noContent().build();
    }

    //RELATIES
    @PutMapping("/{id}/wine/{wineId}")
    public ResponseEntity<String> assignWineToWineAdvice(@PathVariable ("id")Long id, @PathVariable ("wineId") Long wineId) {
        wineAdviceService.assignWineToWineAdvice(id, wineId);
        return ResponseEntity.ok("Wine " + wineId + " assigned to wineadvice " + id);
    }

}
