package com.example.androidlabs;

public class Message {
    private int direction;
    private String text;

    public Message(int direction, String text){
        this.direction = direction;
        this.text = text;
    }

    public int getDirection(){
        return this.direction;
    }

    public String getText(){
        return this.text;
    }
}
