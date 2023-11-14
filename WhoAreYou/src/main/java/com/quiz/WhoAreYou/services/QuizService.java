package com.quiz.WhoAreYou.services;

import com.quiz.WhoAreYou.models.Quiz;
import com.quiz.WhoAreYou.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    public List<Quiz> findAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> findQuizById(Long id) {
        return quizRepository.findById(id);
    }
}
