package com.quiz.WhoAreYou.components;

import com.quiz.WhoAreYou.models.Question;
import com.quiz.WhoAreYou.repositories.QuestionRepository;
import com.quiz.WhoAreYou.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuizRepository quizRepository;


    public DataLoader() {
    }


    public void run (ApplicationArguments args)throws Exception{

        Question question1 = new Question(
                "What is your favourite colour",
                "A) Purple",
                "B) Blue",
                "C) Red",
                "D) Green",
                "C","D",
                "A","B");

        questionRepository.save(question1);

    }

}