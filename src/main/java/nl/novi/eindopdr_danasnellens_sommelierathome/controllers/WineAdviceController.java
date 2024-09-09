package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.services.WineAdviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/wineadvices")
public class WineAdviceController {
    private final WineAdviceService wineAdviceService;
    public WineAdviceController(WineAdviceService wineAdviceService) {
        this.wineAdviceService = wineAdviceService;
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
            (@Valid @RequestBody WineAdviceInputDto wineAdviceInputDto) {
                WineAdviceOutputDto wineAdviceOutput = wineAdviceService.createWineAdvice(wineAdviceInputDto);
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

}
