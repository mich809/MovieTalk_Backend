package Com.CaridadMichael.MovieTalk.MovieTalk.Controllers;


import java.util.Set;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  
    @PostMapping("/likeMovie")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<Movie> likedMovie(@RequestBody Movie movie,Authentication authentication) 
    {    	
    	 return userService.likedMovie(movie,authentication.getName());  
    	
    	
    }
    
    @PostMapping("/movie/addMovie")
    @PreAuthorize("hasRole('User')")
    public void addMovie(@RequestBody Movie movie) {     
        userService.addNewMovie(movie);            	
    }   
    
//    @PutMapping("/movie/rateMovie/{rating}")
//    @PreAuthorize("hasRole('User')")
//    public ResponseEntity<String> ratedMovie(@RequestParam int rating, @RequestParam String id) {    	
//    	return userService.rateMovie(rating, id);
//    	
//    }

    
    @PostMapping({"/authenticate"})
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
    
    @GetMapping("/getMovies")
    public ResponseEntity<Set<Movie>> getMovies(Authentication authentication) {    	
    	return userService.getMovies(authentication.getName());
    	
    }
}