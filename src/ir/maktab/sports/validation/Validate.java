package ir.maktab.sports.util.validation;

public class Validate {
    public static boolean isSetScoreValid(int a, int b) {
        if (a == 3 && (b == 0 || b == 1))
            return true;
        if (b == 3 && (a == 0 || a == 1))
            return true;
        if (a == 3 && b == 2)
            return true;
        if (b == 3 && a == 2)
            return true;
        return false;
    }

    public static boolean isPoanValid(int a, int b, int seta, int setb) {
        if (a > b && seta > setb)
            return true;
        if (a < b && seta < setb)
            return true;
        return false;
    }

    public static boolean isNameValid(String str) {
        return ((!str.equals(""))
                && (str != null)
                && (str.matches("^[\\w\\.\s]{5,}$")));
    }
}

