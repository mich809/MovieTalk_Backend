package Com.CaridadMichael.MovieTalk.MovieTalk.Controllers;




import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.User;
import Com.CaridadMichael.MovieTalk.MovieTalk.Service.UserService;

import Com.CaridadMichael.MovieTalk.MovieTalk.Service.JwtService;
import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtService jwtService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/register"})
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody User user) {
        return userService.registerNewUser(user);  
       }

  
   @PostMapping({"/authenticate"})
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
    
   
}