package com.quiz.WhoAreYou.services;

import com.quiz.WhoAreYou.DTOs.AddRemoveQuestionDTO;
import com.quiz.WhoAreYou.DTOs.AnswerDTO;
import com.quiz.WhoAreYou.models.Question;
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
        Quiz quiz = new Quiz(quizDTO.getFinished(), quizDTO.getZsoltScore(), quizDTO.getColinScore(), quizDTO.getAnnaScore(), quizDTO.getThibyaaScore(), quizDTO.getNumberOfQuestions());

        for (Long questionId : quizDTO.getQuestionIds()) {
            if (questionRepository.findById(questionId).isPresent()) {
                quiz.addQuestion(questionRepository.findById(questionId).get());
            }
        }

        quizRepository.save(quiz);
        return quiz;
    }

    public Long removeQuizById(Long id) {
        if (quizRepository.findById(id).isPresent()) {
            quizRepository.deleteById(id);
            return id;
        } else {
            return null;
        }

    }

    @Transactional
    public Quiz addQuestionsToQuiz(Long id, AddRemoveQuestionDTO addRemoveQuestionDTO) {

        if (quizRepository.findById(id).isEmpty()) {
            return null;
        } else {
            Quiz quiz = quizRepository.findById(id).get();
            //List<Question> questions = addRemoveQuestionDTO;

            if (!quiz.getRunning()) {


                for (Long questionId : addRemoveQuestionDTO.getQuestionIds()) { //loops through Ids in DTO
                    if (questionRepository.findById(questionId).isPresent()) {
                        quiz.addQuestion(questionRepository.findById(questionId).get()); //adds question to quiz
                        questionRepository.findById(questionId).get().addQuiz(quiz); //adds quiz to question
                    }
                }
                quizRepository.save(quiz);
            }
            return quiz;
        }

    }


    public Quiz removeQuestionFromQuiz(Long id, AddRemoveQuestionDTO removeQuestionDTO) {
        if (quizRepository.findById(id).isEmpty()) {
            return null;
        } else {
            Quiz quiz = quizRepository.findById(id).get();

            if (!quiz.getRunning()) {


                for (Long questionId : removeQuestionDTO.getQuestionIds()) {
                    if (questionRepository.findById(questionId).isPresent() && (quiz.getQuestions().contains(questionRepository.findById(questionId).get()))) {
                        quiz.removeQuestion(questionRepository.getById(questionId));
                    }
                }
                quizRepository.save(quiz);

            }
            return quiz;
        }
    }

    @Transactional
    public Quiz submitAnswer (Long id, AnswerDTO answerDTO){
            Optional<Quiz> optionalQuiz = quizRepository.findById(id);
            if (optionalQuiz.isPresent()) {
                Quiz quiz = optionalQuiz.get();
                Long questionId = answerDTO.getQuestionId();
                String userAnswer = answerDTO.getUserAnswer();
                updateScores(quiz, questionId, userAnswer);
                quizRepository.save(quiz);
                return quiz;
            } else {
                return null;
            }
        }

        public void updateScores (Quiz quiz, Long questionId, String userAnswer) {

            int pointIncrement = 1;
            Question question = questionRepository.findById(questionId).get();
            if (userAnswer.equals(question.getZsoltAnswer())){
                quiz.setZsoltScore(quiz.getZsoltScore() + pointIncrement);


            }
            if (userAnswer.equals(question.getColinAnswer())) {
                quiz.setColinScore(quiz.getColinScore()+ pointIncrement);
            }
            if (userAnswer.equals(question.getAnnaAnswer())) {
                quiz.setAnnaScore(quiz.getAnnaScore()+ pointIncrement);
            }

            if (userAnswer.equals(question.getThibyaaAnswer())) {
                quiz.setThibyaaScore(quiz.getThibyaaScore() + pointIncrement);
            }
            quizRepository.save(quiz);
        }



    public Quiz startQuiz(Long id) {
        Optional<Quiz> optionalQuiz  = quizRepository.findById(id);
        if(optionalQuiz.isPresent()){
            Quiz quiz = optionalQuiz.get();
            if (!quiz.getRunning()) {
                quiz.setRunning(true);

                String newCurrentState = "";
                for (Question question : quiz.getQuestions()) {
                    newCurrentState += "N" + question.getId();
                }
                quiz.setCurrentState(newCurrentState);
                quizRepository.save(quiz);
            }
        }
      return null;
    }
}
