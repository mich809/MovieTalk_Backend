package Com.CaridadMichael.MovieTalk.MovieTalk.Service;

import java.util.Collections;
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
		
			public ResponseEntity<String> likedMovie(Movie movie, String username) {
				user = userRepo.findById(username).get(); 
		
		    	
		    	if (!(checkIfMovieExist(movie.getId()))) {			    	
		    		movieRepo.save(movie);		    		
		    	} 
		    	if (!(user.getFavoriteMovies().contains(movie))) {	
		    		 movie = movieRepo.findById(movie.getId()).get();		    
		    		 user.getFavoriteMovies().add(movie); 	    	
			    	 userRepo.save(user);
		    		 return new ResponseEntity<String>(movie.getOriginal_title() +" has been added to " + username +" list" ,HttpStatus.ACCEPTED);
		    	}else {		    		
		    		 return   new ResponseEntity<String>(movie.getOriginal_title() + " was already in " +username + " list ",HttpStatus.ACCEPTED);
		    		
		    	}	
			 
		     
		    	
		    
		     }
		 
		 	public ResponseEntity<Set<Movie>> getFavoriteMovies(String username) {
				user = userRepo.findById(username).get(); 	
				return new ResponseEntity<Set<Movie>>(user.getFavoriteMovies(),HttpStatus.ACCEPTED);
			}
		 
		 	public void rateMovie(String id, int rating) {
				Movie movie;
				movie = movieRepo.findById(id).get();				
				movie.setRating(rating);
				movieRepo.save(movie);
				
		 		
			}
		 
		 	public int getMovieRating( String id) {
		 		if (checkIfMovieExist(id)) {
					Movie movie = movieRepo.findById(id).get();				
					return movie.getRating();
				}else {
					return 0;
				}
			}
		 	
		 	// check movie
			
			public ResponseEntity<String> removeMovie(String id,  String username) {
				user = userRepo.findById(username).get(); 
				Movie movie = movieRepo.findById(id).get();				
				if (user.getFavoriteMovies().contains(movie)){				
					user.getFavoriteMovies().remove(movie);
					userRepo.save(user);
					return new ResponseEntity<String>( movie.getOriginal_title() + " has been removed from "+ username+ " list" , HttpStatus.OK);
					
				}else {
					return new ResponseEntity<String>( movie.getOriginal_title() + " was not in "+ username+ " list", HttpStatus.OK);
				}
				}

			public ResponseEntity<String> commentMovie(Comment comment, String id) {				Movie movie;	
				
			   if(checkIfMovieExist(id)) {
					commentRepo.save(comment);
					movie  = movieRepo.findById(id).get();
					movie.getComments().add(comment);
					movieRepo.save(movie);
					return new ResponseEntity<String>( movie.getId() + " added comment " + comment, HttpStatus.OK);
				}
					
				else {		
					commentRepo.save(comment);
					movie = new Movie();
					movie.setId(id);
					movie.getComments().add(comment);
					movieRepo.save(movie);
					return new ResponseEntity<String>( movie.getId() + " created" , HttpStatus.OK);
					
				}
					
					
				
				
		
    			
			}
			
			public Set<Comment> getAllComments(String id) {
				if(movieRepo.existsById(id.strip())) {
					Movie movie = movieRepo.findById(id).get();
					return movie.getComments();
				}else {
					Set<Comment> noComments = Collections.emptySet();
					return noComments;
				}
				
    		
				
			}
			
			public boolean checkIfMovieExist(String id) {
				if(movieRepo.existsById(id.strip())) {
					return true;
				}else {
					return false;
				}
			}
			
			public boolean getMovieLikedStatus(String id,  String username) {
				user = userRepo.findById(username).get(); 	
				if (checkIfMovieExist(id)) {
					return user.getFavoriteMovies().contains(movieRepo.findById(id).get());
				}else {
					return false;
				}
				
			}
		
}
