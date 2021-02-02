package com.example.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class StudyPlan {
    @PrimaryKey
    @NonNull
    private int id;
    private String typeOfStudy;
    private int wantChazara;

    public StudyPlan(int id, String typeOfStudy, int wantChazara) {
        this.id = id;
        this.typeOfStudy = typeOfStudy;
        this.wantChazara = wantChazara;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeOfStudy(String typeOfStudy) {
        this.typeOfStudy = typeOfStudy;
    }

    public void setWantChazara(int wantChazara) {
        this.wantChazara = wantChazara;
    }

    public String getTypeOfStudy() { return typeOfStudy; }

    public int getWantChazara() { return wantChazara; }

    public int getId() { return id; }

}

