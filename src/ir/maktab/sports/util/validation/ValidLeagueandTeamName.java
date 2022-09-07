package ir.maktab.sports.util.validation;

public class ValidLeagueandTeamName {
    public static boolean isStringOnlyAlphabet(String str) {
        return ((!str.equals(""))
                && (str != null)
                && (str.matches("^[a-zA-Z0-9]*$")));
    }
}
