package com.example.seyfettin.quizapplication;

public class Words {

    private int id;
    private String word;
    private String meaning;
    private String optA;
    private String optB;
    private String optC;
    private String answer;

    public Words(){
        id=0;
        word = "";
        meaning = "";
        optA = "";
        optB = "";
        optC = "";
        answer = "";
    }

    public Words(String word, String meaning){
        this.word = word;
        this.meaning = meaning;

    }
    public Words(String word, String optA, String optB, String optC, String answer){
        this.word = word;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() { return answer; }

    public String getOptC() {
        return optC;
    }

    public String getOptB() {
        return optB;
    }

    public String getOptA() {
        return optA;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() { return meaning; }

    public void setId(int id) {
        this.id = id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

    public void setAnswer(String answer) { this.answer = answer; }

}
