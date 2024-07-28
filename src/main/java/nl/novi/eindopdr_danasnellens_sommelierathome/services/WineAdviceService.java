package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineAdviceInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineAdviceMapper;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineAdviceMapper.*;

@Service
public class WineAdviceService {
    private final WineAdviceRepository wineAdviceRepository;
    public WineAdviceService(WineAdviceRepository wineAdviceRepository) {
        this.wineAdviceRepository = wineAdviceRepository;
    }

    public List<WineAdviceOutputDto> getAllWineAdvices() {
        List<WineAdvice> wineAdviceList = wineAdviceRepository.findAll();
        return wineAdviceModelListToOutputList(wineAdviceList);
    }

    public WineAdviceOutputDto getWineAdviceById(Long id) {
        Optional<WineAdvice> optionalWineAdvice = wineAdviceRepository.findById(id);
        if (optionalWineAdvice.isPresent()) {
            return wineAdviceFromModelToOutputDto(optionalWineAdvice.get());
        }
        else throw new RuntimeException("No wine advice found with id: " + id);
    }

    public WineAdviceOutputDto createWineAdvice(WineAdviceInputDto wineAdviceInputDto) {
        WineAdvice wa = wineAdviceRepository.save(wineAdviceFromInputDtoToModel(wineAdviceInputDto));
        return wineAdviceFromModelToOutputDto(wa);
    }

    public WineAdviceOutputDto updateWineAdvice(Long id, WineAdviceInputDto updatedWineAdvice) {
        Optional<WineAdvice> optionalWineAdvice = wineAdviceRepository.findById(id);
        if (optionalWineAdvice.isPresent()) {
            return wineAdviceFromModelToOutputDto(optionalWineAdvice.get());
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
