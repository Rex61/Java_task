package com.example.demo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Test{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Question> questions = new ArrayList<Question>();

    public Test (Question q){
            this.questions.add(q);
    }

    public Test() {
    }

    public void addQuestion (Question...arr){
        for ( Question q : arr) {
            this.questions.add(q);
        }
    }

    public int countingPoints(TestForm testForm){
        int points = 0;

        for( int i = 0; i < this.getQuestions().size() ; i++){

            if (Answer.compare(this.getQuestionAnswer(i) , testForm.getA()[i])){
                points += this.getQuestionPoints(i);
            }
        }
        return points;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    private void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    private Answer getQuestionAnswer(int index){
        return this.questions.get(index).getAnswer();
    }

    private int getQuestionPoints(int index){
        return this.questions.get(index).getPoints();
    }
}
