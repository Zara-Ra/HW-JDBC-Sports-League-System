package ir.maktab.sports.service;

import ir.maktab.sports.data.League;
import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.team.Team;

import java.sql.SQLException;
import java.util.List;

public interface LeagueService {

    int addTeam(League league,Team team) throws SQLException;
    boolean deleteTeam(Team team) throws SQLException;
    Team TeamInfoByID(int ID) throws SQLException;
    boolean addMatch(League league,Match match) throws SQLException;
    int addLeague(League league) throws SQLException;
    void rankingTable(List<Team> teamList);
}
