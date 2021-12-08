package Com.CaridadMichael.MovieTalk.MovieTalk.Entities;






import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;


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
		 
		 
		
		  
		
		

		
	
		
		
}
