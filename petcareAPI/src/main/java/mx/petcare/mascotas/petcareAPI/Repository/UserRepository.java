package mx.petcare.mascotas.petcareAPI.Repository;

import org.springframework.stereotype.Repository;

public class UserRepository {
    @Repository
    public interface UserRepository extends JpaRepository<User, String> {
    
        
    }
}
