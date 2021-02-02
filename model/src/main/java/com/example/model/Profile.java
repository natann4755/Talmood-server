package com.example.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Profile implements Parcelable  {
   private boolean hebrewCalendar;
   private boolean warning;
   private String alertFrequency;
   private String language;


   public Profile(boolean hebrewCalendar) {
      this.hebrewCalendar = hebrewCalendar;
   }


   public boolean isHebrewCalendar() {
      return hebrewCalendar;
   }

   public void setHebrewCalendar(boolean hebrewCalendar) {
      this.hebrewCalendar = hebrewCalendar;
   }

   public boolean isWarning() {
      return warning;
   }

   public void setWarning(boolean warning) {
      this.warning = warning;
   }

   public String getAlertFrequency() {
      return alertFrequency;
   }

   public void setAlertFrequency(String alertFrequency) {
      this.alertFrequency = alertFrequency;
   }

   public String getLanguage() {
      return language;
   }

   public void setLanguage(String language) {
      this.language = language;
   }



   protected Profile(Parcel in) {
      hebrewCalendar = in.readByte() != 0;
      warning = in.readByte() != 0;
      alertFrequency = in.readString();
      language = in.readString();
   }

   public static final Creator<Profile> CREATOR = new Creator<Profile>() {
      @Override
      public Profile createFromParcel(Parcel in) {
         return new Profile(in);
      }

      @Override
      public Profile[] newArray(int size) {
         return new Profile[size];
      }
   };

   @Override
   public int describeContents() {
      return 0;
   }

   @Override
   public void writeToParcel(Parcel dest, int flags) {
      dest.writeByte((byte) (hebrewCalendar ? 1 : 0));
      dest.writeByte((byte) (warning ? 1 : 0));
      dest.writeString(alertFrequency);
      dest.writeString(language);
   }
}
