package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineRepository;
import org.springframework.stereotype.Service;

@Service
public class WineService {
    //Repository
    private final WineRepository wineRepository;

    public WineService(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    // Get all
    // Get one by id
    // Create
    // Update
}
