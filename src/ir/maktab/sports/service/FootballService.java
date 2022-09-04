package ir.maktab.sports.service;

import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.team.Team;
import ir.maktab.sports.repository.FootballRepository;
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
    public boolean addMatch(Match match) throws SQLException {
        if(matchRepository.addFootballMatch(match))
            return true;
        return false;
    }

    @Override
    public void rankingTable() {

    }
}
