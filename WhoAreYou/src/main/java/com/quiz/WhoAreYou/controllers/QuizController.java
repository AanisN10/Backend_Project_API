package com.quiz.WhoAreYou.controllers;

import com.quiz.WhoAreYou.models.Question;
import com.quiz.WhoAreYou.models.Quiz;
import com.quiz.WhoAreYou.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quizzes")
public class QuizController {
    @Autowired
    QuizService quizService;

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes(){
        return new ResponseEntity<>(quizService.findAllQuizzes(), HttpStatus.OK);
    }

    @GetMapping (value = "/{id}")

    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id){

        Optional<Quiz> getQuizById = quizService.findQuizById(id);
        if(getQuizById.isPresent()){
            return new ResponseEntity<>(getQuizById.get(), HttpStatus.OK);
        } else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
