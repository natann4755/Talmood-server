package com.example.myapp.utils;

public class ConvertNamesToEnglish {

    public static String convertMasechetNamesToEnglish(String masechet){
        switch(masechet) {
            case "ברכות":
               return "Berakhot";
            case "שבת":
                return "Shabbat";
            case "עירובין":
                return "Eruvin";
            case "פסחים":
                return "Pesachim";
            case "שקלים":
                return "Shekalim";
            case "ראש השנה":
                return "Rosh_Hashanah";
            case "יומא":
                return "Yoma";
            case "סוכה":
                return "Sukkah";
            case "ביצה":
                return "Beitzah";
            case "תענית":
                return "Taanit";
            case "מגילה":
                return "Megillah";
            case "מועד קטן":
                return "Moed_Katan";
            case "חגיגה":
                return "Chagigah";
            case "יבמות":
                return "Yevamot";
            case "כתובות":
                return "Ketubot";
            case "נדרים":
                return "Nedarim";
            case "נזיר":
                return "Nazir";
            case "סוטה":
                return "Sotah";
            case "גיטין":
                return "Gittin";
            case "קידושין":
                return "Kiddushin";
            case "בבא קמא":
                return "Bava_Kamma";
            case "בבא מציעא":
                return "Bava_Metzia";
            case "בבא בתרא":
                return "Bava_Batra";
            case "סנהדרין":
                return "Sanhedrin";
            case "מכות":
                return "Makkot";
            case "שבועות":
                return "Shevuot";
            case "עבודה זרה":
                return "Avodah_Zarah";
            case "הוריות":
                return "Horayot";
            case "זבחים":
                return "Zevachim";
            case "מנחות":
                return "Menachot";
            case "חולין":
                return "Chullin";
            case "בכורות":
                return "Bekhorot";
            case "ערכין":
                return "Arakhin";
            case "תמורה":
                return "Temurah";
            case "כריתות":
                return "Keritot";
            case "מעילה":
                return "Meilah";
            case "קינים":
                return "Kinnim";
            case "תמיד":
                return "Tamid";
            case "מידות":
                return "Middot";
            case "נדה":
                return "Niddah";

            default:
                return "";
        }
    }

}
