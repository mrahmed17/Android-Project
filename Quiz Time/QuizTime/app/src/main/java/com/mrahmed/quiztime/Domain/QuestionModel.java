package com.mrahmed.quiztime.Domain;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionModel implements Parcelable {

    private int id;
    private String question;
    private String correctAnswer;
    private String options1;
    private String options2;
    private String options3;
    private String options4;
    private int score;
    private String picPath;
    private String clickedAnswer;

    // Constructor
    public QuestionModel(int id, String question, String correctAnswer, String options1, String options2,
                         String options3, String options4, int score, String picPath, String clickedAnswer) {
        this.id = id;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.options1 = options1;
        this.options2 = options2;
        this.options3 = options3;
        this.options4 = options4;
        this.score = score;
        this.picPath = picPath;
        this.clickedAnswer = clickedAnswer;
    }

    // Constructor for Parcelable
    protected QuestionModel(Parcel in) {
        id = in.readInt();
        question = in.readString();
        correctAnswer = in.readString();
        options1 = in.readString();
        options2 = in.readString();
        options3 = in.readString();
        options4 = in.readString();
        score = in.readInt();
        picPath = in.readString();
        clickedAnswer = in.readString();
    }

    public static final Creator<QuestionModel> CREATOR = new Creator<QuestionModel>() {
        @Override
        public QuestionModel createFromParcel(Parcel in) {
            return new QuestionModel(in);
        }

        @Override
        public QuestionModel[] newArray(int size) {
            return new QuestionModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(question);
        dest.writeString(correctAnswer);
        dest.writeString(options1);
        dest.writeString(options2);
        dest.writeString(options3);
        dest.writeString(options4);
        dest.writeInt(score);
        dest.writeString(picPath);
        dest.writeString(clickedAnswer);
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getOptions1() {
        return options1;
    }

    public void setOptions1(String options1) {
        this.options1 = options1;
    }

    public String getOptions2() {
        return options2;
    }

    public void setOptions2(String options2) {
        this.options2 = options2;
    }

    public String getOptions3() {
        return options3;
    }

    public void setOptions3(String options3) {
        this.options3 = options3;
    }

    public String getOptions4() {
        return options4;
    }

    public void setOptions4(String options4) {
        this.options4 = options4;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getClickedAnswer() {
        return clickedAnswer;
    }

    public void setClickedAnswer(String clickedAnswer) {
        this.clickedAnswer = clickedAnswer;
    }
}
