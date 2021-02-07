package com.example.model;

import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class Learning {
    @Embedded private StudyPlan mStudyPlan;
    @Relation(
            parentColumn = "id",
            entityColumn = "studyPlanID"
    )
    private List <DafLearned> dafLearned;

    @Ignore
    private ArrayList<DafLearned> allDafsToShow;


    public StudyPlan getStudyPlan() {
        return mStudyPlan;
    }

    public void setStudyPlan(StudyPlan mStudyPlan) {
        this.mStudyPlan = mStudyPlan;
    }

    public List<DafLearned> getDafLearned() {
        return dafLearned;
    }

    public void setDafLearned(List<DafLearned> dafLearned) {
        this.dafLearned = dafLearned;
    }

    public ArrayList<DafLearned> getAllDafsToShow() { return allDafsToShow; }

    public void setAllDafsToShow(ArrayList<DafLearned> allDafsToShow) { this.allDafsToShow = allDafsToShow; }

}
