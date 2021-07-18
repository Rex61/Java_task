package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserID {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long db_id;
    private String id;
    private String fullName;
    private int points;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public UserID(String id, String fullName, int points) {
        this.id = id;
        this.fullName = fullName;
        this.points = points;
    }

    public UserID() {
    }
}
