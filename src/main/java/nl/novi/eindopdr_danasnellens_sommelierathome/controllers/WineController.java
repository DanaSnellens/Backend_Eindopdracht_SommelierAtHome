package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineOutputDto;

import nl.novi.eindopdr_danasnellens_sommelierathome.services.WineService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/wines")
public class WineController {
    //Service
    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    //MAPPINGS
    //Get All
    @GetMapping
    public ResponseEntity<List<WineOutputDto>> getAllWines() {
        return ResponseEntity.ok().body(wineService.getAllWines());
    }

    //Get One
    @GetMapping("/{id}")
    public ResponseEntity<WineOutputDto> getWineById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(wineService.getWineById(id));
    }

    @GetMapping("/{wineName}")
    public ResponseEntity<WineOutputDto> getWineByName(@PathVariable("wineName") String wineName) {
        return ResponseEntity.ok().body(wineService.getWineByName(wineName));
    }
    //Create
    //@AuthenticationPrincipal UserDetails userDetails nog fixen (ook in service). Zie huiswerkklas 16; 52 minuten
    //Zie ook SpringSecurityConfig.java
    @PostMapping
    public ResponseEntity<WineOutputDto> createWine
        (@Valid @RequestBody WineInputDto wineInputDto/*, @AuthenticationPrincipal UserDetails userDetails*/) {
            WineOutputDto wineOutputDto = wineService.createWine(wineInputDto/*, userDetails.getUsername()*/);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(wineOutputDto.getId()).toUri();
        return ResponseEntity.created(uri).body(wineOutputDto/*, userDetails.getUsername()*/);
    }
    //Update
    @PutMapping("/{id}")
    public ResponseEntity<WineOutputDto> updateWine(@PathVariable Long id, @Valid @RequestBody WineInputDto updatedWine) {
        WineOutputDto wineOutputDto = wineService.updateWine(id, updatedWine);
        return ResponseEntity.ok().body(wineOutputDto);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWineById(@PathVariable Long id) {
        wineService.deleteWineById(id);
        //Return a 204 status
        return ResponseEntity.noContent().build();
    }
}
