package Com.CaridadMichael.MovieTalk.MovieTalk.Controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.User;
import Com.CaridadMichael.MovieTalk.MovieTalk.Service.UserService;
import Com.CaridadMichael.MovieTalk.MovieTalk.Service.JwtService;
import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.*;

@RestController
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
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
   
    
    @GetMapping("/admin/findUser/{id}")
    @PreAuthorize("hasRole('Admin')")
    public User getUser(@RequestParam String username) {
    	return userService.getUser(username);
    
    }
    
    @PostMapping("/user/likeMovie/{title}")
    @PreAuthorize("hasRole('User')")
    public String likedMovie(@RequestParam String title,Authentication authentication) {    	
    	userService.likedMovie(title,authentication.getName());  
    	return "yes";
    	
    }
    
    @PostMapping("/movie/addMovie/{title}")
    @PreAuthorize("hasRole('User')")
    public void addMovie(@RequestParam String title) {     
        userService.addNewMovie(title);            	
    }
    
    
    @GetMapping(value = "/user/me")
    @ResponseBody
    public String currentUserName(Authentication authentication) {    
        if (authentication != null)
            return authentication.getName();
        else
            return "";
    }
    
    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}