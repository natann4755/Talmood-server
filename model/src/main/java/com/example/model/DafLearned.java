package com.example.model;

import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = @ForeignKey(entity = StudyPlan.class,
        parentColumns = "id",
        childColumns = "studyPlanID",
        onDelete = ForeignKey.CASCADE))
public class DafLearned {

    @PrimaryKey
    private int dafLearnedId;
    private int studyPlanID;
    private String masechetName;
    private int pageNumber;
    private int chazara;
    private int indexInListDafs;

    public DafLearned(int studyPlanID, String masechetName, int pageNumber, int chazara, int indexInListDafs) {
        this.studyPlanID = studyPlanID;
        this.masechetName = masechetName;
        this.pageNumber = pageNumber;
        this.chazara = chazara;
        this.indexInListDafs = indexInListDafs;
    }

    public int getDafLearnedId() {
        return dafLearnedId;
    }

    public void setDafLearnedId(int dafLearnedId) {
        this.dafLearnedId = dafLearnedId;
    }

    public int getStudyPlanID() { return studyPlanID; }

    public void setStudyPlanID(int studyPlanID) {
        this.studyPlanID = studyPlanID;
    }

    public String getMasechetName() { return masechetName; }

    public void setMasechetName(String masechetName) {
        this.masechetName = masechetName;
    }

    public int getPageNumber() { return pageNumber; }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getChazara() { return chazara; }

    public void setChazara(int chazara) {
        this.chazara = chazara;
    }

    public int getIndexInListDafs() { return indexInListDafs; }

    public void setIndexInListDafs(int indexInListDafs) {
        this.indexInListDafs = indexInListDafs;
    }
}
