package mx.petcare.mascotas.petcareAPI.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import mx.petcare.mascotas.petcareAPI.Model.Pet;
import mx.petcare.mascotas.petcareAPI.Repository.PetRepository;

@Service
@Transactional
public class PetService {
    @Autowired

    private PetRepository repo; 

    public List<Pet> getAll() {
        return repo.findAll();
    }

    public Pet save(Pet pet) {
        return repo.save(pet);
    }

    public Pet getByidPet(Integer petId) {
        return repo.findById(petId).get();
    }

    public void delete(Integer petId) {
        repo.deleteById(petId);
    }

}
 
