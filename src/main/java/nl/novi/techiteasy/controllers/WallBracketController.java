package nl.novi.techiteasy.controllers;

import jakarta.validation.ValidationException;
import nl.novi.techiteasy.dtos.WallBracketInputDto;
import nl.novi.techiteasy.dtos.WallBracketOutPutDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.services.WallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static nl.novi.techiteasy.controllers.ControllerHelper.checkForBindingResult;

@RestController
@RequestMapping("/wallBrackets")
public class WallBracketController {

    private final WallBracketService service;

    public WallBracketController(WallBracketService WallBracketService) {
        this.service = WallBracketService;
    }

    @GetMapping
    public ResponseEntity<List<WallBracketOutPutDto>> getAllWallBrackets(){
        return ResponseEntity.ok(service.getAllWallBrackets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WallBracketOutPutDto> getRemoteControllerById(@PathVariable Long id){
        if (id > 0) {
            WallBracketOutPutDto wallBracketId = service.getWallBracketId(id);
            return ResponseEntity.ok(wallBracketId);
        } else {
            throw new RecordNotFoundException("there is no wallBracket");
        }
    }

    @PostMapping
    public ResponseEntity<WallBracketInputDto> createWallBracket (@RequestBody WallBracketInputDto wallBracketInputDto, BindingResult br) {
        if (br.hasFieldErrors()){
            throw new ValidationException(checkForBindingResult(br));
        } else {
            WallBracketInputDto saveWallBrackt = service.createWallBracket(wallBracketInputDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + saveWallBrackt.id).toUriString());
            return ResponseEntity.created(uri).body(saveWallBrackt);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<WallBracketOutPutDto> updateWallBracket(@PathVariable long id, @RequestBody WallBracketInputDto wallBracketInputDto) {
        WallBracketOutPutDto changeWallBracket = service.updateWallBracket(id, wallBracketInputDto);

        return ResponseEntity.ok().body(changeWallBracket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WallBracketInputDto> deleteRC(@PathVariable long id) {
        service.deleteWallBracket(id);
        return ResponseEntity.noContent().build();
    }
}
