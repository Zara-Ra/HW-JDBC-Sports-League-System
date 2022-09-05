import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.League;
import ir.maktab.sports.data.team.FootballTeam;
import ir.maktab.sports.data.team.Team;
import ir.maktab.sports.data.team.VolleyballTeam;
import ir.maktab.sports.repository.util.AppConstant;
import ir.maktab.sports.service.FootballService;
import ir.maktab.sports.service.LeagueService;
import ir.maktab.sports.service.VolleyballService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = AppConstant.getScanner();
    private static League league;
    private static LeagueService leagueService;
    public static void main(String[] args) throws SQLException {
        //first we create a league service, we enter 8 or 5 teams in the league_table, we also enter these team names to the team_table
        //we enter the matches(two nested fors) in the match_table.

        //when ever a match is going to be added the feilds in match_table and the feilds in team_table is going to be updated.

        //what happens when a team is going to be deleted? all the coresponding records should be deleted? dose sql handel it itself

        firstMenu();
    }
    public static void firstMenu() throws SQLException {
        System.out.println("Welcome to Sport League Generator!");
        System.out.println("Press 1 --> Create Football League");
        System.out.println("Press 2 --> Create Volleyball League");
        System.out.println("Press 3 --> See Previous Leagues");
        System.out.println("Press 4 --> Exit");

        int menu = Integer.parseInt(scanner.nextLine());
        switch (menu){
            case 1:
                leagueService = new FootballService();
                System.out.println("Enter The Start Date For the Football League:(exp: 2022-01-31) ");
                league = new League(Date.valueOf(scanner.nextLine()));
                System.out.println("Enter 8 Team names which will play in this League:(press enter after each team name) ");
                for (int i = 0; i < 8; i++) {
                    Team team = new FootballTeam(scanner.nextLine());
                    league.addTeam(team);
                }
                league.setLeagueID(leagueService.addLeague(league));
                if(league.getLeagueID() != 0)
                    System.out.println("Football League with ID number : "+league.getLeagueID()+" Created Successfully!");
                secondMenu();
                break;
            case 2:
                leagueService = new VolleyballService();
                System.out.println("Enter The Start Date For the Volleyball League:(exp: 2022-01-31) ");
                league = new League(Date.valueOf(scanner.nextLine()));
                System.out.println("Enter 5 Team names which will play in this League:(press enter after each team name) ");
                for (int i = 0; i < 5; i++) {
                    Team team = new VolleyballTeam(scanner.nextLine());
                    league.addTeam(team);
                }
                league.setLeagueID(leagueService.addLeague(league));
                if(league.getLeagueID() != 0)
                    System.out.println("Volleyball League with ID number : "+league.getLeagueID()+" Created Successfully!");
                secondMenu();
                break;
            case 3:
                //secondMenu();
                break;
            case 4:
                break;
        }
    }
    public static void secondMenu(){
        System.out.println("League");
        System.out.println("Press 1 --> Show League Info");
        System.out.println("Press 2 --> Delete Team");
        System.out.println("Press 3 --> Add Team");
        System.out.println("Press 4 --> Add Match");
        System.out.println("Press 5 --> Show Team Info");
        System.out.println("Press 6 --> Show Ranking Table");




    }
}
