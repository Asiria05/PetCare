package mx.petcare.mascotas.petcareAPI.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import mx.petcare.mascotas.petcareAPI.Model.User;
import mx.petcare.mascotas.petcareAPI.Repository.UserRepository;


@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> getAll(){
        return repo.findAll();
    }

    public void save(User user){
        repo.save(user);
    }

    public User getByName(String name){
        return repo.findById(name).get();
    }

    public void delete(String name){
        repo.deleteById(name);
    }

}
