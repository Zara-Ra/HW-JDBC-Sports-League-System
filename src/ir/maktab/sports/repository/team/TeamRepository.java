package ir.maktab.sports.repository.team;

import ir.maktab.sports.data.League;
import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.team.Team;

import java.sql.SQLException;

public interface TeamRepository {
    int addTeam(Team team) throws SQLException;//return new team id
    boolean removeTeam(Team team) throws SQLException;

    Team teamInfo(int ID) throws SQLException;
    boolean updateTeam(Team team) throws SQLException;
    int addMatch(Match match) throws SQLException;

    boolean updateMatch(Match match) throws SQLException;
    int addLeague(League league) throws SQLException;

    boolean setTeamsLeagueID(Team team, int leagueID) throws SQLException;

    boolean updateLeague(Team team) throws SQLException;

    String [] showAllLeague() throws SQLException;

}
