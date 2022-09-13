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

    public static void main(String[] args) throws SQLException {
        System.out.println("---------------------------------------");
        System.out.println("Welcome to Sport League Generator!");
        MainHandler mainHandler = new MainHandler();
        mainHandler.firstMenu();
    }
}
