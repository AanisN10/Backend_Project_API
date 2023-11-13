package com.quiz.WhoAreYou.services;

import com.quiz.WhoAreYou.repositories.QuestionRepository;
import com.quiz.WhoAreYou.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
