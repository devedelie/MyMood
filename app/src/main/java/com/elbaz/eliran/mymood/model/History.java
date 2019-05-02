package com.elbaz.eliran.mymood.model;


public class History {

    private String dayText, commentData;
    int mCommentBtnImage;

    public History(String dayText, String commentData, int commentBtnImage) {
        this.dayText = dayText;
        this.commentData = commentData;
        this.mCommentBtnImage = commentBtnImage;
    }

    public History() {
    }

    public int getCommentBtnImage() {
        return mCommentBtnImage;
    }

    public String getDayText() {
        return dayText;
    }

    public void setDayText(String dayText) {
        this.dayText = dayText;
    }

    public String getCommentData() {
        return commentData;
    }

    public void setCommentData(String commentData) {
        this.commentData = commentData;
    }

    @Override
    public String toString() {
        return "History{" +
                "dayText='" + dayText + '\'' +
                ", commentData='" + commentData + '\'' +
                '}';
    }
}
