package com.thaile.baitap_ailatrieuphu;

/**
 * Created by Le on 8/14/2016.
 */
public class QuestionObject {
    private String question;
    private String caseA;
    private String caseB;
    private String caseC;
    private String caseD;
    private int trueCase;
    private String score;

    public QuestionObject(String question, int trueCase, String caseA, String caseB, String caseC, String caseD, String score) {
        this.question = question;
        this.trueCase = trueCase;
        this.caseD = caseD;
        this.caseC = caseC;
        this.caseB = caseB;
        this.caseA = caseA;
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public String getQuestion() {
        return question;
    }


    public int getTrueCase() {
        return trueCase;
    }

    public String getCaseD() {
        return caseD;
    }

    public String getCaseC() {
        return caseC;
    }

    public String getCaseB() {
        return caseB;
    }

    public String getCaseA() {
        return caseA;
    }
}

