package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.SommelierInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.SommelierOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.services.SommelierService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sommeliers")
public class SommelierController {
    private final SommelierService sommelierService;
    public SommelierController(SommelierService sommelierService) {
        this.sommelierService = sommelierService;
    }

    @GetMapping
    public ResponseEntity<List<SommelierOutputDto>> getAllSommeliers() {
        return ResponseEntity.ok().body(sommelierService.getAllSommeliers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SommelierOutputDto> getSommelierById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(sommelierService.getSommelierById(id));
    }

    @GetMapping("/{userName}")
    public ResponseEntity<SommelierOutputDto> getSommelierByUsername(@PathVariable("userName") String userName) {
        return ResponseEntity.ok().body(sommelierService.getSommelierByUserName(userName));
    }

    @PostMapping
    public ResponseEntity<SommelierOutputDto> createSommelier(@Valid @RequestBody SommelierInputDto sommelierInputDto,
                                                              @AuthenticationPrincipal UserDetails userDetails) {
        SommelierOutputDto sommelierOutputDto = sommelierService.createSommelier(sommelierInputDto, userDetails.getUsername());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sommelierOutputDto.getId()).toUri();
        return ResponseEntity.created(uri).body(sommelierOutputDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SommelierOutputDto> updateSommelierById(@PathVariable Long id, @Valid @RequestBody SommelierInputDto updatedSommelier) {
        SommelierOutputDto sommelierOutputDto = sommelierService.updateSommelierById(id, updatedSommelier);
        return ResponseEntity.ok().body(sommelierOutputDto);
    }
    @PutMapping("/{userName}")
    public ResponseEntity<SommelierOutputDto> updateSommelierByUsername(@PathVariable String userName, @Valid @RequestBody SommelierInputDto updatedSommelier) {
        SommelierOutputDto sommelierOutputDto = sommelierService.updateSommelierByUserName(userName, updatedSommelier);
        return ResponseEntity.ok().body(sommelierOutputDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSommelierById(@PathVariable Long id) {
        sommelierService.deleteSommelierById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<Object> deleteSommelierByUsername(@PathVariable String userName) {
        sommelierService.deleteSommelierByUsername(userName);
        return ResponseEntity.noContent().build();
    }

    //RELATIES
    //TODO Hier nog een getmapping om alle WARs v.e. sommelier te krijgen? Of 2: 1 voor alle WARs en 1 voor alle WARs van een sommelier? (Of moet de eerste ergens anders?)
}
