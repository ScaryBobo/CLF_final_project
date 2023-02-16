package project_backend.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project_backend.model.User;
import project_backend.service.UserService;
import project_backend.util.JwtUtil;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/")
public class LoginRestController {

    private Logger logger = Logger.getLogger(LoginRestController.class.getName());
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userSvc;

    @PostMapping(path = "/createuser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createNewUser(@RequestBody String payload){
        logger.info(">>>> login payload is %s".formatted(payload));
        Gson gson = new Gson();
        User user = gson.fromJson(payload, User.class);
        System.out.println(">>>>> after mapping object: " + user.toString());
        Optional<String> opt = userSvc.checkIfEmailExists(user);

        if (opt.isEmpty()){
            user.setUserId(userSvc.generateUserId());
            userSvc.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @PostMapping (path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> loginUser(@RequestBody String payload){
        logger.info(">>>> login payload is %s".formatted(payload));
        Gson gson = new Gson();
        User user = gson.fromJson(payload, User.class);
        boolean authUser = userSvc.authenticateLogin(user);
        if (authUser){
            Optional <User> opt = userSvc.getUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(opt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<User> generateToken(@RequestBody String payload) throws Exception {
        System.out.println(">>>> payload" + payload);
        Gson gson = new Gson();
        User user = gson.fromJson(payload, User.class);


        boolean authUser = userSvc.authenticateLogin(user);

        if (authUser){
            String token = jwtUtil.generateToken(user.getEmail());
            Optional <User> opt = userSvc.getUser(user);
            opt.get().setToken(token);

            return ResponseEntity.status(HttpStatus.OK).body(opt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
