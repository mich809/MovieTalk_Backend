package Com.CaridadMichael.MovieTalk.MovieTalk.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Comment {
	
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id;
	  private String comment;
	  private String postedBy;

}
