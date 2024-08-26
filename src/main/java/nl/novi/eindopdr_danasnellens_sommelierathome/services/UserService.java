package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.models.User;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get All
    public List<UserOutputDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userModelListToOutputList(userList);
    }

    // Get One
    public UserOutputDto getUserByUsername(String userName) {
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        if (optionalUser.isPresent()) {
            return userFromModelToOutputDto(optionalUser.get());
        }
        else throw new UsernameNotFoundException("No user found with username: " + userName);
    }

    // Create
    //@AuthenticationPrincipal UserDetails userDetails nog fixen (ook in controller). Zie huiswerkklas 16; 52 minuten

    public UserOutputDto createUser(UserInputDto userInputDto/*, String userName */ ) {
        User u = userRepository.save(userFromInputDtoToModel(userInputDto/*, userName*/));
        return userFromModelToOutputDto(u);
    }

    // Update

    public UserOutputDto updateUser (String userName, UserIputDto updatedUser) {
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        if (optionalUser.isPresent()) {
            return userFromModelToOutputDto(optionalUser.get());
        }
        else throw new UsernameNotFoundException("No user found with username: " + userName);
    }

    // Delete
    public void deleteUserByUserName(String userName) {
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        if (optionalUser.isPresent()) {
            userRepository.deleteByUserName(userName);
        }
        else throw new UsernameNotFoundException("No user found with username: " + userName);
    }
}
