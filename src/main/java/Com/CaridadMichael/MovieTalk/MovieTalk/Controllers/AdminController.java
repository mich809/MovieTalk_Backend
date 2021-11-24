package Com.CaridadMichael.MovieTalk.MovieTalk.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.User;
import Com.CaridadMichael.MovieTalk.MovieTalk.Service.UserService;

@RestController
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/findUser/{id}")
    @PreAuthorize("hasRole('Admin')")
    public User getUser(@RequestParam String id) {
    	return userService.getUser(id);
    
    }
	
	@DeleteMapping("/findUser/{id}")
	@PreAuthorize("hasRole('Admin')")
    public User deletetUser(@RequestParam String id) {
    	return null;
    
    }

}
