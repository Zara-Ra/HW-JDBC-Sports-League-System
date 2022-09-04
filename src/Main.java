import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.leagues.League;
import ir.maktab.sports.repository.AppConstant;
import ir.maktab.sports.service.FootballService;
import ir.maktab.sports.service.LeagueService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = AppConstant.getScanner();

    public static void main(String[] args) throws SQLException {
        League newFLeague = new League();
        LeagueService FLS = new FootballService();
        newFLeague.setLeaugeService(FLS);
        Match firstMatch = new Match(111, 222, 3, 0);

        if (newFLeague.addMatch(firstMatch))
            System.out.println(firstMatch.getMatchID());
    }
}
