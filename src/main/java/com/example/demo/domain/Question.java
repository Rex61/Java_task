package com.example.demo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question{
    private String text;
    private int Wid;
    // question number in the test
    @OneToOne(cascade = CascadeType.ALL)
    private Answer answer;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> variants;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private int points;

    public Question (String text , Answer answer , ArrayList<String> variants , int wid , int points){
        this.answer = answer;
        this.text = text;
        this.variants = variants;
        this.points = points;
        this.Wid = wid;
    }

    public Question() {

    }

    public void print() {
        this.answer.print();
        System.out.println(this.Wid);
        System.out.println(this.id);
    }

    public int getWid() {
        return Wid;
    }

    public void setWid(int wid) {
        Wid = wid;
    }

    public List<String> getVariants() {
        return this.variants;
    }

    public void setVariants(ArrayList<String> variants) {
        this.variants = variants;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public String getText(){
        return this.text;
    }

}

