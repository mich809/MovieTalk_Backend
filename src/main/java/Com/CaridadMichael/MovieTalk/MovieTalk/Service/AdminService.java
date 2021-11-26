package Com.CaridadMichael.MovieTalk.MovieTalk.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.User;
import Com.CaridadMichael.MovieTalk.MovieTalk.Repo.UserRepo;


@Service
public class AdminService {

		@Autowired
	    private UserRepo userRepo;		
		
		
	    public ResponseEntity<String> deleteUser(String username) {    	
	    	 User deletedUser = this.getUser(username);	  
	    	 if  (deletedUser == null){
	    		 return  new ResponseEntity<String>(username + " does not exist", HttpStatus.ACCEPTED);
	    	 }else {
	    		 userRepo.delete(deletedUser);		    	 
		    	 return  new ResponseEntity<String>(username + " has been deleted", HttpStatus.ACCEPTED);	    		 
	    	 }
	    	 
	    }  	
	    
	    public User getUser(String username) {
	    	if(userRepo.findById(username).isPresent())
	    		return (userRepo.findById(username).get());
	    	else return null;
	    }
}
