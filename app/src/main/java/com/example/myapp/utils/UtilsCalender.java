package com.example.myapp.utils;

import android.annotation.SuppressLint;
import android.icu.util.HebrewCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UtilsCalender {

    public static Calendar findDateOfStartDafHayomiEnglishDate() {
        Calendar today = Calendar.getInstance();
        ArrayList<Calendar> startDafHyomiDates = initListStartDafHyomiEnglishDate();
        if (today.after(startDafHyomiDates.get(0)) && today.before(startDafHyomiDates.get(1))) {
            return startDafHyomiDates.get(0);
        } else if (today.after(startDafHyomiDates.get(1)) && today.before(startDafHyomiDates.get(2))) {
            return startDafHyomiDates.get(1);
        } else {
            return startDafHyomiDates.get(2);
        }
    }

    private static ArrayList<Calendar> initListStartDafHyomiEnglishDate() {
        ArrayList<Calendar> listStartDafHyomiDates = new ArrayList<>();
        listStartDafHyomiDates.add(createEnglishCalender(2012, 7, 3));
        listStartDafHyomiDates.add(createEnglishCalender(2020, 0, 5));
        listStartDafHyomiDates.add(createEnglishCalender(2027, 5, 8));
//        add more machzorim!!!!!!!!!!!!!!!!!!
        return listStartDafHyomiDates;
    }

    private static Calendar createEnglishCalender(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        return c;
    }

    public static String convertDateToHebrewDate(String englishDate) {
        int[] mDate = catStringData(englishDate);
        Date d = new Date((mDate[2] - 1900), (mDate[1] - 1), mDate[0]);
        HebrewCalendar h = new HebrewCalendar(d);
        String month = fixMonthAdarAB(findHebrewMonthName(h.get(HebrewCalendar.MONTH)), h);
        return String.format(Locale.getDefault(), "%s  %s  %s", ConvertIntToPage.intToPage(h.get(HebrewCalendar.DAY_OF_MONTH)), month, ConvertIntToPage.intToPage(h.get(HebrewCalendar.YEAR)-5000));
    }

    private static String findHebrewMonthName(int month) {
        String[] nameMonth = {"תשרי", "חשוון", "כסליו", "טבת", "שבט", "אדר א", "אדר", "ניסן", "אייר", "סיון", "תמוז", "אב", "אלול"};
        return nameMonth[month];
    }

    private static String fixMonthAdarAB(String hebrewMonthName, HebrewCalendar h) {

        if (hebrewMonthName.equals("אדר")) {
            int yearLength = (h.getActualMaximum(HebrewCalendar.DAY_OF_YEAR));
            if (yearLength > 370) {
                return "אדר ב";
            } else {
                return "אדר";
            }
        }
        return hebrewMonthName;
    }


    private static int[] catStringData(String date) {
        char[] charDateInts = date.toCharArray();
        int[] mIntDate = new int[3];
        int placeInArray = 0;
        StringBuilder mDate = new StringBuilder();
        for (char charDateInt : charDateInts) {
            String mChar = String.valueOf(charDateInt);
            if (mChar.equals("0") && mDate.length() == 0) {
                continue;
            }
            if (mChar.equals("/")) {
                mIntDate[placeInArray] = Integer.parseInt(mDate.toString());
                placeInArray++;
                mDate = new StringBuilder();
            } else {
                mDate.append(charDateInt);
            }
        }
        mIntDate[placeInArray] = Integer.parseInt(mDate.toString());
        return mIntDate;
    }

    public static String dateStringFormat(Calendar c) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(c.getTime());
    }
}
