package com.netcom.firebaseexoplayer;

public class ModelClass {
        String textView;
        int imageView;
        String link;

    public ModelClass(String textView, int imageView, String link) {
        this.textView = textView;
        this.imageView = imageView;
        this.link = link;
    }

    public String getTextView() {
        return textView;
    }

    public int getImageView() {
        return imageView;
    }

    public String getLink() {
        return link;
    }
}
