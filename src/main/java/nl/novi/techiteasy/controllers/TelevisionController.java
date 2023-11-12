package nl.novi.techiteasy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class TelevisionController {

    ArrayList<String> television = new ArrayList<>();
    @PostMapping("/add")
    public void SaveTelevision(@RequestParam String tv) {
        this.television.add(tv);
    }

    @GetMapping("/show")
    public String retriveTelevision() {

        if(this.television.isEmpty()) {
            return "no tv provided";
        }
        else {
            return "this is your tv's " + television;
        }
    }
}
