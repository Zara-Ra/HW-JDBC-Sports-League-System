package ir.maktab.sports.presentation;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println("---------------------------------------");
        System.out.println("Welcome to Sport League Generator!");
        MainHandler mainHandler = new MainHandler();
        mainHandler.firstMenu();
    }
}
