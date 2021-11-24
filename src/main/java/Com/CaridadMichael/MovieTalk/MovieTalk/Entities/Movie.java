package Com.CaridadMichael.MovieTalk.MovieTalk.Entities;




import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;


@Entity
@Data
public class Movie {
		@Id
	    private String title; 	
		
		@ManyToMany(mappedBy = "movies", fetch = FetchType.EAGER)
	    private Set<User> users = new HashSet<User>();
		
	
		
		
}
