package net.hailm.sqlitedemo.model;

/**
 * Created by hai_l on 23/11/2017.
 */

public class Question {
    private int id;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private int trueCase;

    public Question() {
    }

    public Question(int id, String question, String answerA, String answerB, String answerC, String answerD, int trueCase) {
        this.id = id;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.trueCase = trueCase;
    }

    public Question(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String getQuestion() {
        return question;
    }

    public Question setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getAnswerA() {
        return answerA;
    }

    public Question setAnswerA(String answerA) {
        this.answerA = answerA;
        return this;
    }

    public String getAnswerB() {
        return answerB;
    }

    public Question setAnswerB(String answerB) {
        this.answerB = answerB;
        return this;
    }

    public String getAnswerC() {
        return answerC;
    }

    public Question setAnswerC(String answerC) {
        this.answerC = answerC;
        return this;
    }

    public String getAnswerD() {
        return answerD;
    }

    public Question setAnswerD(String answerD) {
        this.answerD = answerD;
        return this;
    }

    public int getTrueCase() {
        return trueCase;
    }

    public Question setTrueCase(int trueCase) {
        this.trueCase = trueCase;
        return this;
    }
}
