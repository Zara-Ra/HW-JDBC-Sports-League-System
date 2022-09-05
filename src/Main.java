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
        //System.out.println("Press 3 --> See Previous Leagues");
        System.out.println("Press 4 --> Exit");

        int menu = Integer.parseInt(scanner.nextLine());
        switch (menu) {
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
                if (league.getLeagueID() != 0) {
                    System.out.println("Football League with ID number : " + league.getLeagueID() + " Created Successfully!");
                    secondMenu();
                } else {
                    System.out.println("Unable to Create a New League");
                    firstMenu();
                }
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
                if (league.getLeagueID() != 0) {
                    System.out.println("Volleyball League with ID number : " + league.getLeagueID() + " Created Successfully!");
                    secondMenu();
                } else {
                    System.out.println("Unable to Create a New League");
                    firstMenu();
                }
                break;
            case 3:
                //show all previous leagues the user will choose the league and we set the static league then second menu
                //TODO show previous leagues
                //secondMenu();
                break;
            case 4:
                break;
        }
    }

    public static void secondMenu() throws SQLException {
        System.out.println(leagueService);
        System.out.println("Press 1 --> Show League Info");
        System.out.println("Press 2 --> Delete Team");
        System.out.println("Press 3 --> Add Team");
        System.out.println("Press 4 --> Add Match");
        System.out.println("Press 5 --> Show Team Info");
        System.out.println("Press 6 --> Show Ranking Table");
        System.out.println("Press 7 --> Exit");

        int menu = Integer.parseInt(scanner.nextLine());
        switch (menu) {
            case 1:
                System.out.println(league);
                secondMenu();
                break;
            case 2:
                System.out.println("Enter Team Name to Delete: ");
                String delTeamName = scanner.nextLine();
                Team delTeam = league.findTeam(delTeamName);
                if (delTeam != null) {
                    if (leagueService.deleteTeam(delTeam)) {
                        System.out.println("Team deleted successfully");
                        league.deleteTeam(delTeam);
                    } else
                        System.out.println("Unable to delete team");
                } else
                    System.out.println("Team not found");
                secondMenu();
                break;
            case 3:
                System.out.println("Enter Team Name to add to the League: ");
                Team newTeam = null;
                if (leagueService instanceof FootballService) {
                    if (league.getTeamList().size() < 8) {
                        newTeam = new FootballTeam(scanner.nextLine());
                    } else {
                        System.out.println("There are already 8 Teams in the League, First Delete a Team");
                        secondMenu();
                    }
                } else if (leagueService instanceof VolleyballService) {
                    if (league.getTeamList().size() < 5)
                        newTeam = new VolleyballTeam(scanner.nextLine());
                    else {
                        System.out.println("There are already 5 Teams in the League, First Delete a Team");
                        secondMenu();
                    }
                } else {
                    System.out.println("No suitable service choosen");
                    firstMenu();
                }
                newTeam.setLeagueID(league.getLeagueID());
                int teamID = leagueService.addTeam(league, newTeam);
                newTeam.setTeamID(teamID);
                league.addTeam(newTeam);

                secondMenu();
                break;
            case 4:
                Team homeTeam = null;
                Team awayTeam = null;
                int homeScore, awayScore;
                int homePoint = 0, awayPoint = 0;
                System.out.println("Enter Home Team Information for the Match: ");
                System.out.println("Name : ");
                homeTeam = league.findTeam(scanner.nextLine());
                if (homeTeam == null) {
                    System.out.println("Team not in current League");
                    secondMenu();
                    break;
                }
                System.out.println("Score : ");
                homeScore = Integer.parseInt(scanner.nextLine());

                System.out.println("Enter Away Team Information for the Match: ");
                System.out.println("Name : ");
                awayTeam = league.findTeam(scanner.nextLine());
                if (awayTeam == null) {
                    System.out.println("Team not in current League");
                    secondMenu();
                    break;
                }
                System.out.println("Score : ");
                awayScore = Integer.parseInt(scanner.nextLine());
                if (leagueService instanceof FootballService) {
                    if (homeScore > awayScore) {
                        homePoint = 3;
                        awayPoint = 0;
                    } else if (homeScore == awayScore) {
                        homePoint = 1;
                        awayPoint = 1;
                    } else {
                        homePoint = 0;
                        awayPoint = 3;
                    }
                } else if (leagueService instanceof VolleyballService) {
                    if (homeScore == 3 && (awayScore == 0 || awayScore == 1)) {
                        homePoint = 3;
                        awayPoint = 0;
                    } else if (awayScore == 3 && (homeScore == 0 || homeScore == 1)) {
                        awayPoint = 3;
                        homePoint = 0;
                    } else if (homeScore == 3 && awayScore == 2) {
                        homePoint = 2;
                        awayPoint = 1;
                    } else if (awayScore == 3 && homeScore == 2) {
                        awayPoint = 2;
                        homePoint = 1;
                    }
                }
                Match match = new Match(homeTeam.getTeamID(), awayTeam.getTeamID(), homePoint, awayPoint, league.getLeagueID(), homeScore, awayScore);
                match.setLeagueID(league.getLeagueID());
                if(leagueService instanceof VolleyballService){
                    System.out.println("Enter Total number of Won sets and Lost sets for home team:(EXP. 52:13) ");
                    String temp = scanner.nextLine();
                    String [] splited = temp.split(":");
                    int [] sets = new int[2];
                    sets[0] = Integer.parseInt(splited[0]);
                    sets[1] = Integer.parseInt(splited[1]);
                    ((VolleyballTeam) homeTeam).setSets(sets);

                    System.out.println("Enter Total number of Won sets and Lost sets for away team:(EXP. 52:13) ");
                    String temp1 = scanner.nextLine();
                    String [] splited1 = temp.split(":");
                    int [] sets1 = new int[2];
                    sets1[0] = Integer.parseInt(splited1[0]);
                    sets1[1] = Integer.parseInt(splited1[1]);
                    ((VolleyballTeam) awayTeam).setSets(sets);

                }
                leagueService.addMatch(league, match);
                secondMenu();
                break;
            case 5:
                System.out.println("Enter Team name to See Info: ");
                Team team = league.findTeam(scanner.nextLine());
                team = leagueService.TeamInfoByID(team.getTeamID());
                System.out.println(team);
                secondMenu();
                break;
            case 6:
                leagueService.rankingTable(league.getTeamList());
                for (int i = 0; i < league.getTeamList().size(); i++) {
                    System.out.println(league.getTeamList().get(i));
                }
                secondMenu();
                break;
            case 7:
                break;
        }
    }
}
