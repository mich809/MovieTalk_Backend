package Com.CaridadMichael.MovieTalk.MovieTalk.Repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.Role;

@Repository
public interface RoleRepo extends CrudRepository<Role, String> {

}