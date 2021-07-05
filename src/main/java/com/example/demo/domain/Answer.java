package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    public boolean variant1;
    public boolean variant2;
    public boolean variant3;
    public boolean variant4;

    public Answer(boolean variant1, boolean variant2, boolean variant3, boolean variant4) {
        this.variant1 = variant1;
        this.variant2 = variant2;
        this.variant3 = variant3;
        this.variant4 = variant4;
    }

    public Answer() {
        this.variant1 = false;
        this.variant2 = false;
        this.variant3 = false;
        this.variant4 = false;
    }

    static boolean compare(Answer a1 , Answer a2){
        if ((a1.variant1 == a2.variant1) && (a1.variant2 == a2.variant2)
                && (a1.variant3 == a2.variant3) && (a1.variant4 == a2.variant4)){
            return true;
        }
        return false;
    }
    public void print(){
        System.out.println("--------------");
        System.out.println(variant1);
        System.out.println(variant2);
        System.out.println(variant3);
        System.out.println(variant4);
        System.out.println("--------------");
    }

    public boolean isVariant1() {
        return variant1;
    }

    public void setVariant1(boolean variant1) {
        this.variant1 = variant1;
    }

    public boolean isVariant2() {
        return variant2;
    }

    public void setVariant2(boolean variant2) {
        this.variant2 = variant2;
    }

    public boolean isVariant3() {
        return variant3;
    }

    public void setVariant3(boolean variant3) {
        this.variant3 = variant3;
    }

    public boolean isVariant4() {
        return variant4;
    }

    public void setVariant4(boolean variant4) {
        this.variant4 = variant4;
    }


}

