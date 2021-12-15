package Com.CaridadMichael.MovieTalk.MovieTalk.Entities;






import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import javax.persistence.OneToMany;

import lombok.Data;





@Entity
@Data

public class Movie {
	     @Id
		 private String id;
	     private String original_title;
		 private String release_date;
		 @Column(length = 3000) 
		 private String overview;
		 private String poster_path;
		 private String backdrop_path;
		 private String runtime;
		 private int rating = 0;
		
		 
		 
	    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	    @JoinTable(name = "movie_comments",
	            joinColumns = {
	                    @JoinColumn(name = "movie_id")
	            },
	            inverseJoinColumns = {
	                    @JoinColumn(name = "comment_id")
	            }
	    )
	    private Set<Comment> comments = new HashSet<Comment>();
	 
		
		  
		
		

		
	
		
		
}
