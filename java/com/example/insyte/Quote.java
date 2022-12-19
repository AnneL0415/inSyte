package com.example.insyte;

public class Quote {
    private String theQuote;
    private String theSpeaker;

    public Quote(String theQuote, String theSpeaker) {
        this.theQuote = theQuote;
        this.theSpeaker = theSpeaker;
    }

    public String getTheQuote() {
        return theQuote;
    }

    public void setTheQuote(String theQuote) {
        this.theQuote = theQuote;
    }

    public String getTheSpeaker() {
        return theSpeaker;
    }

    public void setTheSpeaker(String theSpeaker) {
        this.theSpeaker = theSpeaker;
    }
}
