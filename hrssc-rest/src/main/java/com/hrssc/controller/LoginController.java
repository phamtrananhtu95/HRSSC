package com.hrssc.controller;
import com.hrssc.entities.User;
import com.hrssc.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
/**
 * Created by Thien on 6/16/2018.
 */
@RestController
@CrossOrigin(origins = "localhost:4200")
public class LoginController {
    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UsersService userService;

//    @CrossOrigin
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ResponseEntity<?> createUser(@RequestBody User newUser) {
//        if (userService.find(newUser.getUsername()) != null) {
//            logger.error("username Already exist " + newUser.getUsername());
//            return new ResponseEntity(
//                    new CustomErrorType("user with username " + newUser.getUsername() + "already exist "),
//                    HttpStatus.CONFLICT);
//        }
//        newUser.setRole("USER");
//
//        return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
//    }


    @GetMapping(value = {"/","/login"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    private User login(){
        User user = userService.getAuthenticatedUser();
        if (user == null){
            return null;
        }else {
            return user;
        }
    }

//    @CrossOrigin
//    @RequestMapping("/login")
//    public Principal user(Principal principal) {
//        logger.info("user logged "+principal);
//        return principal;
//    }
}
