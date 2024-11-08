package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.SommelierInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.SommelierOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.RoleRepository;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.SommelierRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.SommelierMapper.*;

@Service
public class SommelierService {
    private final SommelierRepository sommelierRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public SommelierService(SommelierRepository sommelierRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.sommelierRepository = sommelierRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public List<SommelierOutputDto> getAllSommeliers() {
        List<Sommelier> sommelierList = sommelierRepository.findAll();
        return sommelierModelListToOutputList(sommelierList);
    }
    
    public SommelierOutputDto getSommelierByUsername(String username) {
        Optional<Sommelier> optionalSommelier = sommelierRepository.findSommelierByUsername(username);
        if (optionalSommelier.isPresent()) {
            return sommelierModelToOutput(optionalSommelier.get());
        }
        else throw new UsernameNotFoundException("No sommelier found with the username " + username);
    }
    
    public SommelierOutputDto createSommelier(SommelierInputDto sommelierInputDto/*, String username*/) {
        Optional<Sommelier> optionalSommelier = sommelierRepository.findSommelierByUsername(sommelierInputDto.getUsername());
        if (optionalSommelier.isEmpty()) {
            sommelierInputDto.setPassword(passwordEncoder.encode(sommelierInputDto.getPassword()));

            Optional<Role> sommelierRoleOptional = Optional.ofNullable(roleRepository.findRoleByRoleName("ADMIN"));
            Role sommelierRole = sommelierRoleOptional
                    .orElseThrow(() -> new EntityNotFoundException("ADMIN role not found"));

            Sommelier sommelier = sommelierInputDtoToModel(sommelierInputDto/*, username*/);
            sommelier.getRoleSet().add(sommelierRole);

            sommelier = sommelierRepository.save(sommelier);
            return sommelierModelToOutput(sommelier);
        } else {
            throw new UsernameNotFoundException("Sommelier with username: " + sommelierInputDto.getUsername() + " already exists");
        }
    }
    //TODO do smth with updatedSomm (of imput weg?)
    public SommelierOutputDto updateSommelierByUsername(String username, SommelierInputDto updatedSommelier) {
        Optional<Sommelier> optionalSommelier = sommelierRepository.findSommelierByUsername(username);
        if (optionalSommelier.isPresent()) {
            return sommelierModelToOutput(optionalSommelier.get());
        }
        else throw new UsernameNotFoundException("No sommelier found with username: " + username);
    }

    @Transactional
    public void deleteSommelierByUsername(String sommelierUsername) {
        Optional<Sommelier> optionalSommelier = sommelierRepository.findSommelierByUsername(sommelierUsername);
        if (optionalSommelier.isPresent()) {
            sommelierRepository.deleteSommelierByUsername(sommelierUsername);
        }
        else throw new UsernameNotFoundException("No sommelier found with sommelierUsername: " + sommelierUsername);
    }
}
