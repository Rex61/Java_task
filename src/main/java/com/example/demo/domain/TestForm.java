package com.example.demo.domain;

public class TestForm {
    private Answer[] a = new Answer[100];
    {
        for (int i = 0 ; i < 100; i++){
            a[i] = new Answer();
        }
    }
    public Answer[] getA() {
        return a;
    }

    public void setA(Answer[] a) {
        this.a = a;
    }
}
