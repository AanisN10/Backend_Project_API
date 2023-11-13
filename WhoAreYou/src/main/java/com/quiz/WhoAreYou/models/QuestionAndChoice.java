package com.quiz.WhoAreYou.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "questions")
public class QuestionAndChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;


    @Column
    private String question;

    @Column
    private String optionA;
    @Column
    private String optionB;
    @Column
    private String optionC;
    @Column
    private String optionD;

    @Column
    private HashMap<String,String> answerKey;


    @JsonIgnoreProperties({"questions"})
    @ManyToMany(mappedBy = "questions")
    private List<Quiz> quizzes;

    public QuestionAndChoice(String question,
                             String optionA,
                             String optionB,
                             String optionC,
                             String optionD) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answerKey = new HashMap<String,String>();
        this.quizzes = new ArrayList<Quiz>();
    }

    public QuestionAndChoice() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public HashMap<String, String> getAnswerKey() {
        return answerKey;
    }

    public void setAnswerKey(HashMap<String, String> answerKey) {
        this.answerKey = answerKey;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
