package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Wine;
import nl.novi.eindopdr_danasnellens_sommelierathome.services.WineService;
import org.springframework.http.ResponseEntity;
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
    @PostMapping
    public ResponseEntity<WineOutputDto> addWine(@RequestBody Wine wine) {
        return ResponseEntity.ok().body(wineService.createWine(wine));
    }
    //Update
    //Delete
}
