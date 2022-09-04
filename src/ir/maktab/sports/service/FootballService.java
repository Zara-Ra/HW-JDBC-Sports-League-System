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
        return footballRepository.addTeam(team);
    }

    @Override
    public boolean deleteTeam(Team team) throws SQLException {
        return footballRepository.removeTeam(team);
    }

    @Override
    public Team TeamInfo(Team team) {
        return null;
    }

    @Override
    public int addMatch(Match match) throws SQLException {
        return footballRepository.addMatch(match);
    }

    @Override
    public int addLeague(League league) throws SQLException {
        return footballRepository.addLeague(league);
    }


    @Override
    public void rankingTable() {

    }
}
