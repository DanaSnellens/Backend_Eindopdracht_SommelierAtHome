package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineMapper;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Wine;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineMapper.*;

@Service
public class WineService {
    //Repository
    private final WineRepository wineRepository;

    public WineService(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    // Get All
    public List<WineOutputDto> getAllWines() {
        List<Wine> wineList = wineRepository.findAll();
        return wineModelListToOutputList(wineList);
    }

    // Get One
    public WineOutputDto getWineById(Long id) {
        Optional<Wine> optionalWine = wineRepository.findById(id);
        if (optionalWine.isPresent()) {
            return wineFromModelToOutputDto(optionalWine.get());
        }
        else throw new RuntimeException("No wine found with id: " + id);
    }

    // Create
    //@AuthenticationPrincipal UserDetails userDetails nog fixen (ook in controller). Zie huiswerkklas 16; 52 minuten

    public WineOutputDto createWine(WineInputDto wineInputDto/*, String wineName */) {
        Wine wine = wineRepository.save(wineFromInputDtoToModel(wineInputDto/*, wineName)*/));
        return wineFromModelToOutputDto(wine);
    }

    // Update
    public WineOutputDto updateWine(Long id, WineInputDto updatedWine) {
        Optional<Wine> optionalWine = wineRepository.findById(id);
        if (optionalWine.isPresent()) {
            return WineMapper.wineFromModelToOutputDto(optionalWine.get());
        }
        else throw new RuntimeException("No wine found with id: " + id);
    }

    // Delete
    public void deleteWineById(Long id) {
        Optional<Wine> optionalWine = wineRepository.findById(id);
        if (optionalWine.isPresent()) {
            wineRepository.deleteById(id);
        }
        else throw new RuntimeException("No wine found with id: " + id);
    }



}
