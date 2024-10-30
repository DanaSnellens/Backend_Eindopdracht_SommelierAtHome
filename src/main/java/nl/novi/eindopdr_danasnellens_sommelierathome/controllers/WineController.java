package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineOutputDto;

import nl.novi.eindopdr_danasnellens_sommelierathome.helpers.ApiResponse;
import nl.novi.eindopdr_danasnellens_sommelierathome.services.WineService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wines")
public class WineController {
    //Service
    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping
    public ResponseEntity<List<WineOutputDto>> getAllWines() {
        return ResponseEntity.ok().body(wineService.getAllWines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WineOutputDto> getWineById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(wineService.getWineById(id));
    }

    //TODO @AuthenticationPrincipal UserDetails userDetails nog fixen (ook in service). Zie huiswerkklas 16; 52 minuten
    //Zie ook SpringSecurityConfig.java
    @PostMapping
    public ResponseEntity<ApiResponse<WineOutputDto>> createWine
        (@Valid @RequestBody WineInputDto wineInputDto/*, @AuthenticationPrincipal UserDetails userDetails*/) {
            WineOutputDto wineOutputDto = wineService.createWine(wineInputDto); ;
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(wineOutputDto.getId()).toUri();
        ApiResponse<WineOutputDto> response = new ApiResponse<>("Wine was successfully created", wineOutputDto);
        return ResponseEntity.created(uri).body(response /*, userDetails.getUsername()*/);
    }

    /*
    return new ResponseEntity<>(Map.of("message", "New wine with id " + savedWine.getId() + " is created.", "wine", wineModelToOutput(savedWine)), HttpStatus.CREATED);*/
    @PutMapping("/{id}")
    public ResponseEntity<WineOutputDto> updateWineById(@PathVariable Long id, @Valid @RequestBody WineInputDto updatedWine) {
        WineOutputDto wineOutputDto = wineService.updateWineById(id, updatedWine);
        //TODO Message toevoegen: "Wine with id  " + id + " has been successful updated"
        return ResponseEntity.ok().body(wineOutputDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWineById(@PathVariable Long id) {
        wineService.deleteWineById(id);
        //Return a 204 status
        //TODO Message toevoegen: "Wine with id: " + id + " has been deleted"
        return ResponseEntity.ok("Wine with id " + id + " has been successful deleted");
    }
}
