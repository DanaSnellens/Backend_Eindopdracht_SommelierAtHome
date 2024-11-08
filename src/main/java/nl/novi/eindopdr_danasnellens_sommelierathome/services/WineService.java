package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.exceptions.EntityAlreadyExistsException;
import nl.novi.eindopdr_danasnellens_sommelierathome.exceptions.RecordNotFoundException;
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

    public List<WineOutputDto> getAllWines() {
        List<Wine> wineList = wineRepository.findAll();
        return wineModelListToOutputList(wineList);
    }

    public WineOutputDto getWineById(Long id) {
        Optional<Wine> optionalWine = wineRepository.findById(id);
        if (optionalWine.isPresent()) {
            return wineModelToOutput(optionalWine.get());
        }
        else throw new RecordNotFoundException("No wine found with id: " + id);
    }

    //TODO @AuthenticationPrincipal UserDetails userDetails nog fixen (ook in controller). Zie huiswerkklas 16; 52 minuten
    public WineOutputDto createWine(WineInputDto wineInputDto) {
        //TODO: of dit anders oplossen voor deze simpele class?
        boolean exists = wineRepository.existsWineByWineName(wineInputDto.getWineName());
        if (exists) {
            throw new EntityAlreadyExistsException("Wine with name: " + wineInputDto.getWineName() + " already exists");
        }
        else {
            Wine wine = wineInputToModel(wineInputDto);
            Wine savedWine = wineRepository.save(wine);
            return wineModelToOutput(savedWine);
        }
    }
//TODO @Valid toevoegen? (voor WineInputDto
    public WineOutputDto updateWineById(Long id, WineInputDto updatedWineInputDto) {
        Optional<Wine> optionalWine = wineRepository.findById(id);
        if (optionalWine.isPresent()) {
            Wine existingWine = optionalWine.get();
            updateWineMapper(existingWine, updatedWineInputDto);
            Wine updatedWine = wineRepository.save(existingWine);
            return wineModelToOutput(updatedWine);
        }
        else throw new RecordNotFoundException("No wine found with id: " + id);
    }

    public void deleteWineById(Long id) {
        Optional<Wine> optionalWine = wineRepository.findById(id);
        if (optionalWine.isPresent()) {
            wineRepository.deleteById(id);
        }
        else throw new RecordNotFoundException("No wine found with id: " + id);
    }
}
