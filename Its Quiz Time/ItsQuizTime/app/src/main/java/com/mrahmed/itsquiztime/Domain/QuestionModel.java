package com.mrahmed.itsquiztime.Domain;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionModel implements Parcelable {
    private int id;
    private String question;
    private String answer_1;
    private String answer_2;
    private String answer_3;
    private String answer_4;
    private String correctAnswer;
    private int score;
    private String picPath;
    private String clickedAnswer;

    // Constructor
    public QuestionModel(int id, String question, String answer_1, String answer_2, String answer_3,
                         String answer_4, String correctAnswer, int score, String picPath, String clickedAnswer) {
        this.id = id;
        this.question = question;
        this.answer_1 = answer_1;
        this.answer_2 = answer_2;
        this.answer_3 = answer_3;
        this.answer_4 = answer_4;
        this.correctAnswer = correctAnswer;
        this.score = score;
        this.picPath = picPath;
        this.clickedAnswer = clickedAnswer;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer_1() {
        return answer_1;
    }

    public String getAnswer_2() {
        return answer_2;
    }

    public String getAnswer_3() {
        return answer_3;
    }

    public String getAnswer_4() {
        return answer_4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getScore() {
        return score;
    }

    public String getPicPath() {
        return picPath;
    }

    public String getClickedAnswer() {
        return clickedAnswer;
    }

    public void setClickedAnswer(String clickedAnswer) {
        this.clickedAnswer = clickedAnswer;
    }

    // Parcelable implementation
    // (Make sure to implement the necessary methods here)

    protected QuestionModel(Parcel in) {
        id = in.readInt();
        question = in.readString();
        answer_1 = in.readString();
        answer_2 = in.readString();
        answer_3 = in.readString();
        answer_4 = in.readString();
        correctAnswer = in.readString();
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
        dest.writeString(answer_1);
        dest.writeString(answer_2);
        dest.writeString(answer_3);
        dest.writeString(answer_4);
        dest.writeString(correctAnswer);
        dest.writeInt(score);
        dest.writeString(picPath);
        dest.writeString(clickedAnswer);
    }
}
