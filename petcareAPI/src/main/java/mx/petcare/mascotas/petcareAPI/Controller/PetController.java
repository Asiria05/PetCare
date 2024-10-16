package mx.petcare.mascotas.petcareAPI.Controller;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.petcare.mascotas.petcareAPI.Model.Pet;
import mx.petcare.mascotas.petcareAPI.Service.PetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("pets")  // Cambié "Pet" a "pets" para seguir las convenciones REST
@CrossOrigin(origins = "*")
public class PetController {

    @Autowired
    private PetService service;

    @Operation(summary = "Get all pets")
    @ApiResponse(responseCode = "200", description = "Pets retrieved", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Pet.class))) })
    @GetMapping
    public ResponseEntity<List<Pet>> getAll() {
        List<Pet> pets = service.getAll();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @Operation(summary = "Get pet by ID")
    @ApiResponse(responseCode = "200", description = "Pet retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class)))
    @ApiResponse(responseCode = "404", description = "Pet not found")
    @GetMapping("{idPet}")  // Cambié a GetMapping ya que esto debería ser un GET, no un POST
    public ResponseEntity<Pet> getByIdPet(@PathVariable Integer idPet) {
        try {
            Pet pet = service.getByidPet(idPet);
            return new ResponseEntity<>(pet, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create a new pet")
    @ApiResponse(responseCode = "201", description = "Pet created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class)))
    @PostMapping
    public ResponseEntity<Pet> create(@RequestBody Pet pet) {
        Pet createdPet = service.save(pet);
        return new ResponseEntity<>(createdPet, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a pet")
    @ApiResponse(responseCode = "200", description = "Pet updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class)))
    @ApiResponse(responseCode = "404", description = "Pet not found")
    @PutMapping("{idPet}")
    public ResponseEntity<Pet> update(@RequestBody Pet pet, @PathVariable Integer idPet) {
        try {
            pet.setPetId(idPet); // Asigna el ID proporcionado en la URL
            Pet updatedPet = service.save(pet);
            return new ResponseEntity<>(updatedPet, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a pet")
    @ApiResponse(responseCode = "200", description = "Pet deleted")
    @ApiResponse(responseCode = "404", description = "Pet not found")
    @DeleteMapping("{idPet}")
    public ResponseEntity<String> delete(@PathVariable Integer idPet) {
        try {
            service.delete(idPet);
            return new ResponseEntity<>("Pet deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Pet not found", HttpStatus.NOT_FOUND);
        }
    }
}
