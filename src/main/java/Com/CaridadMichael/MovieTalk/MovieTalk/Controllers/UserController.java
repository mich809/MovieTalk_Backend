package Com.CaridadMichael.MovieTalk.MovieTalk.Controllers;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

  
    @PutMapping("/likeMovie/{title}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<String> likedMovie(@RequestParam String title,Authentication authentication) {    	
    	return userService.likedMovie(title,authentication.getName());  
    	
    	
    }
    
    @PostMapping("/movie/addMovie/{title}")
    @PreAuthorize("hasRole('User')")
    public void addMovie(@RequestParam String title) {     
        userService.addNewMovie(title);            	
    }
    
    
    @GetMapping(value = "/me")
    @ResponseBody
    public String currentUserName(Authentication authentication) {    
        if (authentication != null)
            return authentication.getName();
        else
            return "";
    }
    
    @PostMapping({"/authenticate"})
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}