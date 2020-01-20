package com.bvcoe.demo;

public class recycleview {

    private int ImageSource;
    private String  text1;
    private String text2;

    public recycleview(int imageSource, String text1, String text2) {
        ImageSource = imageSource;
        this.text1 = text1;
        this.text2 = text2;
    }

    public int getImageSource() {
        return ImageSource;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }
}
