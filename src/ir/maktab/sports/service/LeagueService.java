package ir.maktab.sports.service;

import ir.maktab.sports.data.League;
import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.team.Team;

import java.sql.SQLException;

public interface LeagueService {

    int addTeam(Team team) throws SQLException;
    boolean deleteTeam(Team team) throws SQLException;
    Team TeamInfoByID(int ID) throws SQLException;
    int addMatch(Match match) throws SQLException;
    int addLeague(League league) throws SQLException;
    void rankingTable();
}
