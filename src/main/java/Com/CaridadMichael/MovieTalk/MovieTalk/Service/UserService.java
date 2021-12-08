package Com.CaridadMichael.MovieTalk.MovieTalk.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.Movie;
import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.Role;
import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.User;
import Com.CaridadMichael.MovieTalk.MovieTalk.Repo.MovieRepo;
import Com.CaridadMichael.MovieTalk.MovieTalk.Repo.RoleRepo;
import Com.CaridadMichael.MovieTalk.MovieTalk.Repo.UserRepo;


import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;
    
    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;    
  
    @Autowired
    private User user;


    public void initRoleAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepo.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleRepo.save(userRole);

        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("password"));       
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepo.save(adminUser);


    }

    public ResponseEntity<String> registerNewUser(User user) {    	
    	if (userExist(user.getUsername())) {
    		return new ResponseEntity<String>("User already Exist",HttpStatus.CONFLICT);
    	}else {
    	    Role role = roleRepo.findById("User").get();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepo.save(user);
            return  new ResponseEntity<String>("User registered", HttpStatus.CREATED);
    	}  	
    	
    }    
   
    
    public ResponseEntity<Movie> likedMovie(Movie movie, String username) {
    	user = userRepo.findById(username).get(); 
    	movieRepo.save(movie);
	    user.getMovies().add(movie); 	  
    	userRepo.save(user);
     
    	
    	return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
     }
    
    public void addNewMovie(Movie movie) {   	  
       movieRepo.save(movie);
    }
    
    public boolean userExist(String username) {
    	if(userRepo.findById(username).isPresent())
    		return true;
    	else return false;
    	
    }

	public ResponseEntity<Set<Movie>> getMovies(String username) {
		user = userRepo.findById(username).get(); 	
		return new ResponseEntity<Set<Movie>>(user.getMovies(),HttpStatus.ACCEPTED);
	}
	
//	public ResponseEntity<String> rateMovie(int rating , String id) {
//		Movie movie;
//		movie = movieRepo.findById(id).get();
//		movie.setRating(movie.getRating()+rating);
//		movieRepo.save(movie);
//		
//		return new ResponseEntity<String>(movie.getOriginal_title() + " has been given a rating of "+ rating , HttpStatus.OK);
//		
//		
//	}
	

   
}