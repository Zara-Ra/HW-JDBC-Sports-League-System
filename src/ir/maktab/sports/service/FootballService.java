package ir.maktab.sports.service;

import ir.maktab.sports.data.League;
import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.team.Team;
import ir.maktab.sports.repository.team.FootballRepository;

import java.sql.SQLException;

public class FootballService implements LeagueService {

    private FootballRepository footballRepository = new FootballRepository();

    @Override
    public int addTeam(Team team) throws SQLException {
        int teamID = footballRepository.addTeam(team);
        team.setTeamID(teamID);
        footballRepository.updateLeague(team);
        return teamID;
    }

    @Override
    public boolean deleteTeam(Team team) throws SQLException {
        return footballRepository.removeTeam(team);
    }

    @Override
    public Team TeamInfoByID(int ID) throws SQLException {
        return footballRepository.teamInfo(ID);
    }

    @Override
    public int addMatch(Match match) throws SQLException {
        int matchID = footballRepository.addMatch(match);

        return matchID;
    }

    @Override
    public int addLeague(League league) throws SQLException {
        for (int i = 0; i < league.getTeamList().size(); i++) {
            int teamID = footballRepository.addTeam(league.getTeamList().get(i));
            league.getTeamList().get(i).setTeamID(teamID);
        }

        int leagueID = footballRepository.addLeague(league);
        league.setLeagueID(leagueID);

        for (int i = 0; i < league.getTeamList().size(); i++) {
            footballRepository.setTeamsLeagueID(league.getTeamList().get(i) , leagueID);
        }
        return leagueID;
    }

    @Override
    public void rankingTable() {

    }

    @Override
    public String toString() {
        return "Football League";
    }
}
