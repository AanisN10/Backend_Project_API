package com.quiz.WhoAreYou.services;

import com.quiz.WhoAreYou.models.Quiz;
import com.quiz.WhoAreYou.models.QuizDTO;
import com.quiz.WhoAreYou.repositories.QuestionRepository;
import com.quiz.WhoAreYou.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    public List<Quiz> findAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> findQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public Quiz addNewQuiz(QuizDTO quizDTO) {
        Quiz quiz = new Quiz(quizDTO.getFinished(), quizDTO.getZsoltScore(), quizDTO.getColinScore(), quizDTO.getAnnaScore(), quizDTO.getThibyaaScore());

        for (Long questionId : quizDTO.getQuestionIds()){
            if (questionRepository.findById(questionId).isPresent()){
                quiz.addQuestion(questionRepository.findById(questionId).get());
            }
        }

        quizRepository.save(quiz);
        return quiz;
    }

    public Long removeQuizById(Long id) {
       if(quizRepository.findById(id).isPresent()){
           quizRepository.deleteById(id);
           return id;
       } else{
           return null;
       }

    }
}
