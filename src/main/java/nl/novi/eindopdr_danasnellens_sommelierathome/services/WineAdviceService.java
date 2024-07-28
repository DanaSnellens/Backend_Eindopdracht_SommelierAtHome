package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineAdviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
