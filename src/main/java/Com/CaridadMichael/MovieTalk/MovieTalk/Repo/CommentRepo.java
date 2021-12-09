package Com.CaridadMichael.MovieTalk.MovieTalk.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.Comment;

@Repository
public interface CommentRepo extends CrudRepository<Comment,Long> {

}
