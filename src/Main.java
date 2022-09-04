import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.League;
import ir.maktab.sports.data.team.FootballTeam;
import ir.maktab.sports.data.team.Team;
import ir.maktab.sports.repository.util.AppConstant;
import ir.maktab.sports.service.FootballService;
import ir.maktab.sports.service.LeagueService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = AppConstant.getScanner();

    public static void main(String[] args) throws SQLException {
        // first of all teams should be added to the corespondig tables(because of foriegn key constrants
        //then we can create a new league and add the teams
        //then the matches can be added

       LeagueService footballService = new FootballService();
        /*FootballTeam perspolise = new FootballTeam("Perspolise");
        perspolise.setTeamID(220);
        FootballTeam esteghlal = new FootballTeam("Esteghlal");
        esteghlal.setTeamID(213);
        FootballTeam three = new FootballTeam("three");
        three.setTeamID(214);
        FootballTeam four = new FootballTeam("Four");
        four.setTeamID(215);
        FootballTeam fIve = new FootballTeam("FIve");
        fIve.setTeamID(216);
        FootballTeam six = new FootballTeam("six");
        six.setTeamID(217);
        FootballTeam seven = new FootballTeam("seven");
        seven.setTeamID(218);
        FootballTeam eight = new FootballTeam("eight");
        eight.setTeamID(219);
        //footballService.addTeam(new FootballTeam("Perspolise"));

        League KhalijeFarsLeague = new League(Date.valueOf("2020-01-01"));
        List<Team> teamList = new ArrayList<>();
        teamList.add(perspolise);
        teamList.add(esteghlal);
        teamList.add(three);
        teamList.add(four);
        teamList.add(fIve);
        teamList.add(six);
        teamList.add(seven);
        teamList.add(eight);

        KhalijeFarsLeague.setTeamList(teamList);
        System.out.println(footballService.addLeague(KhalijeFarsLeague));
*/
        System.out.println(footballService.TeamInfoByID(217));

    }
}
