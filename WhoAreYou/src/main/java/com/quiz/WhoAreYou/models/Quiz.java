package com.quiz.WhoAreYou.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private int currentQuestionCounter;
    @Column
    private Boolean isFinished;
    @Column
    private Boolean isRunning;

    @Column
    private int zsoltScore;
    @Column
    private int colinScore;
    @Column
    private int annaScore;
    @Column
    private int thibyaaScore;
    @ElementCollection
    @CollectionTable
    @Column
    private List<List<String>> currentState;
    @Column
    private int numberOfQuestions;

    @JsonIgnoreProperties({"quizzes"})
    @ManyToMany
    @JoinTable(name = "quizzes_questions",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questions;

    public Quiz(Boolean isFinished, int zsoltScore, int colinScore, int annaScore, int thibyaaScore, int numberOfQuestions){
        this.isFinished = isFinished;
        this.zsoltScore = zsoltScore;
        this.colinScore = colinScore;
        this.annaScore = annaScore;
        this.thibyaaScore = thibyaaScore;
        this.questions = new ArrayList<>();
        this.isRunning = false;
        this.currentState = new ArrayList<>();
        this.numberOfQuestions = numberOfQuestions;
        this.currentQuestionCounter = 0;
    }

    public Quiz() {
    }

    public int getCurrentQuestionCounter() {
        return currentQuestionCounter;
    }

    public void setCurrentQuestionCounter(int currentQuestionCounter) {
        this.currentQuestionCounter = currentQuestionCounter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public int getZsoltScore() {
        return zsoltScore;
    }

    public void setZsoltScore(int zsoltScore) {
        this.zsoltScore = zsoltScore;
    }

    public int getColinScore() {
        return colinScore;
    }

    public void setColinScore(int colinScore) {
        this.colinScore = colinScore;
    }

    public int getAnnaScore() {
        return annaScore;
    }

    public void setAnnaScore(int annaScore) {
        this.annaScore = annaScore;
    }

    public int getThibyaaScore() {
        return thibyaaScore;
    }

    public void setThibyaaScore(int thibyaaScore) {
        this.thibyaaScore = thibyaaScore;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Boolean getRunning() {
        return isRunning;
    }

    public void setRunning(Boolean running) {
        isRunning = running;
    }

    public void removeQuestion(Question question) {
        this.questions.remove(question);
    }

    public void addQuestion(Question question) { this.questions.add(question);
    }

    public List<List<String>> getCurrentState() {
        return currentState;
    }

    public void setCurrentState(List<List<String>> currentState) {
        this.currentState = currentState;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public void setCurrentStatebyQuestionNumber(int questionNumber, List<String> questionState){
        this.currentState.set(questionNumber,questionState);
    }
}
