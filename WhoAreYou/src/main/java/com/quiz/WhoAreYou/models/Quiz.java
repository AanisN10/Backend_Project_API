package com.quiz.WhoAreYou.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean isFinished;

    @Column
    private HashMap<String, Integer> score;

    @JsonIgnoreProperties({"quizzes"})
    @ManyToMany
    @JoinTable(name = "quizzes_questions",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<QuestionAndChoice> questionAndChoices;

    public Quiz(Boolean isFinished) {
        this.isFinished = false;
        this.score = new HashMap<String, Integer>();
        this.questionAndChoices = new ArrayList<QuestionAndChoice>();
    }

    public Quiz() {
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

    public HashMap<String, Integer> getScore() {
        return score;
    }

    public void setScore(HashMap<String, Integer> score) {
        this.score = score;
    }

    public List<QuestionAndChoice> getQuestionAndChoices() {
        return questionAndChoices;
    }

    public void setQuestionAndChoices(List<QuestionAndChoice> questionAndChoices) {
        this.questionAndChoices = questionAndChoices;
    }
}
