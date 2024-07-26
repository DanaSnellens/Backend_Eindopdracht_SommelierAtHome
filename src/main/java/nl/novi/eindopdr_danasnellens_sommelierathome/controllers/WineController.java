package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineOutputDto;

import nl.novi.eindopdr_danasnellens_sommelierathome.services.WineService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    //Create
    //@AuthenticationPrincipal UserDetails userDetails nog fixen (ook in service). Zie huiswerkklas 16; 52 minuten
    @PostMapping
    public ResponseEntity<WineOutputDto> createWine(@RequestBody WineInputDto wineInputDto/*, @AuthenticationPrincipal UserDetails userDetails*/) {
        WineOutputDto wineOutputDto = wineService.createWine(wineInputDto/*, userDetails.getUsername()*/);
        //URI
        return ResponseEntity.created(null).body(wineOutputDto/*, userDetails.getUsername()*/);
    }
    //Update
    //Delete
}
