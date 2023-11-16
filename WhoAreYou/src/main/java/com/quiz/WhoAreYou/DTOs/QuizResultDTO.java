package com.quiz.WhoAreYou.DTOs;

public class QuizResultDTO {

    private Long quizId;
    private int zsoltScore;
    private int colinScore;
    private int annaScore;
    private int thibyaaScore;

    private String zsoltBio;
    private String colinBio;
    private String annaBio;
    private String thibyaaBio;

    public QuizResultDTO(Long quizId, int zsoltScore, int colinScore, int annaScore, int thibyaaScore) {
        this.quizId = quizId;
        this.zsoltScore = zsoltScore;
        this.colinScore = colinScore;
        this.annaScore = annaScore;
        this.thibyaaScore = thibyaaScore;
        this.zsoltBio = "The personality of a caffeine-overdosed golden retriever - Zsolt's wife\n" +
                        "The absolute most handsome boy in the world - Zsolt's mum\n" +
                        "I'm not saying he's annoying, but... - All of Zsolt's former/current colleagues, probably";
        this.colinBio = "";
        this.annaBio = "";
        this.thibyaaBio = "";
    }

    public QuizResultDTO() {
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
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

    public String getZsoltBio() {
        return zsoltBio;
    }

    public void setZsoltBio(String zsoltBio) {
        this.zsoltBio = zsoltBio;
    }

    public String getColinBio() {
        return colinBio;
    }

    public void setColinBio(String colinBio) {
        this.colinBio = colinBio;
    }

    public String getAnnaBio() {
        return annaBio;
    }

    public void setAnnaBio(String annaBio) {
        this.annaBio = annaBio;
    }

    public String getThibyaaBio() {
        return thibyaaBio;
    }

    public void setThibyaaBio(String thibyaaBio) {
        this.thibyaaBio = thibyaaBio;
    }
}
