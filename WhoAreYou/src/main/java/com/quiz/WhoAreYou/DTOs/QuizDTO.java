package com.quiz.WhoAreYou.DTOs;

import jakarta.persistence.Column;

import java.util.List;

public class QuizDTO {

    private Boolean isFinished;
    private int zsoltScore;
    private int colinScore;
    private int annaScore;
    private int thibyaaScore;
    private int numberOfQuestions;
    private List<Long> questionIds;

    public QuizDTO(Boolean isFinished, int zsoltScore, int colinScore,
                   int annaScore, int thibyaaScore, int numberOfQuestions, List<Long> questionIds) {
        this.isFinished = isFinished;
        this.zsoltScore = zsoltScore;
        this.colinScore = colinScore;
        this.annaScore = annaScore;
        this.thibyaaScore = thibyaaScore;
        this.numberOfQuestions = numberOfQuestions;
        this.questionIds = questionIds;
    }

    public QuizDTO() {
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

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
}
