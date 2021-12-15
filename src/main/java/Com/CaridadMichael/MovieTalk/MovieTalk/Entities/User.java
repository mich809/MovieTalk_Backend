package Com.CaridadMichael.MovieTalk.MovieTalk.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.Data;


import java.util.Set;

@Entity
@Data
@Component
public class User {

    @Id
    @NotBlank
    @Size(min = 4)
    private String username;  
    
    
    @NotBlank
    private String password;
  
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    private Set<Role> role ;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_movies",
            joinColumns = {
                    @JoinColumn(name = "username")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "movie_id")
            }
    )
    private Set<Movie> favoriteMovies;
    
    


}