package com.example.myapp.utils;

public class ConvertIntToPage {

    public static String intToPage(int i) {
        StringBuilder pageNum = new StringBuilder();
        int num = 0;
        int module = 10000;

        while (module>1) {
            module /= 10;
            if (num ==15){
                pageNum.append("טו");
                break;
            }
            if (num ==16){
                pageNum.append("טז");
                break;
            }
            if (num > 400){
                pageNum.append(findPageFromInt(400));
                i -= 400;
                if (i > 400){
                    pageNum.append(findPageFromInt(400));
                    i -= 400;
                }
            }
            num = i % module;
            if (num != i) {
                i -= num;
                pageNum.append(findPageFromInt(i));
                i=num;
            }
        }
        return pageNum.toString();
    }

    private static String findPageFromInt (int intPage){
        char c;
        if (intPage<=10){
            c=(char)(intPage+1487);
        }else if (intPage <100){
            int num = intPage /10;
            int intUmiCode = 1497+ fixFinalLetters(intPage);
            c=(char)(num+intUmiCode);
        }else {
            int num = intPage /100;
            c=(char)(num+1510);
        }
        return String.valueOf(c);
    }

    private static int fixFinalLetters(int intPage) {
        int fixInt = 0;
        if (intPage>=40){
            fixInt ++;
        }if (intPage>=50){
            fixInt ++;
        }if (intPage>=80){
            fixInt ++;
        }if (intPage>=90){
            fixInt ++;
        }
        return fixInt;
    }

    public static int fixKinimTamidMidot(int k, String masechetName) {
        switch(masechetName) {
            case "קינים":
                return (k + 21);
            case "תמיד":
                return (k + 24);
            case "מידות":
               return (k + 32);
            default:
               return k;
        }
    }
}
