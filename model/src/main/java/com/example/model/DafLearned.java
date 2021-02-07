package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(primaryKeys = {"studyPlanID","masechetName","pageNumber"},foreignKeys = @ForeignKey(entity = StudyPlan.class,
        parentColumns = "id",
        childColumns = "studyPlanID",
        onDelete = ForeignKey.CASCADE))
public class DafLearned implements Parcelable{

    private int dafLearnedId;
    private int studyPlanID;
    @NonNull
    private String masechetName;
    private int pageNumber;
    private int chazara;
    private int indexInListDafs;
    @Ignore
    private boolean isLearned;
    @Ignore
    private String pageDate;

    public DafLearned(int studyPlanID, @NonNull String masechetName, int pageNumber, boolean isLearned, int chazara, int indexInListDafs) {
        this.studyPlanID = studyPlanID;
        this.masechetName = masechetName;
        this.pageNumber = pageNumber;
        this.chazara = chazara;
        this.indexInListDafs = indexInListDafs;
        this.isLearned = isLearned;
    }

    public DafLearned() {
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

    public boolean isLearned() { return isLearned; }

    public void setLearned(boolean learned) { isLearned = learned; }

    public String getPageDate() { return pageDate; }

    public void setPageDate(String pageDate) { this.pageDate = pageDate; }

    protected DafLearned(Parcel in) {
        dafLearnedId = in.readInt();
        studyPlanID = in.readInt();
        masechetName = in.readString();
        pageNumber = in.readInt();
        chazara = in.readInt();
        indexInListDafs = in.readInt();
        isLearned = in.readByte() != 0;
        pageDate = in.readString();
    }

    public static final Creator<DafLearned> CREATOR = new Creator<DafLearned>() {
        @Override
        public DafLearned createFromParcel(Parcel in) {
            return new DafLearned(in);
        }

        @Override
        public DafLearned[] newArray(int size) {
            return new DafLearned[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(dafLearnedId);
        dest.writeInt(studyPlanID);
        dest.writeString(masechetName);
        dest.writeInt(pageNumber);
        dest.writeInt(chazara);
        dest.writeInt(indexInListDafs);
        dest.writeByte((byte) (isLearned ? 1 : 0));
        dest.writeString(pageDate);
    }
}
