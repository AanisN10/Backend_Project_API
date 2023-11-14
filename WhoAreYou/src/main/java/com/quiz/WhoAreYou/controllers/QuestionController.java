package com.quiz.WhoAreYou.controllers;

import com.quiz.WhoAreYou.models.Question;
import com.quiz.WhoAreYou.models.QuestionDTO;
import com.quiz.WhoAreYou.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions(){
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Question> makeNewQuestion(@RequestBody QuestionDTO questionDTO){
        return new ResponseEntity<>(questionService.addNewQuestion(questionDTO),HttpStatus.CREATED);
    }




}
