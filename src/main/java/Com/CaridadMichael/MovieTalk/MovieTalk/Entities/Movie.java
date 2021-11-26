package Com.CaridadMichael.MovieTalk.MovieTalk.Entities;




import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;




@Entity
@Data

public class Movie {
		@Id
	    private String title; 	
		
//		@EqualsAndHashCode.Exclude
//		@ToString.Exclude
//		@ManyToMany(mappedBy = "movies", fetch = FetchType.EAGER)
//	    private Set<User> users = new HashSet<User>();
		
	
		
		
}
