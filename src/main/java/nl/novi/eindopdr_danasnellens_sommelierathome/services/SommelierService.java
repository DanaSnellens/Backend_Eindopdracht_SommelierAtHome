package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.SommelierInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.SommelierOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.SommelierRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.SommelierMapper.*;

@Service
public class SommelierService {
    private final SommelierRepository sommelierRepository;
    
    public SommelierService(SommelierRepository sommelierRepository) {
        this.sommelierRepository = sommelierRepository;
    }
    
    public List<SommelierOutputDto> getAllSommeliers() {
        List<Sommelier> sommelierList = sommelierRepository.findAll();
        return sommelierModelListToOutputList(sommelierList);
    }
    
/*    public SommelierOutputDto getSommelierById(Long id) {
        Optional<Sommelier> optionalSommelier = sommelierRepository.findById(id);
        if (optionalSommelier.isPresent()) {
            return sommelierModelToOutput(optionalSommelier.get());
        }
        else throw new RuntimeException("No sommelier found with id: " + id);
    }*/
    
    public SommelierOutputDto getSommelierByUsername(String userName) {
        Optional<Sommelier> optionalSommelier = sommelierRepository.findSommelierByUsername(userName);
        if (optionalSommelier.isPresent()) {
            return sommelierModelToOutput(optionalSommelier.get());
        }
        else throw new UsernameNotFoundException("No sommelier found with the username " + userName);
    }
    
    public SommelierOutputDto createSommelier(SommelierInputDto sommelierInputDto, String userName) {
        Optional<Sommelier> optionalSommelier = sommelierRepository.findSommelierByUsername(userName);
        if (optionalSommelier.isEmpty()) {
            Sommelier sommelier = sommelierRepository.save(sommelierInputDtoToModel(sommelierInputDto, userName));
            return sommelierModelToOutput(sommelier);
        } else {
            throw new UsernameNotFoundException("Sommelier with username: " + userName + "already exists");
        }
    }
    
/*    public SommelierOutputDto updateSommelierById(Long id, SommelierInputDto updatedSommelier) {
        Optional<Sommelier> optionalSommelier = sommelierRepository.findById(id);
        if (optionalSommelier.isPresent()) {
            return sommelierModelToOutput(optionalSommelier.get());
        }
        else throw new RuntimeException("No sommelier found with id: " + id);
    }*/
    
    public SommelierOutputDto updateSommelierByUsername(String userName, SommelierInputDto updatedSommelier) {
        Optional<Sommelier> optionalSommelier = sommelierRepository.findSommelierByUsername(userName);
        if (optionalSommelier.isPresent()) {
            return sommelierModelToOutput(optionalSommelier.get());
        }
        else throw new UsernameNotFoundException("No sommelier found with username: " + userName);
    }
    
/*    public void deleteSommelierById(Long id) {
        Optional<Sommelier> optionalSommelier = sommelierRepository.findById(id);
        if (optionalSommelier.isPresent()) {
            sommelierRepository.deleteById(id);
        }
        else throw new RuntimeException("No sommelier found with id: " + id);
    }*/
    
    public void deleteSommelierByUserName(String userName) {
        Optional<Sommelier> optionalSommelier = sommelierRepository.findSommelierByUsername(userName);
        if (optionalSommelier.isPresent()) {
            sommelierRepository.deleteSommelierByUsername(userName);
        }
        else throw new UsernameNotFoundException("No sommelier found with username: " + userName);
    }

    public void deleteSommelierByUsername(String userName) {
    }
}
