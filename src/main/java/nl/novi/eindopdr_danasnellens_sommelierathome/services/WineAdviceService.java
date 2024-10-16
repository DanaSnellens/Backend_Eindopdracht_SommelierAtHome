package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Wine;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRequestRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineAdviceMapper.*;

@Service
public class WineAdviceService {
    private final WineAdviceRepository wineAdviceRepository;
    private final WineAdviceRequestRepository wineAdviceRequestRepository;
    private final WineRepository wineRepository;

    public WineAdviceService(WineAdviceRepository wineAdviceRepository, WineAdviceRequestRepository wineAdviceRequestRepository, WineRepository wineRepository) {
        this.wineAdviceRepository = wineAdviceRepository;
        this.wineAdviceRequestRepository = wineAdviceRequestRepository;
        this.wineRepository = wineRepository;
    }

    public List<WineAdviceOutputDto> getAllWineAdvices() {
        List<WineAdvice> wineAdviceList = wineAdviceRepository.findAll();
        return wineAdviceModelListToOutputList(wineAdviceList);
    }

    public WineAdviceOutputDto getWineAdviceById(Long id) {
        Optional<WineAdvice> optionalWineAdvice = wineAdviceRepository.findById(id);
        if (optionalWineAdvice.isPresent()) {
            return wineAdviceModelToOutput(optionalWineAdvice.get());
        }
        else throw new RuntimeException("No wine advice found with id: " + id);
    }

    public WineAdviceOutputDto createWineAdvice(WineAdviceInputDto waInputDto) {
        WineAdviceRequest war = wineAdviceRequestRepository.findById(waInputDto.getWineAdviceRequestId())
                .orElseThrow(() -> new EntityNotFoundException("No wine advice request found with id: " + waInputDto.getWineAdviceRequestId()));



        Set<Wine> wineSet = waInputDto.getWineIdSet().stream()
                .map(wineId -> wineRepository.findById(wineId)
                        .orElseThrow(() -> new EntityNotFoundException("No wine found with id: " + wineId)))
                .collect(Collectors.toSet());


        WineAdvice wa = wineAdviceInputToModel(waInputDto, war, wineSet);
        return wineAdviceModelToOutput(wineAdviceRepository.save(wa));
    }

    public WineAdviceOutputDto updateWineAdvice(Long id, WineAdviceInputDto updatedWineAdvice) {
        Optional<WineAdvice> optionalWineAdvice = wineAdviceRepository.findById(id);
        if (optionalWineAdvice.isPresent()) {
            return wineAdviceModelToOutput(optionalWineAdvice.get());
        }
        else throw new RuntimeException("No wine advice found with id: " + id);
    }

    public void deleteWineAdviceById(Long id) {
        Optional<WineAdvice> optionalWineAdvice = wineAdviceRepository.findById(id);
        if (optionalWineAdvice.isPresent()) {
            wineAdviceRepository.deleteById(id);
        }
        else throw new RuntimeException("No wine advice found with id: " + id);
    }
}
