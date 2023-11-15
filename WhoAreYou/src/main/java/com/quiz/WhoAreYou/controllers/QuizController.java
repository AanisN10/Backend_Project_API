package com.quiz.WhoAreYou.controllers;

import com.quiz.WhoAreYou.DTOs.AddRemoveQuestionDTO;
import com.quiz.WhoAreYou.DTOs.AnswerDTO;
import com.quiz.WhoAreYou.models.Quiz;
import com.quiz.WhoAreYou.DTOs.QuizDTO;
import com.quiz.WhoAreYou.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Quiz> makeNewQuiz(@RequestBody QuizDTO quizDTO){
        return new ResponseEntity<>(quizService.addNewQuiz(quizDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteQuizById(@PathVariable Long id){
        Long removeById = quizService.removeQuizById(id);
        if(removeById != null){
            return new ResponseEntity<Long>(id, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Quiz> addQuestionsToQuiz(@PathVariable Long id, @RequestBody AddRemoveQuestionDTO addQuestionDTO){
        return new ResponseEntity<>(quizService.addQuestionsToQuiz(id, addQuestionDTO),HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeQuestion/{id}")
    public ResponseEntity<Quiz> removeQuestionsFromQuiz(@PathVariable Long id, @RequestBody AddRemoveQuestionDTO removeQuestionDTO){
        return new ResponseEntity<>(quizService.removeQuestionFromQuiz(id,removeQuestionDTO),HttpStatus.OK);
    }

    @PostMapping(value = "/answer/{id}")
    public ResponseEntity<Quiz> submitAnswer(@PathVariable Long id,
                                             @RequestBody AnswerDTO answerDTO) {
        return new ResponseEntity<>(quizService.submitAnswer(id, answerDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/startQuiz/{id}")
    public ResponseEntity<Quiz> startQuiz(@PathVariable Long id){
        return new ResponseEntity<>(quizService.startQuiz(id), HttpStatus.OK);
    }

    @PostMapping("/random")
    public ResponseEntity<Quiz> createRandomQuiz(@RequestParam int numberOfQuestions) throws Exception {
        try {
            Quiz quiz = quizService.createRandomQuiz(numberOfQuestions);
            return new ResponseEntity<>(quiz, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
