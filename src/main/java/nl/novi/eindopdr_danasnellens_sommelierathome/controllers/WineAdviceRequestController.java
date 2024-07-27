package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceRequestInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceRequestOutputDto;

import nl.novi.eindopdr_danasnellens_sommelierathome.services.WineAdviceRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/wineadvicerequests")
public class WineAdviceRequestController {
    private final WineAdviceRequestService wineAdviceRequestService;

    public WineAdviceRequestController(WineAdviceRequestService wineAdviceRequestService) {
        this.wineAdviceRequestService = wineAdviceRequestService;
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
            (@Valid @RequestBody WineAdviceRequestInputDto wineAdviceRequestInputDto) {
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
}
