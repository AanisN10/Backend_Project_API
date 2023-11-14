package com.quiz.WhoAreYou.services;

import com.quiz.WhoAreYou.models.Quiz;
import com.quiz.WhoAreYou.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    public List<Quiz> findAllQuizzes() {
        return quizRepository.findAll();
    }
}
