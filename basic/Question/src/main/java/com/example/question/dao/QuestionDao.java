package com.example.question.dao;

import com.example.question.model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    public List<Question> getQuestions(String tableName) throws SQLException, ClassNotFoundException {
        List<Question> questions = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hb_student_tracker?" +
                    "user=root&password=StrongPassword1$");

            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("select questionId, question, questionTypeId from " + tableName + ";");
            while (rset.next()) {
                if (questions == null) questions = new ArrayList<>();
                Integer questionId = rset.getInt(1);
                String questionStr = rset.getString(2);
                Integer questionTypeId = rset.getInt(3);
                Question question = new Question(questionId, questionStr, questionTypeId);
                questions.add(question);
            }
        } finally {
            if (conn != null) conn.close();
        }
        return questions;
    }

    public Integer addQuestion(String tableName, Question question) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Integer questionId = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hb_student_tracker?" +
                    "user=root&password=StrongPassword1$");

            Statement stmt = conn.createStatement();
            String query = "insert into " + tableName + " set question = '" + question.getQuestion() +
                    "', questionTypeId = " + question.getQuestionTypeId();
            int numRows = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            System.out.println("numRows inserted = " + numRows);

            if (numRows == 1) {
                ResultSet genKeys = stmt.getGeneratedKeys();
                if (genKeys.next()) {
                    questionId = genKeys.getInt(1);  // ResultSet should have exactly one column, the primary key of INSERT table.
                } else {
                    throw new SQLException("Could not get the customerId");
                }
            } else {
                throw new SQLException("Could not get the customerId");
            }
        } finally {
            if (conn != null) conn.close();
        }
        return questionId;
    }
}