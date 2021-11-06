package com.bike.controller;

import com.bike.model.Score;
import com.bike.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/Score")
@CrossOrigin("*")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/all")
    public List<Score> getServices() {
        return scoreService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Score> getService(@PathVariable("id") int scoreId) {
        return scoreService.getScore(scoreId);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Score save(@RequestBody Score score) {
        return scoreService.save(score);
    }
    
    @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteScore(@PathVariable int id){
        return scoreService.deleteScore(id);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Score updateScore(@RequestBody Score score){
        return scoreService.updateScore(score);
    }
}
