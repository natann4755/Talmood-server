package com.example.model.shas_masechtot;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Seder implements Parcelable {

    @SerializedName("name")
    private String name;

    @SerializedName("masechtot")
    private List<Masechet> masechtot;

    private boolean isOpen = false;

    public Seder(String name, List<Masechet> masechet) {
        this.name = name;
        this.masechtot = masechet;
    }


    public boolean isOpen() { return isOpen; }

    public void setOpen(boolean open) { isOpen = open; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Masechet> getMasechtot() {
        return masechtot;
    }

    public void setMasechtot(List<Masechet> masechet) {
        this.masechtot = masechet;
    }

    protected Seder(Parcel in) {
        name = in.readString();
        isOpen = in.readByte() != 0;
    }

    public static final Creator<Seder> CREATOR = new Creator<Seder>() {
        @Override
        public Seder createFromParcel(Parcel in) {
            return new Seder(in);
        }

        @Override
        public Seder[] newArray(int size) {
            return new Seder[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (isOpen ? 1 : 0));
    }
}
