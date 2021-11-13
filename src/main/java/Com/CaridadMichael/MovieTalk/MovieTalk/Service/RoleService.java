package Com.CaridadMichael.MovieTalk.MovieTalk.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.CaridadMichael.MovieTalk.MovieTalk.Entities.Role;
import Com.CaridadMichael.MovieTalk.MovieTalk.Repo.RoleRepo;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role createNewRole(Role role) {
        return roleRepo.save(role);
    }
}
