package ir.maktab.sports.service;

import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.League;
import ir.maktab.sports.data.team.Team;
import ir.maktab.sports.repository.team.FootballRepository;
import ir.maktab.sports.repository.MatchRepository;

import java.sql.SQLException;

public class FootballService implements LeagueService {
    private MatchRepository matchRepository = new MatchRepository();
    private FootballRepository footballRepository = new FootballRepository();
    @Override
    public boolean addTeam(Team team) {
        return false;
    }

    @Override
    public boolean deleteTeam(Team team) {
        return false;
    }

    @Override
    public Team TeamInfo(Team team) {
        return null;
    }

    @Override
    public int addMatch(Match match) throws SQLException {
        return matchRepository.addFootballMatch(match);
    }

    @Override
    public void rankingTable() {

    }
}
