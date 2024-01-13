//package nl.novi.techiteasy.controllers;
//
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/votes")
//public class VotingController {
//    private List<String> votes = new ArrayList<>();
//    @PostMapping
//    public String createVote(@RequestParam String partij) {
//        votes.add(partij);
//        return "Je hebt gestemd op " + partij;
//    }
//
//    @GetMapping
//    public List<String> getAllVotes() {
//        return votes;
//    }
//
//    @GetMapping("/count")
//    public String getVoteCount() {
//        int count = votes.size();
//      //  String countString = String.valueOf(count);
//        return "er is "  + count + " keer gestemed";
//    }
//}
