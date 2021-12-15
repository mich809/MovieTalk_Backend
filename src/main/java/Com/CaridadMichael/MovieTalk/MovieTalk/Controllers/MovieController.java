package Com.CaridadMichael.MovieTalk.MovieTalk.Controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.Comment;
import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.Movie;
import Com.CaridadMichael.MovieTalk.MovieTalk.Service.MovieService;



@RestController
@RequestMapping("movie")
@PreAuthorize("hasRole('User')")
public class MovieController {
	
 	@Autowired
    private MovieService movieService;
 	
	 	@PostMapping("/postRating")
		public void postRating(@RequestParam String id ,@RequestParam int rating) { 	
	 		 movieService.rateMovie(id, rating);
		}
		
 	
	
		 @GetMapping("/getMovies")
		 public ResponseEntity<Set<Movie>> getMovies(Authentication authentication) {    	
				return movieService.getFavoriteMovies(authentication.getName());
		}
	 
	   @PostMapping("/likeMovie") 
		public ResponseEntity<String> likedMovie(@RequestBody Movie movie,Authentication authentication) {    	
		 
			 return movieService.likedMovie(movie,authentication.getName());  
		    }
	 
		@DeleteMapping("/removeMovie")   
	    public ResponseEntity<String> removeMovie(@RequestParam String id,Authentication authentication) {   
			
			return movieService.removeMovie(id,authentication.getName());      	
	    }
		
		@PostMapping("/postComment")	
		public ResponseEntity<String> comment(@RequestBody Comment comment,@RequestParam String id) {    	
		    	return  movieService.commentMovie(comment , id);  
		}
	
	    @GetMapping("/getComments")
		public Set<Comment> getComments(@RequestParam String id) {    	
			
			 return movieService.getAllComments(id);
		}	 
	 	
	 	
	 	@GetMapping("/getRating")
		public int getRating(@RequestParam String id) { 	
			return movieService.getMovieRating(id);
		}
	 	
	 	@GetMapping("/likedStatus")
	 	public boolean getLikedStatus(@RequestParam String id , Authentication authentication) { 	
			return movieService.getMovieLikedStatus(id, authentication.getName());
		}
}
