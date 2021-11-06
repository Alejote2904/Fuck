package com.bike.service;

import com.bike.model.Reservation;
import com.bike.model.Score;
import com.bike.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll() {
        return scoreRepository.getAll();
    }

    public Optional<Score> getScore(int scoreId) {
        return scoreRepository.getScore(scoreId);
    }

    public Score save(Score score) {
        if (score.getStars() >= 0 && score.getStars() <= 5) {
            if (score.getIdScore() == null) {
                return scoreRepository.save(score);
            } else {
                Optional<Score> e = scoreRepository.getScore(score.getIdScore());
                if (e.isEmpty()) {
                    return scoreRepository.save(score);
                }
            }

        }
        return score;
    }
    
    public boolean deleteScore(int id) {

        Optional<Score> score = getScore(id);

        if (score.isEmpty()) {
            return false;
        } else {
            scoreRepository.delete(score.get());
            return true;
        }
    }
    
    public Score updateScore(Score score) {
        if (score.getIdScore() != null) {
            Optional<Score> e = scoreRepository.getScore(score.getIdScore());
            if (!e.isEmpty()) {
                if (score.getMessageText() != null) {
                    e.get().setMessageText(score.getMessageText());
                }
                if (score.getStars() != null && score.getStars() >= 0 && score.getStars() <= 5) {
                    e.get().setStars(score.getStars());
                }
                scoreRepository.save(e.get());
                return e.get();
            } else {
                return score;
            }
        } else {
            return score;
        }
    }
}
