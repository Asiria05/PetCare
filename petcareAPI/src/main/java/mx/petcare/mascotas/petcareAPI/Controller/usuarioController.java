package mx.petcare.mascotas.petcareAPI.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.petcare.mascotas.petcareAPI.Service.UserService;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

public class usuarioController {

    @Autowired
    private UserService service;
     
    @GetMapping
    public List<User> getAll(){
        return service.getAll();
    }

    @GetMapping("{name}")
    public ResponseEntity<User> getByName(@PathVariable String name){
        User user = service.getByName(name);
        return new ResponseEntity<User>(users, httpStatus.OK);
    }

    @PostMapping
    public void ingresar(@RequestBody User user){
        service.save(user);
    }
    @PutMapping("{name}")
    public ResponseEntity<?> update (@RequestBody User user, @PathVariable String name){
        try {
            User auxUser = service.getByName(name);
            user.setName(auxUser.getName());
            service.save(user);
            return new ResponseEntity<String>("Updated record", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<String>("The record", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{name}")
    public void delete(@PathVariable String name){
        service.delete(name);
    }
    
}
