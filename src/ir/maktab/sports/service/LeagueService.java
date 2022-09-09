package ir.maktab.sports.service;

import ir.maktab.sports.dao.League;
import ir.maktab.sports.dao.Match;
import ir.maktab.sports.dao.team.Team;

import java.sql.SQLException;
import java.util.List;

public interface LeagueService {

    int addTeam(League league, Team team) throws SQLException;

    boolean deleteTeam(Team team) throws SQLException;

    Team teamInfo(int ID) throws SQLException;

    List <Team> findTeamsByLeagueID(int leagueID) throws SQLException;
    boolean addMatch(League league, Match match) throws SQLException;

    int addLeague(League league) throws SQLException;

    void rankingTable(List<Team> teamList);//todo read from table

    List<League> previousLeagues() throws SQLException;
    League findLeagueByName(String leagueName) throws SQLException;
}
