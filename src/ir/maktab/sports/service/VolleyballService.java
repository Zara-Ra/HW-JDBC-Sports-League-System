package ir.maktab.sports.service;

import ir.maktab.sports.data.League;
import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.team.Team;
import ir.maktab.sports.repository.team.VolleyballRepository;

import java.sql.SQLException;

public class VolleyballService implements LeagueService {
    private VolleyballRepository volleyballRepository = new VolleyballRepository();

    @Override
    public int addTeam(Team team) throws SQLException {
        return volleyballRepository.addTeam(team);
    }

    @Override
    public boolean deleteTeam(Team team) throws SQLException {
        return volleyballRepository.removeTeam(team);
    }

    @Override
    public Team TeamInfoByID(int ID) throws SQLException {
        return volleyballRepository.teamInfo(ID);
    }

    @Override
    public int addMatch(Match match) throws SQLException {
        return volleyballRepository.addMatch(match);
    }

    @Override
    public int addLeague(League league) throws SQLException {
        for (int i = 0; i < league.getTeamList().size(); i++) {
            int teamID = volleyballRepository.addTeam(league.getTeamList().get(i));
            league.getTeamList().get(i).setTeamID(teamID);
        }

        int leagueID = volleyballRepository.addLeague(league);
        league.setLeagueID(leagueID);

        for (int i = 0; i < league.getTeamList().size(); i++) {
            volleyballRepository.setTeamsLeagueID(league.getTeamList().get(i) , leagueID);
        }
        return leagueID;
    }

    @Override
    public void rankingTable() {

    }
}
