package Com.CaridadMichael.MovieTalk.MovieTalk.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.Movie;

@Repository
public interface MovieRepo extends CrudRepository<Movie,String> {
	
	

}
