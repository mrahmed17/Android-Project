package com.mrahmed.quiztime.Domain;

import java.util.Objects;

public class UserModel {

    private int id;
    private String name;
    private String pic;
    private int score;

    public UserModel(int id, String name, String pic, int score) {
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.score = score;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPic() {
        return pic;
    }

    public int getScore() {
        return score;
    }

    // Setters (if needed)
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id &&
                score == userModel.score &&
                Objects.equals(name, userModel.name) &&
                Objects.equals(pic, userModel.pic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pic, score);
    }
}
