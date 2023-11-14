package com.quiz.WhoAreYou.services;

import com.quiz.WhoAreYou.models.QuestionDTO;
import com.quiz.WhoAreYou.repositories.QuestionRepository;
import com.quiz.WhoAreYou.models.Question;
import com.quiz.WhoAreYou.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuizRepository quizRepository;
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question addNewQuestion(QuestionDTO questionDTO) {
        Question question = new Question(questionDTO.getQuestion(),questionDTO.getOptionA(),questionDTO.getOptionB(),questionDTO.getOptionC(),questionDTO.getOptionD(),questionDTO.getZsoltAnswer(),questionDTO.getAnnaAnswer(),questionDTO.getColinAnswer(),questionDTO.getThibyaaAnswer());

        for (Long quizID : questionDTO.getQuizIds()){
            if (quizRepository.findById(quizID).isPresent()){
                question.addQuiz(quizRepository.findById(quizID).get());
            }
        }

        questionRepository.save(question);
        return question;
    }
}
