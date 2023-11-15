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

import java.util.*;

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

//    @Transactional
//    public Quiz submitAnswer (Long id, AnswerDTO answerDTO) {
//        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
//        if (optionalQuiz.isPresent()) {
//            Quiz quiz = optionalQuiz.get();
//
//            if (!quiz.getFinished() && quiz.getRunning()) {
//                Long questionId = answerDTO.getQuestionId();
//                String userAnswer = answerDTO.getUserAnswer();
//                updateScores(quiz, questionId, userAnswer);
//                //quiz.setCurrentQuestionCounter();
//                quizRepository.save(quiz);
//                return quiz;
//            }
//        }
//        return null;
//    }
//
//        public void updateScores (Quiz quiz, Long questionId, String userAnswer) {
//
//            int pointIncrement = 1;
//            Question question = questionRepository.findById(questionId).get();
//            if (userAnswer.equals(question.getZsoltAnswer())){
//                quiz.setZsoltScore(quiz.getZsoltScore() + pointIncrement);
//
//
//            }
//            if (userAnswer.equals(question.getColinAnswer())) {
//                quiz.setColinScore(quiz.getColinScore()+ pointIncrement);
//            }
//            if (userAnswer.equals(question.getAnnaAnswer())) {
//                quiz.setAnnaScore(quiz.getAnnaScore()+ pointIncrement);
//            }
//
//            if (userAnswer.equals(question.getThibyaaAnswer())) {
//                quiz.setThibyaaScore(quiz.getThibyaaScore() + pointIncrement);
//            }
//            quizRepository.save(quiz);
//        }
//
//
//
    public Quiz startQuiz(Long id) {
        Optional<Quiz> optionalQuiz  = quizRepository.findById(id);
        if(optionalQuiz.isPresent()){
            Quiz quiz = optionalQuiz.get();
            if (!quiz.getRunning()) {
                quiz.setRunning(true);

                List<List<String>> newCurrentState = new ArrayList<>();
                for (Question question : quiz.getQuestions()) {
                    List<String> questionState = new ArrayList<>();
                    questionState.add("N");
                    questionState.add(question.getId().toString());
                    newCurrentState.add(questionState); // Add questionState to newCurrentState
                }
                quiz.setCurrentState(newCurrentState);
                quizRepository.save(quiz);
            }
        }
      return null;
    }

    public Quiz createRandomQuiz(int numberOfQuestions) throws Exception {
        List<Question> allQuestions = questionRepository.findAll();

        if (allQuestions.size() < numberOfQuestions){
            throw new Exception("Not enough questions available");
        }

        Collections.shuffle(allQuestions);
        List<Question> selectedQuestions = allQuestions.subList(0, numberOfQuestions);
        Quiz quiz = new Quiz(false, 0, 0, 0, 0, numberOfQuestions);
        for (Question question : selectedQuestions){
            quiz.addQuestion(question);
        }
        quizRepository.save(quiz);

        return quiz;
    }


    public Quiz answerQuestionFromQuiz(Long quizId, AnswerDTO answerDTO) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if(optionalQuiz.isPresent()){
            Quiz quiz = optionalQuiz.get();
            int questionNumber = Math.toIntExact(answerDTO.getQuestionNumber());
            List<String> currentQuestion = quiz.getCurrentState().get(questionNumber);
            currentQuestion.set(0, answerDTO.getUserAnswer());
            quiz.setCurrentStatebyQuestionNumber(questionNumber, currentQuestion);
            quizRepository.save(quiz);
        }
       return null;


    }



    public Quiz finishQuiz(Long quizId){
            int pointIncrement = 1;
           Optional<Quiz> optionalQuiz= quizRepository.findById(quizId);

           if(optionalQuiz.isPresent()) {
               Quiz quiz = optionalQuiz.get();
               for(List<String>questionState:quiz.getCurrentState()) {
                   Question question = questionRepository.findById(Long.valueOf(questionState.get(1))).get();
                   String userAnswer= questionState.get(0);
                   if (userAnswer.equals(question.getZsoltAnswer())) {
                       quiz.setZsoltScore(quiz.getZsoltScore() + pointIncrement);
                   }
                   if (userAnswer.equals(question.getColinAnswer())) {
                       quiz.setColinScore(quiz.getColinScore() + pointIncrement);
                   }
                   if (userAnswer.equals(question.getAnnaAnswer())) {
                       quiz.setAnnaScore(quiz.getAnnaScore() + pointIncrement);
                   }

                   if (userAnswer.equals(question.getThibyaaAnswer())) {
                       quiz.setThibyaaScore(quiz.getThibyaaScore() + pointIncrement);
                   }
               }
               quizRepository.save(quiz);
           }
           return null;
        }
}
