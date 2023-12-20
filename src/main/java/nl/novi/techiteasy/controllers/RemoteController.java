package nl.novi.techiteasy.controllers;

import jakarta.validation.ValidationException;
import nl.novi.techiteasy.dtos.RemoteControllerInputDto;
import nl.novi.techiteasy.dtos.RemoteControllerOutPutDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.services.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import static nl.novi.techiteasy.controllers.ControllerHelper.checkForBindingResult;

@RestController
@RequestMapping("/remoteControllers")
public class RemoteController {

    private final RemoteControllerService service;

    public RemoteController(RemoteControllerService remoteControllerService) {
        this.service = remoteControllerService;
    }

    @GetMapping
    public ResponseEntity<List<RemoteControllerOutPutDto>> getAllRemoteControllers(){
        return ResponseEntity.ok(service.getAllRemoteControllers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerOutPutDto> getRemoteControllerById(@PathVariable Long id){
        if (id > 0) {
            RemoteControllerOutPutDto remoteController = service.getRemoteControllerId(id);
            return ResponseEntity.ok(remoteController);
        } else {
            throw new RecordNotFoundException("there is no RemoteController");
        }
    }

    @PostMapping
    public ResponseEntity<RemoteControllerInputDto> createRemoteController (@RequestBody RemoteControllerInputDto remoteControllerInputDto, BindingResult br) {
        if (br.hasFieldErrors()){
            throw new ValidationException(checkForBindingResult(br));
        } else {
            RemoteControllerInputDto savedRC = service.createRemoteController(remoteControllerInputDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + savedRC.id).toUriString());
            return ResponseEntity.created(uri).body(savedRC);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemoteControllerOutPutDto> updateRemoteController(@PathVariable long id, @RequestBody RemoteControllerInputDto remoteControllerInputDto) {
        RemoteControllerOutPutDto changeRC = service.updateRemoteController(id, remoteControllerInputDto);

        return ResponseEntity.ok().body(changeRC);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RemoteControllerInputDto> deleteRC(@PathVariable long id) {
        service.deleteRemoteController(id);
        return ResponseEntity.noContent().build();
    }
}
