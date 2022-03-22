package com.example.question.model;

import java.util.Objects;

public class Question {
    private Integer questionId;
    private String question;
    private Integer questionTypeId;

    public Integer getQuestionId() {
        return questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(questionId, question1.questionId) && Objects.equals(question, question1.question) && Objects.equals(questionTypeId, question1.questionTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, question, questionTypeId);
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public Question(Integer questionId, String question, Integer questionTypeId) {
        this.questionId = questionId;
        this.question = question;
        this.questionTypeId = questionTypeId;
    }

}
