package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.models.User;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//TODO: Klopt dit gebruik van T?
@Service
public abstract class UserService <T extends User> {
//TODO DIT HIER NIET NOEMEN? (volgens chatGPT)    private final UserRepository userRepository;
/*    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    //Get All
    public abstract List<T> getAllUsers();

    //Get One
    //TODO zowel getUserByUserName als by ID?
    public abstract Optional<T> getUserByUsername(String userName);

    public abstract Optional<T> getUserById(Long id);

    //Create
    public abstract T addUser(T user);

    //Update
    public abstract T updateUser(Long id, T user);

    //Delete
    public abstract void deleteUserById(Long id);
}


