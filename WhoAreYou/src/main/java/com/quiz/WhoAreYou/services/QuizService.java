package com.quiz.WhoAreYou.services;

import com.quiz.WhoAreYou.DTOs.AddRemoveQuestionDTO;
import com.quiz.WhoAreYou.models.Quiz;
import com.quiz.WhoAreYou.DTOs.QuizDTO;
import com.quiz.WhoAreYou.repositories.QuestionRepository;
import com.quiz.WhoAreYou.repositories.QuizRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Quiz addQuestionsToQuiz(Long id, AddRemoveQuestionDTO addRemoveQuestionDTO) {
        if (quizRepository.findById(id).isEmpty()){
            return null;
        }else{
            Quiz quiz = quizRepository.findById(id).get();
            //List<Question> questions = addRemoveQuestionDTO;

            for(Long questionId : addRemoveQuestionDTO.getQuestionIds()){ //loops through Ids in DTO
                if (questionRepository.findById(questionId).isPresent()){
                    quiz.addQuestion(questionRepository.findById(questionId).get()); //adds question to quiz
                    questionRepository.findById(questionId).get().addQuiz(quiz); //adds quiz to question
                }
            }
            quizRepository.save(quiz);
            return quiz;

        }
    }

    public Quiz removeQuestionFromQuiz(Long id, AddRemoveQuestionDTO removeQuestionDTO) {
        if(quizRepository.findById(id).isEmpty()){
            return null;
        }else {
            Quiz quiz = quizRepository.findById(id).get();

            for (Long questionId : removeQuestionDTO.getQuestionIds()){
                if(questionRepository.findById(questionId).isPresent() && (quiz.getQuestions().contains(questionRepository.findById(questionId).get()))){
                    quiz.removeQuestion(questionRepository.getById(questionId));
                }
            }
            quizRepository.save(quiz);
            return quiz;

        }
    }
}
