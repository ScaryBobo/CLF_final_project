package project_backend.service;

import project_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project_backend.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public boolean createUser (User user){
        return userRepo.insertNewUser(
                user.getUserId(),  user.getPassword(), user.getEmail());
    }

    public Optional<String> checkIfEmailExists (User user){ return userRepo.getUserRecordByEmail(user.getEmail()); }

    public boolean authenticateLogin (User user) {
        return userRepo.verifyUser(user.getEmail(), user.getPassword()).isPresent();
    }

    public Optional <String> getUserId (String email){return userRepo.getUserIdByEmail(email);}

    public Optional<User> getUser (User user){
        return userRepo.getUserIdByLogin(user.getEmail(), user.getPassword());
    }
    public String generateUserId (){return UUID.randomUUID().toString().substring(0,8); }

    public Optional<User> getUserById (String userId){
        return userRepo.getUserByUserId(userId);
    }

}
