package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Wine;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRequestRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineAdviceMapper.*;

@Service
public class WineAdviceService {
    private final WineAdviceRepository wineAdviceRepository;
    private final WineAdviceRequestRepository wineAdviceRequestRepository;
    private final WineRepository wineRepository;
    public WineAdviceService(WineAdviceRepository wineAdviceRepository, WineAdviceRepository wineAdviceRepository1, WineAdviceRequestRepository wineAdviceRequestRepository, WineRepository wineRepository) {
        this.wineAdviceRepository = wineAdviceRepository1;
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

    public WineAdviceOutputDto createWineAdvice(WineAdviceInputDto wineAdviceInputDto) {
        WineAdviceRequest war = getWineAdviceRequestById(wineAdviceInputDto.getWineAdviceRequestId());
        for (Long wineId : wineAdviceInputDto.getWineIdSet()) {
            assignWineToWineAdvice(war.getId(), wineId);
        }
        WineAdvice wa = wineAdviceRepository.save(wineAdviceInputToModel(wineAdviceInputDto));
        return wineAdviceModelToOutput(wa);
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

    //RELATIES
    //TODO moet ik hier geen gebruik maken van DTO en mapper?
    public void assignWineToWineAdvice(Long id, Long wineId) {
        Optional<WineAdvice> optionalWineAdvice = wineAdviceRepository.findById(id);
        Optional<Wine> optionalWine = wineRepository.findById(wineId);

        if (optionalWineAdvice.isPresent() && optionalWine.isPresent()) {
            Wine wine = optionalWine.get();
            WineAdvice wa = optionalWineAdvice.get();

            wa.getWineSet().add(wine);

            wineAdviceRepository.save(wa);
        } else throw new RuntimeException("No wine advice found with id: " + id + " or no wine found with id: " + wineId);
    }

    public WineAdviceRequest getWineAdviceRequestById(Long wineAdviceRequestId) {

        Optional<WineAdviceRequest> optionalWineAdviceRequest = wineAdviceRequestRepository.findById(wineAdviceRequestId);
        if (optionalWineAdviceRequest.isPresent()) {
            return optionalWineAdviceRequest.get();
        }
        else throw new RuntimeException("No wine advice request found with id: " + wineAdviceRequestId);
    }
}
