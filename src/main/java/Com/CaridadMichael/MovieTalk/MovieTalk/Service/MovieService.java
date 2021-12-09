package Com.CaridadMichael.MovieTalk.MovieTalk.Service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.Comment;
import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.Movie;
import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.User;
import Com.CaridadMichael.MovieTalk.MovieTalk.Repo.CommentRepo;
import Com.CaridadMichael.MovieTalk.MovieTalk.Repo.MovieRepo;
import Com.CaridadMichael.MovieTalk.MovieTalk.Repo.UserRepo;

@Service
public class MovieService {
	
		@Autowired
	    private MovieRepo movieRepo;
		
		@Autowired
	    private UserRepo userRepo;
		
		@Autowired
		private CommentRepo commentRepo;
		
		@Autowired
	    private User user;
		
		 public ResponseEntity<Movie> likedMovie(Movie movie, String username) {
		    	user = userRepo.findById(username).get(); 
		    	movieRepo.save(movie);
			    user.getFavoriteMovies().add(movie); 	  
		    	userRepo.save(user);
		     
		    	
		    	return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		     }
		 
		 public ResponseEntity<Set<Movie>> getFavoriteMovies(String username) {
				user = userRepo.findById(username).get(); 	
				return new ResponseEntity<Set<Movie>>(user.getFavoriteMovies(),HttpStatus.ACCEPTED);
			}
		 
		 public ResponseEntity<String> rateMovie(int rating , String id) {
				Movie movie;
				movie = movieRepo.findById(id).get();				
				movie.setRating(rating);
				movieRepo.save(movie);
				
				return new ResponseEntity<String>(movie.getOriginal_title() + " has been given a rating of "+ rating , HttpStatus.OK);
				
				
			}
			
			
			public ResponseEntity<String> removeMovie(Movie movie,  String username) {
				user = userRepo.findById(username).get(); 
				if (user.getFavoriteMovies().remove(movie)) {	
					userRepo.save(user);
					return new ResponseEntity<String>( movie.getOriginal_title() + " has been removed from "+ username+ " list", HttpStatus.OK);
					
				}else {
					return new ResponseEntity<String>( movie.getOriginal_title() + " was not removed from "+ username+ " list", HttpStatus.OK);
				}
				}

			public ResponseEntity<String> commentMovie(Comment comment, String id) {
				Movie movie = movieRepo.findById(id).get();
				commentRepo.save(comment);
    			movie.getComments().add(comment);
				movieRepo.save(movie);
				
				return new ResponseEntity<String>( movie.getOriginal_title() + " had comment: "+ comment.getComment() + " added", HttpStatus.OK);
			}
			
			public ResponseEntity<Set<Comment>> getAllComments(String id) {
				Movie movie = movieRepo.findById(id).get();
				
    		
				
				return new ResponseEntity<Set<Comment>>( movie.getComments(), HttpStatus.OK);
			}

}
