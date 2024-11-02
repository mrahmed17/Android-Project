package com.example.QuizApp;

public class Question {
    private int ID;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int subject_id;
    private String answer_cr;

    private String userSelectedAnswer;

    public String getUserSelectedAnswer() {
        return userSelectedAnswer;
    }

    public void setUserSelectedAnswer(String userSelectedAnswer) {
        this.userSelectedAnswer = userSelectedAnswer;
    }

    public Question(int ID, String question, String option1, String option2, String option3, String option4, int subject_id, String answer_cr,String userSelectedAnswer) {
        this.ID = ID;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.subject_id = subject_id;
        this.answer_cr = answer_cr;
        this.userSelectedAnswer = userSelectedAnswer;
    }

    public Question( int ID,String question, String option1, String option2, String option3, String option4, int subject_id, String answer_cr) {
        this.ID = ID;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.subject_id = subject_id;
        this.answer_cr = answer_cr;
    }

    public Question( String question, String option1, String option2, String option3, String option4, int subject_id, String answer_cr) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.subject_id = subject_id;
        this.answer_cr = answer_cr;
    }
    public Question()
    {
        ID=0;
        question="";
        option1="";
        option2="";
        option3="";
        option4="";
        subject_id = 1;
        answer_cr="";
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getAnswer_cr() {
        return answer_cr;
    }

    public void setAnswer_cr(String answer_cr) {
        this.answer_cr = answer_cr;
    }
}
