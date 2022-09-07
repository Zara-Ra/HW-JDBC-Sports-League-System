package ir.maktab.sports.view;

import ir.maktab.sports.data.League;
import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.team.FootballTeam;
import ir.maktab.sports.data.team.Team;
import ir.maktab.sports.data.team.VolleyballTeam;
import ir.maktab.sports.service.FootballService;
import ir.maktab.sports.service.LeagueService;
import ir.maktab.sports.service.VolleyballService;
import ir.maktab.sports.util.AppConstant;
import ir.maktab.sports.util.validation.ValidLeagueandTeamName;
import ir.maktab.sports.util.validation.ValidVolleyballScore;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    enum FirstMenuOption {
        FOOTBALLLEAGUE, VOLLEYBALLLEAGUE, PREVIOUSLEAGUE, EXIT
    }

    enum SecondMenuOption {
        LEAGUEINFO, DELETETEAM, ADDTEAM, ADDMATCH, TEAMINFO, RANKING, PREVMENU, EXIT
    }

    private static final Scanner scanner = AppConstant.getScanner();
    private static League league;
    private static LeagueService leagueService;

    public static void main(String[] args) throws SQLException {
        System.out.println("---------------------------------------");
        System.out.println("Welcome to Sport League Generator!");

        firstMenu();
    }

    public static void firstMenu() throws SQLException {
        System.out.println("---------------------------------------");
        System.out.println("Press 1 --> Create Football League");
        System.out.println("Press 2 --> Create Volleyball League");
        System.out.println("Press 3 --> See Previous Leagues");
        System.out.println("Press 4 --> Exit");
        System.out.println("---------------------------------------");

        int value = Integer.parseInt(scanner.nextLine()) - 1;
        FirstMenuOption menu = FirstMenuOption.values()[value];
        switch (menu) {
            case FOOTBALLLEAGUE:
                leagueService = new FootballService();
                createNewLeague(8);
                firstMenu();
                break;
            case VOLLEYBALLLEAGUE:
                leagueService = new VolleyballService();
                createNewLeague(5);
                firstMenu();
                break;
            case PREVIOUSLEAGUE:
                System.out.println("---------------------------------------");
                System.out.println("Press 1 --> Football Leagues");
                System.out.println("Press 2 --> Volleyball Leagues");
                System.out.println("---------------------------------------");
                int num = Integer.parseInt(scanner.nextLine());
                if (num == 1)
                    leagueService = new FootballService();
                else if (num == 2)
                    leagueService = new VolleyballService();
                else {
                    System.out.println("Wrong number entered");
                    firstMenu();
                    break;
                }
                if (showPreviousLeagues()) {
                    //TODO System.out.println("Enter a League Name to Edit and see more Info: ");
                    secondMenu();
                } else
                    firstMenu();
                break;
            case EXIT:
                break;
            default:
                System.out.println("---------------------------------------");
                System.out.println("Press a valid number...");
                System.out.println("---------------------------------------");
                firstMenu();
                break;
        }
    }

    public static void secondMenu() throws SQLException {
        System.out.println("---------------------------------------");
        System.out.println(leagueService);
        System.out.println("---------------------------------------");
        System.out.println("Press 1 --> Show League Info");
        System.out.println("Press 2 --> Delete Team");
        System.out.println("Press 3 --> Add Team");
        System.out.println("Press 4 --> Add Match");
        System.out.println("Press 5 --> Show Team Info");
        System.out.println("Press 6 --> Show Ranking Table");
        System.out.println("Press 7 __> Previous Menu");
        System.out.println("Press 8 --> Exit");
        System.out.println("---------------------------------------");

        int value = Integer.parseInt(scanner.nextLine()) - 1;
        SecondMenuOption menu = SecondMenuOption.values()[value];
        switch (menu) {
            case LEAGUEINFO -> {
                System.out.println("---------------------------------------");
                System.out.println(league);
                System.out.println("---------------------------------------");
                secondMenu();
            }
            case DELETETEAM -> {
                deletTeam();
                secondMenu();
            }
            case ADDTEAM -> {
                createTeam();
                secondMenu();
            }
            case ADDMATCH -> {
                addMatch();
                secondMenu();
            }
            case TEAMINFO -> {
                teamInfo();
                secondMenu();
            }
            case RANKING -> {
                rankingTable();
                secondMenu();
            }
            case PREVMENU -> firstMenu();
            case EXIT -> exit(0);
            default -> {
                System.out.println("---------------------------------------");
                System.out.println("Press a Valid Number...");
                System.out.println("---------------------------------------");
                secondMenu();
            }
        }
    }

    private static void teamInfo() throws SQLException {
        System.out.println("---------------------------------------");
        System.out.println("Enter Team Name to See Info: ");
        System.out.println("---------------------------------------");
        Team team = league.findTeam(scanner.nextLine());
        if (team != null) {
            team = leagueService.teamInfo(team.getTeamID());
            System.out.println(team);
        } else
            System.out.println("Team Not Found");
    }

    private static void rankingTable() {
        leagueService.rankingTable(league.getTeamList());
        System.out.println("---------------------------------------");
        for (int i = 0; i < league.getTeamList().size(); i++) {
            System.out.println(league.getTeamList().get(i));
        }
        System.out.println("---------------------------------------");
    }

    private static boolean showPreviousLeagues() throws SQLException {
        String[] prevLeaguesName = leagueService.previousLeagues();
        if (prevLeaguesName.length == 0) {
            System.out.println("No Leagues Available");
            return false;
        }
        System.out.println("---------------------------------------");
        for (String s : prevLeaguesName) {
            System.out.println(s);
        }
        System.out.println("---------------------------------------");
        return true;
    }

    private static void createNewLeague(int numOfTeams) throws SQLException {
        System.out.println("---------------------------------------");
        System.out.println("Enter The Leauge Name: ");
        String name = scanner.nextLine();
        if (!ValidLeagueandTeamName.isStringOnlyAlphabet(name)) {
            System.out.println("Enter a Valid Name,only Alphanumerics are Accepted");
            return;
        }
        System.out.println("Enter The Start Date For the Football League:(exp: 2022-01-31) ");
        league = new League(Date.valueOf(scanner.nextLine()), name);
        System.out.println("Enter " + numOfTeams + " Team names which will play in this League:(press enter after each team name) ");
        Team team;
        if (leagueService instanceof FootballService) {
            for (int i = 0; i < numOfTeams; i++) {
                team = new FootballTeam(scanner.nextLine());
                league.addTeam(team);
            }
        } else if (leagueService instanceof VolleyballService) {
            for (int i = 0; i < numOfTeams; i++) {
                team = new VolleyballTeam(scanner.nextLine());
                league.addTeam(team);
            }
        }
        league.setLeagueID(leagueService.addLeague(league));
        if (league.getLeagueID() != 0) {
            System.out.println("League  " + league.getLeagueName() + " Created Successfully!");
            System.out.println("---------------------------------------");
            secondMenu();
        } else {
            System.out.println("Unable to Create a New League");
            firstMenu();
        }
    }

    private static void addMatch() throws SQLException {
        Team homeTeam, awayTeam;
        int homeScore, awayScore;
        int homePoint = 0, awayPoint = 0;
        System.out.println("---------------------------------------");
        System.out.println("Enter Home Team Name: ");
        homeTeam = league.findTeam(scanner.nextLine());
        if (homeTeam == null) {
            System.out.println("Team not in current League");
            return;
        }
        System.out.println("Enter Away Team Name: ");
        awayTeam = league.findTeam(scanner.nextLine());
        if (awayTeam == null) {
            System.out.println("Team Not in Current League");
            return;
        }
        System.out.println("Enter the Score of the Match(EXP. 3:1) : ");

        String temp = scanner.nextLine();
        String[] splited = temp.split(":");
        homeScore = Integer.parseInt(splited[0]);
        awayScore = Integer.parseInt(splited[1]);

        if (leagueService instanceof FootballService) {
            if (homeScore > awayScore) {
                homePoint = 3;
            } else if (homeScore == awayScore) {
                homePoint = 1;
                awayPoint = 1;
            } else {
                awayPoint = 3;
            }
        } else if (leagueService instanceof VolleyballService) {
            if (!ValidVolleyballScore.isSetScoreValid(homeScore, awayScore)) {
                System.out.println("Score Not Valid for a Volleyball Match");
                return;
            }
            if (homeScore == 3 && (awayScore == 0 || awayScore == 1)) {
                homePoint = 3;
            } else if (awayScore == 3 && (homeScore == 0 || homeScore == 1)) {
                awayPoint = 3;
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
        if (leagueService instanceof VolleyballService) {
            System.out.println("Enter Total number of Won and Lost Poans :(EXP. 52:13) ");
            String temp1 = scanner.nextLine();
            String[] splited1 = temp1.split(":");
            int[] sets = new int[2];
            sets[0] = Integer.parseInt(splited1[0]);
            sets[1] = Integer.parseInt(splited1[1]);
            if (!ValidVolleyballScore.isPoanValid(homeScore, awayScore, sets[0], sets[1])) {
                System.out.println("The Poans Entered are NOT consistent with the Score Entered Above");
                return;
            }
            ((VolleyballTeam) homeTeam).setPoans(sets[0]);
            ((VolleyballTeam) awayTeam).setPoans(sets[1]);

        }
        if (leagueService.addMatch(league, match))
            System.out.println("New Match added Successfully");
        else
            System.out.println("Match NOT added try again");
        System.out.println("---------------------------------------");
    }

    private static void deletTeam() throws SQLException {
        System.out.println("---------------------------------------");
        System.out.println("Enter Team Name to Delete: ");
        String delTeamName = scanner.nextLine();
        Team delTeam = league.findTeam(delTeamName);
        if (delTeam != null) {
            if (leagueService.deleteTeam(delTeam)) {
                System.out.println("Team Deleted Successfully");
                league.deleteTeam(delTeam);
            } else
                System.out.println("Unable to Delete Team");
        } else
            System.out.println("Team NOT Found");
        System.out.println("---------------------------------------");
    }

    private static void createTeam() throws SQLException {
        Team newTeam = null;
        if (leagueService instanceof FootballService) {
            newTeam = addTeam(8);
        } else if (leagueService instanceof VolleyballService) {
            newTeam = addTeam(5);
        } else {
            System.out.println("No suitable service chosen");
        }
        if (newTeam != null) {
            newTeam.setLeagueID(league.getLeagueID());
            int teamID = leagueService.addTeam(league, newTeam);
            newTeam.setTeamID(teamID);
            league.addTeam(newTeam);
            System.out.println("Team " + newTeam.getTeamName() + " added Successfully!");
            System.out.println("---------------------------------------");
        }
    }

    private static Team addTeam(int numOfTeams) {
        System.out.println("---------------------------------------");
        Team newTeam = null;
        if (league.getTeamList().size() < numOfTeams) {
            System.out.println("Enter Team Name to Add to the League: ");
            String teamName = scanner.nextLine();
            if (ValidLeagueandTeamName.isStringOnlyAlphabet(teamName)) {
                if (numOfTeams == 8)
                    newTeam = new FootballTeam(teamName);
                else if (numOfTeams == 5) {
                    newTeam = new VolleyballTeam(teamName);
                }
            } else {
                System.out.println("Enter a Valid Name,only Alphanumerics are Accepted ");
            }
        } else {
            System.out.println("There are already " + numOfTeams + " Teams in the League, First Delete a Team");
        }
        System.out.println("---------------------------------------");
        return newTeam;
    }
}
