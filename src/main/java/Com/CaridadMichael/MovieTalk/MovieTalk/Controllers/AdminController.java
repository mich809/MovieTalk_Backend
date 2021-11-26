package Com.CaridadMichael.MovieTalk.MovieTalk.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.User;
import Com.CaridadMichael.MovieTalk.MovieTalk.Service.AdminService;


@RestController
@RequestMapping("admin")
@PreAuthorize("hasRole('Admin')")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	@GetMapping("/findUser/{id}") 
    public User getUser(@RequestParam String id) {
    	return adminService.getUser(id);
    
    }
	
	@DeleteMapping("/deleteUser/{id}")    
    public ResponseEntity<String> deleteUser(@RequestParam String id) {
    	return adminService.deleteUser(id);
    
    }
	
	
	


}
