package ir.maktab.sports.service;

import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.League;
import ir.maktab.sports.data.team.Team;

import java.sql.SQLException;

public interface LeagueService {
    boolean addTeam(Team team);
    boolean deleteTeam(Team team);
    Team TeamInfo(Team team);
    int addMatch(Match match) throws SQLException;
    void rankingTable();
}
