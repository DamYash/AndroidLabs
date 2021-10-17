package com.example.androidlabs;
public class Message {
    private Long id;
    private boolean isSend;
    private String text;
    public Message(Long id, boolean isSend, String text){
        this.id = id;
        this.isSend = isSend;
        this.text = text;
    }


    public Long getId() {
        return this.id;
    }
    public boolean isSend(){
        return this.isSend;
    }
    public String getText(){
        return this.text;
    }
}
