package Com.CaridadMichael.MovieTalk.MovieTalk.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.Role;
import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.User;

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
    private PasswordEncoder passwordEncoder;    
  
  

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
    	if (userRepo.findById(user.getUsername()).isPresent()) {
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
   
    
   
    
  
    


	
	
	
	
		
		
		
		
	}
	
	
	

   