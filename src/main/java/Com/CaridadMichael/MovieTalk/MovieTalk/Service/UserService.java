package Com.CaridadMichael.MovieTalk.MovieTalk.Service;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    public User getUser(String username) {
    	if(userRepo.findById(username).isPresent())
    		return userRepo.findById(username).get();
    	else return null;
    }
    
    public String likedMovie(String title,String username) {
    	user = userRepo.findById(username).get(); 
    	Movie movie = new Movie();
    	movie.setTitle(title);    	
   
    	user.getMovies().add(movie);
    	userRepo.save(user);
    	
    	return "yes";  
     }
    
    public void addNewMovie(String title) {    	  
        Movie movie = new Movie();
        movie.setTitle(title);
        movieRepo.save(movie);
    }
    
    public boolean userExist(String username) {
    	if(userRepo.findById(username).isPresent())
    		return true;
    	else return false;
    	
    }

   
}