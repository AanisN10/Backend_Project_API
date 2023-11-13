package com.quiz.WhoAreYou.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Long id;


    @Column
    private String question;
    @Column
    private String zsoltAnswer;
    @Column
    private String annaAnswer;
    @Column
    private String colinAnswer;
    @Column
    private String thibyaaAnswer;

    @ManyToMany(mappedBy = "questions")
    @JsonIgnoreProperties({"questions"})
    private List<Quiz> quizzes;

    public Question(String question, String zsoltAnswer, String annaAnswer, String colinAnswer, String thibyaaAnswer) {
        this.question = question;
        this.zsoltAnswer = zsoltAnswer;
        this.annaAnswer = annaAnswer;
        this.colinAnswer = colinAnswer;
        this.thibyaaAnswer = thibyaaAnswer;
        this.quizzes = new ArrayList<>();
    }

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getZsoltAnswer() {
        return zsoltAnswer;
    }

    public void setZsoltAnswer(String zsoltAnswer) {
        this.zsoltAnswer = zsoltAnswer;
    }

    public String getAnnaAnswer() {
        return annaAnswer;
    }

    public void setAnnaAnswer(String annaAnswer) {
        this.annaAnswer = annaAnswer;
    }

    public String getColinAnswer() {
        return colinAnswer;
    }

    public void setColinAnswer(String colinAnswer) {
        this.colinAnswer = colinAnswer;
    }

    public String getThibyaaAnswer() {
        return thibyaaAnswer;
    }

    public void setThibyaaAnswer(String thibyaaAnswer) {
        this.thibyaaAnswer = thibyaaAnswer;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}