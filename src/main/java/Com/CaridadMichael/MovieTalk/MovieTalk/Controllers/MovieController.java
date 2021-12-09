package Com.CaridadMichael.MovieTalk.MovieTalk.Controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.Comment;
import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.Movie;
import Com.CaridadMichael.MovieTalk.MovieTalk.Service.MovieService;


@RestController
@RequestMapping("movie")
public class MovieController {
	
 	@Autowired
    private MovieService movieService;
	
 	@PutMapping("/movie/rateMovie/{rating}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<String> ratedMovie(@RequestParam int rating, @RequestParam String id) {    	
    	
 		return movieService.rateMovie(rating, id);    	
    }	
	
	 @GetMapping("/getMovies")
	public ResponseEntity<Set<Movie>> getMovies(Authentication authentication) {    	
		
		 return movieService.getFavoriteMovies(authentication.getName());
	}
	 
	 @PostMapping("/likeMovie")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<Movie> likedMovie(@RequestBody Movie movie,Authentication authentication) {    	
    	
		 return movieService.likedMovie(movie,authentication.getName());  
    }
	 
	@DeleteMapping("/removeMovie")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<String> removeMovie(@RequestBody Movie movie,Authentication authentication) {   
		
		return movieService.removeMovie(movie,authentication.getName());      	
    }
	
	@PostMapping("/comment")
	@PreAuthorize("hasRole('User')")
	public ResponseEntity<String> comment(@RequestBody Comment comment,@RequestParam String id) {    	
	    	return movieService.commentMovie(comment , id);  
	}
	
	 @GetMapping("/getComments")
		public ResponseEntity<Set<Comment>> getComments(@RequestParam String id) {    	
			
			 return movieService.getAllComments(id);
		}
}
