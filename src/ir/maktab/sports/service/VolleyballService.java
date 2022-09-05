package ir.maktab.sports.service;

import ir.maktab.sports.data.League;
import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.sortHelper.sortByPoints;
import ir.maktab.sports.data.sortHelper.sortByWonSets;
import ir.maktab.sports.data.team.Team;
import ir.maktab.sports.data.team.VolleyballTeam;
import ir.maktab.sports.repository.team.VolleyballRepository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class VolleyballService implements LeagueService {
    private VolleyballRepository volleyballRepository = new VolleyballRepository();

    @Override
    public int addTeam(League league, Team team) throws SQLException {
        int teamID = volleyballRepository.addTeam(team);
        team.setTeamID(teamID);
        volleyballRepository.setTeamsLeagueID(team, league.getLeagueID());
        team.setLeagueID(league.getLeagueID());
        volleyballRepository.updateLeague(team);
        int initialSize = league.getTeamList().size();
        for (int i = 0; i < initialSize; i++) {
            Match match = new Match(league.getLeagueID(), team.getTeamID(), league.getTeamList().get(i).getTeamID());
            int matchID = volleyballRepository.addMatch(match);
            match.setMatchID(matchID);
            match.setLeagueID(league.getLeagueID());
            league.addMatch(match);
        }
        for (int i = 0; i < initialSize; i++) {
            Match match = new Match(league.getLeagueID(), league.getTeamList().get(i).getTeamID(), team.getTeamID());
            int matchID = volleyballRepository.addMatch(match);
            match.setMatchID(matchID);
            league.addMatch(match);
        }
        return teamID;
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
    public boolean addMatch(League league, Match match) throws SQLException {
        VolleyballTeam homeT = (VolleyballTeam) league.findTeam(match.getHomeTeamID());
        VolleyballTeam awayT = (VolleyballTeam) league.findTeam(match.getAwayTeamID());

        homeT.setPlayed(homeT.getPlayed() + 1);
        awayT.setPlayed(awayT.getPlayed() + 1);

        homeT.setPoints(homeT.getPoints() + match.getHomeTeamPoints());
        awayT.setPoints(awayT.getPoints() + match.getAwayTeamPoints());

        if (match.getHomeTeamScore() > match.getAwayTeamScore()) {
            homeT.setWon(homeT.getWon() + 1);
            awayT.setLost(awayT.getLost() + 1);
        }
        else {
            awayT.setWon(awayT.getWon() + 1);
            homeT.setLost(homeT.getLost() + 1);
        }
        volleyballRepository.updateTeam(homeT);
        volleyballRepository.updateTeam(awayT);

        return volleyballRepository.updatMatch(match);
    }

    @Override
    public int addLeague(League league) throws SQLException {
        int initialListsize = league.getTeamList().size();
        for (int i = 0; i < initialListsize; i++) {
            int teamID = volleyballRepository.addTeam(league.getTeamList().get(i));
            league.getTeamList().get(i).setTeamID(teamID);
        }

        int leagueID = volleyballRepository.addLeague(league);
        league.setLeagueID(leagueID);

        for (int i = 0; i < initialListsize; i++) {
            volleyballRepository.setTeamsLeagueID(league.getTeamList().get(i), leagueID);
        }
        for (int i = 0; i < initialListsize; i++) {
            for (int j = 0; j < initialListsize; j++) {
                Team hometeam = league.getTeamList().get(i);
                Team awayteam = league.getTeamList().get(j);
                if (!hometeam.equals(awayteam)) {
                    Match match = new Match(leagueID, hometeam.getTeamID(), awayteam.getTeamID());
                    int matchID = volleyballRepository.addMatch(match);
                    match.setMatchID(matchID);
                    league.addMatch(match);
                }
            }
        }
        return leagueID;
    }

    @Override
    public void rankingTable(List<Team> teamList) {
        Collections.sort(teamList, new sortByPoints());
        Collections.sort(teamList, new sortByWonSets());
    }

    @Override
    public String toString() {
        return "Volleyball League";
    }
}
