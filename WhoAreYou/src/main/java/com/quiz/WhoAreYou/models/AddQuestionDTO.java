package com.quiz.WhoAreYou.models;

import java.util.List;

public class AddQuestionDTO {

    private List<Long> questionIds;

    public AddQuestionDTO(List<Long> questionIds) {
        this.questionIds = questionIds;
    }

    public AddQuestionDTO() {
    }

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }
}
