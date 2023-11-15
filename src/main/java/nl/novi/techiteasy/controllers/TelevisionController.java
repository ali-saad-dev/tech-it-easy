package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.CreateTvRequest;
import nl.novi.techiteasy.models.Television;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final ArrayList<Television> televisionDataBase = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);
    @PostMapping
    public ResponseEntity<Television> create(@RequestBody CreateTvRequest model) {
        int id = idCounter.getAndIncrement();
        Television result = new Television(id, model.name, model.price);
        this.televisionDataBase.add(result);
        return ResponseEntity.created(null).body(result);
    }

    @GetMapping("/get")
    public ResponseEntity<Television> get() {
        if(this.televisionDataBase.isEmpty()) {
            throw new RecordNotFoundException("Television not found");
        }
        else {
            return ResponseEntity.ok(this.televisionDataBase.get(0));
        }
    }

    @GetMapping
    public ResponseEntity<ArrayList<Television>> getAll() {

        if(this.televisionDataBase.isEmpty()) {
            throw new RecordNotFoundException("There is no televisions available");
        }
        else {
            return new ResponseEntity<>(this.televisionDataBase, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ArrayList<Television>> delete(@PathVariable int id) {
        Iterator<Television> iterator = this.televisionDataBase.iterator();
        while ((iterator).hasNext()) {
            Television tv = iterator.next();
            if (tv.getId() == id) {
                iterator.remove();
                return ResponseEntity.ok().build();
            }
        }
        throw new RecordNotFoundException("There is no televisions available to delete");
    }
    @PutMapping("/{id}")
    public ResponseEntity<Television> update(@PathVariable int id, @RequestBody CreateTvRequest model) {
        Optional<Television> optionalTv = this.televisionDataBase.stream().filter(tv -> tv.getId() == id).findFirst();
        if ((optionalTv).isPresent()) {
            Television tvToUpdate = optionalTv.get();
            tvToUpdate.setName(model.name);
            tvToUpdate.setPrice(model.price);
            return ResponseEntity.ok(tvToUpdate);
        } else {
            throw new RecordNotFoundException("Update failed");
        }
    }
}
