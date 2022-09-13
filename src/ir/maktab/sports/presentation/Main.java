package ir.maktab.sports.presentation;

import ir.maktab.sports.dao.League;
import ir.maktab.sports.dao.Match;
import ir.maktab.sports.dao.enums.Sports;
import ir.maktab.sports.dao.team.FootballTeam;
import ir.maktab.sports.dao.team.Team;
import ir.maktab.sports.dao.team.VolleyballTeam;
import ir.maktab.sports.service.FootballService;
import ir.maktab.sports.service.LeagueService;
import ir.maktab.sports.service.VolleyballService;
import ir.maktab.sports.util.AppConstant;
import ir.maktab.sports.util.validation.Validate;
import ir.maktab.sports.presentation.enums.FirstMenuOption;
import ir.maktab.sports.presentation.enums.SecondMenuOption;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    private static final Scanner scanner = AppConstant.getScanner();//ToDo when to close connection db
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
                createNewLeague(Sports.FOOTBALL);
                firstMenu();
                break;
            case VOLLEYBALLLEAGUE:
                leagueService = new VolleyballService();
                createNewLeague(Sports.VOLLEYBALL);
                firstMenu();
                break;
            case PREVIOUSLEAGUE:
                previousLeagues();
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
        System.out.println(league.getLeagueName());
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

    private static void previousLeagues() throws SQLException {
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
            return;
        }
        if (hasPreviousLeagues()) {
            System.out.println("Press 1 --> Edit Previous League");
            System.out.println("Press 2 --> Back");
            int number = Integer.parseInt(scanner.nextLine());
            switch (number) {
                case 1:
                    System.out.println("Enter a League Name to Edit Info: ");
                    String prevLeagueName = scanner.nextLine();
                    if (Validate.isNameValid(prevLeagueName)) {
                        League local = leagueService.findLeagueByName(prevLeagueName);
                        if (local != null) {
                            league = local;
                            league.setTeamList(leagueService.findTeamsByLeagueID(league.getLeagueID()));
                            secondMenu();
                        } else {
                            System.out.println("League Not Found");
                            firstMenu();
                        }
                    } else {
                       printValidationErr();
                        firstMenu();
                    }
                    break;
                case 2:
                    firstMenu();
                    break;
            }
        } else
            firstMenu();
    }

    private static boolean hasPreviousLeagues() throws SQLException {
        List<League> prevLeagues = leagueService.previousLeagues();
        if (prevLeagues.size() == 0) {
            System.out.println("No Leagues Available");
            return false;
        }
        System.out.println("---------------------------------------");
        for (int i = 0; i < prevLeagues.size(); i++) {
            System.out.println(prevLeagues.get(i).getLeagueName() + "   " + prevLeagues.get(i).getStartDate());
        }
        System.out.println("---------------------------------------");
        return true;
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

    private static void createNewLeague(Sports sportType) throws SQLException {
        System.out.println("---------------------------------------");
        System.out.println("Enter The Leauge Name: ");
        String name = scanner.nextLine();
        if (!Validate.isNameValid(name)) {
            printValidationErr();
            return;
        }
        System.out.println("Enter The Start Date For the League:(exp: 2022-01-31) ");
        league = new League(Date.valueOf(scanner.nextLine()), name, sportType);
        System.out.println("How Many Teams are Playing in the League : ");
        int numOfTeams = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter " + numOfTeams + " Team names which will play in this League:(press enter after each team name) ");
        Team team;
        if (leagueService instanceof FootballService) {
            for (int i = 0; i < numOfTeams; i++) {
                String teamName = scanner.nextLine();
                if(!Validate.isNameValid(teamName)){
                    printValidationErr();
                    continue;
                }
                team = new FootballTeam(teamName);
                league.addTeam(team);
            }
        } else if (leagueService instanceof VolleyballService) {
            for (int i = 0; i < numOfTeams; i++) {
                String teamName = scanner.nextLine();
                if(!Validate.isNameValid(teamName)){
                    printValidationErr();
                    continue;
                }
                team = new VolleyballTeam(teamName);
                league.addTeam(team);
            }
        }
        league.setLeagueID(leagueService.addLeague(league));
        if (league.getLeagueID() != 0) {
            System.out.println("League  " + league.getLeagueName() + " Created Successfully with "+league.getTeamList().size()+" Teams!");
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

        if (leagueService instanceof VolleyballService) {
            if (!Validate.isSetScoreValid(homeScore, awayScore)) {
                System.out.println("Score Not Valid for a Volleyball Match");
                return;
            }
        }
        Match match = new Match(homeTeam.getTeamID(), awayTeam.getTeamID(), league.getLeagueID(), homeScore, awayScore);
        match.setLeagueID(league.getLeagueID());
        if (leagueService instanceof VolleyballService) {
            System.out.println("Enter Total number of Won and Lost Poans :(EXP. 52:13) ");
            String temp1 = scanner.nextLine();
            String[] splited1 = temp1.split(":");
            int[] sets = new int[2];
            sets[0] = Integer.parseInt(splited1[0]);
            sets[1] = Integer.parseInt(splited1[1]);
            if (!Validate.isPoanValid(homeScore, awayScore, sets[0], sets[1])) {
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
        System.out.println("Enter Team Name to Add to the League: ");
        String teamName = scanner.nextLine();
        if (Validate.isNameValid(teamName)) {
            Team newTeam = null;
            if (leagueService instanceof FootballService)
                newTeam = new FootballTeam(league.getLeagueID(), teamName);
            else if (leagueService instanceof VolleyballService) {
                newTeam = new VolleyballTeam(league.getLeagueID(), teamName);
            }
            int teamID = leagueService.addTeam(league, newTeam);
            newTeam.setTeamID(teamID);
            league.addTeam(newTeam);
            System.out.println("Team " + newTeam.getTeamName() + " added Successfully!");
            System.out.println("---------------------------------------");
        } else
            printValidationErr();
    }

    private static void printValidationErr() {
        System.out.println("Enter a Valid Name");
        System.out.println("The length should be at least 5 characters");
        System.out.println("Special Characters are not allowed");
    }
}
