package com.example.model;


import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Daf implements Parcelable  {
    @PrimaryKey
    private int id;
    private int indexTypeOfStudy;
    private String masechet;
    private int pageNumber;
    private boolean isLearning;
    private int chazara;
    private int wantChazara;
    private boolean isLearningPage1;
    private boolean isLearningPage2;
    private String typeOfStudy;
    private String pageDate;

    public Daf(String masechet, int pageNumber, String typeOfStudy, int indexTypeOfStudy) {
        this.masechet = masechet;
        this.pageNumber = pageNumber;
        this.typeOfStudy =typeOfStudy;
        this.indexTypeOfStudy = indexTypeOfStudy;
    }

    public String getTypeOfStudy() {
        return typeOfStudy;
    }

    public void setTypeOfStudy(String typeOfStudy) {
        this.typeOfStudy = typeOfStudy;
    }

    public int getIndexTypeOfStudy() {
        return indexTypeOfStudy;
    }

    public void setIndexTypeOfStudy(int indexTypeOfStudy) { this.indexTypeOfStudy = indexTypeOfStudy; }

    public String getPageDate() {
        return pageDate;
    }

    public void setPageDate(String pageDate) {
        this.pageDate = pageDate;
    }

    public int getWantChazara() { return wantChazara; }

    public void setWantChazara(int wantChazara) {
        this.wantChazara = wantChazara;
    }

    public String getMasechet() {
        return masechet;
    }

    public void setMasechet(String masechet) {
        this.masechet = masechet;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isLearning() {
        return isLearning;
    }

    public void setLearning(boolean learning) {
        isLearning = learning;
    }

    public int getChazara() {
        return chazara;
    }

    public void setChazara(int chazara) {
        this.chazara = chazara;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLearningPage1() {
        return isLearningPage1;
    }

    public void setLearningPage1(boolean learningPage1) {
        isLearningPage1 = learningPage1;
    }

    public boolean isLearningPage2() {
        return isLearningPage2;
    }

    public void setLearningPage2(boolean learningPage2) {
        isLearningPage2 = learningPage2;
    }


    protected Daf(Parcel in) {
        id = in.readInt();
        indexTypeOfStudy = in.readInt();
        masechet = in.readString();
        pageNumber = in.readInt();
        isLearning = in.readByte() != 0;
        chazara = in.readInt();
        wantChazara = in.readInt();
        isLearningPage1 = in.readByte() != 0;
        isLearningPage2 = in.readByte() != 0;
        typeOfStudy = in.readString();
        pageDate = in.readString();
    }

    public static final Creator<Daf> CREATOR = new Creator<Daf>() {
        @Override
        public Daf createFromParcel(Parcel in) {
            return new Daf(in);
        }

        @Override
        public Daf[] newArray(int size) {
            return new Daf[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(indexTypeOfStudy);
        dest.writeString(masechet);
        dest.writeInt(pageNumber);
        dest.writeByte((byte) (isLearning ? 1 : 0));
        dest.writeInt(chazara);
        dest.writeInt(wantChazara);
        dest.writeByte((byte) (isLearningPage1 ? 1 : 0));
        dest.writeByte((byte) (isLearningPage2 ? 1 : 0));
        dest.writeString(typeOfStudy);
        dest.writeString(pageDate);
    }
}
