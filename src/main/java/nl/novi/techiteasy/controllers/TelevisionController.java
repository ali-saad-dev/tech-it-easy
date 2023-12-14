package nl.novi.techiteasy.controllers;
import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.IdInputDto;
import nl.novi.techiteasy.dtos.TelevisionInputDto;
import nl.novi.techiteasy.dtos.TelevisionOutPutDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.services.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;



@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final TelevisionService service;

    public TelevisionController(TelevisionService televisionService){this.service = televisionService;}

    @GetMapping
    public ResponseEntity<List<TelevisionOutPutDto>> getAllTelevision() {
        return ResponseEntity.ok(service.GetAllTelevision());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TelevisionOutPutDto> getTelevisionById(@PathVariable Long id) {
        TelevisionOutPutDto television = service.getTelevisionById(id);
        return ResponseEntity.ok(television);
    }
    @PostMapping
    public ResponseEntity<Object> createTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto, BindingResult br) {

        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        }
        else {
            televisionInputDto = service.createTelevision(televisionInputDto);

            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + televisionInputDto.id).toUriString());

            return ResponseEntity.created(uri).body(televisionInputDto);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelevisionOutPutDto> updateTelevision(@PathVariable Long id, @RequestBody TelevisionInputDto televisionInputDto) {
        try {
            TelevisionOutPutDto updatedTelevision = service.updateTelevision(id, televisionInputDto);
            return ResponseEntity.ok(updatedTelevision);
        } catch (RecordNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TelevisionInputDto> deleteTelevision(@PathVariable Long id) {
        try {
            service.deleteTelevision(id);
            return ResponseEntity.noContent().build();
        } catch (RecordNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/remotecontroller")
    public ResponseEntity<String> putRemoteController(@PathVariable Long id, @RequestBody IdInputDto remoteControllerIdDto) {
        try {
            Long remoteControllerId = remoteControllerIdDto.getId();
            service.assignRemoteControllerToTelevision(id, remoteControllerId);
            return ResponseEntity.ok("RemoteController assigned to Television successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error assigning RemoteController to Television: " + e.getMessage());
        }
    }
}
