package ir.maktab.sports.service;

import ir.maktab.sports.dao.League;
import ir.maktab.sports.dao.Match;
import ir.maktab.sports.dao.enums.Sports;
import ir.maktab.sports.dao.team.Team;
import ir.maktab.sports.dao.team.VolleyballTeam;
import ir.maktab.sports.repository.LeagueRepository;
import ir.maktab.sports.repository.MatchRepository;
import ir.maktab.sports.repository.TeamRepository;
import ir.maktab.sports.util.sortHelper.sortByPoan;
import ir.maktab.sports.util.sortHelper.sortByPoints;
import ir.maktab.sports.util.sortHelper.sortByScore;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class VolleyballService implements LeagueService {
    final private LeagueRepository leagueRepository = new LeagueRepository();
    final private MatchRepository matchRepository = new MatchRepository();
    final private TeamRepository teamRepository = new TeamRepository();

    @Override
    public int addTeam(League league, Team team) throws SQLException {
        int teamID = teamRepository.addTeam(team);
        team.setTeamID(teamID);
        int initialSize = league.getTeamList().size();
        for (int i = 0; i < initialSize; i++) {
            Match match = new Match(league.getLeagueID(), team.getTeamID(), league.getTeamList().get(i).getTeamID());
            matchRepository.addMatch(match);
        }
        for (int i = 0; i < initialSize; i++) {
            Match match = new Match(league.getLeagueID(), league.getTeamList().get(i).getTeamID(), team.getTeamID());
            matchRepository.addMatch(match);
        }
        return teamID;
    }

    @Override
    public boolean deleteTeam(Team team) throws SQLException {
        return teamRepository.removeTeam(team);
    }

    @Override
    public Team teamInfo(int ID) throws SQLException {
        return teamRepository.findTeamByID(ID, Sports.VOLLEYBALL);
    }

    @Override
    public List<Team> findTeamsByLeagueID(int leagueID) throws SQLException {
        return teamRepository.findTeamsByLeagueID(leagueID, Sports.VOLLEYBALL);
    }

    @Override
    public boolean addMatch(League league, Match match) throws SQLException {
        VolleyballTeam homeT = (VolleyballTeam) league.findTeam(match.getHomeTeamID());
        VolleyballTeam awayT = (VolleyballTeam) league.findTeam(match.getAwayTeamID());

        homeT.setPlayed(homeT.getPlayed() + 1);
        awayT.setPlayed(awayT.getPlayed() + 1);

        homeT.setScoreSets(homeT.getScoreSets() + match.getHomeTeamScore());
        awayT.setScoreSets(awayT.getScoreSets() + match.getAwayTeamScore());

        if (match.getHomeTeamScore() == 3 && match.getAwayTeamScore() == 1 || match.getAwayTeamScore() == 0) {
            homeT.setWon(homeT.getWon() + 1);
            awayT.setLost(awayT.getLost() + 1);
            homeT.setPoints(homeT.getPoints() + 3);
        } else if (match.getHomeTeamScore() == 3 && match.getAwayTeamScore() == 2) {
            homeT.setWon(homeT.getWon() + 1);
            awayT.setLost(awayT.getLost() + 1);
            homeT.setPoints(homeT.getPoints() + 2);
            awayT.setPoints(awayT.getPoints() + 1);
        } else if (awayT.getPoints() == 3 && homeT.getPoints() == 1 || homeT.getPoints() == 0) {
            awayT.setWon(awayT.getWon() + 1);
            homeT.setLost(homeT.getLost() + 1);
            awayT.setPoints(awayT.getPoints() + 3);
        } else if (awayT.getPoints() == 3 && homeT.getPoints() == 2) {
            awayT.setWon(awayT.getWon() + 1);
            homeT.setLost(homeT.getLost() + 1);
            awayT.setPoints(awayT.getPoints() + 2);
            homeT.setPoints(homeT.getPoints() + 1);
        }
        teamRepository.updateTeam(homeT, Sports.VOLLEYBALL);
        teamRepository.updateTeam(awayT, Sports.VOLLEYBALL);

        return matchRepository.updateMatch(match, Sports.VOLLEYBALL);
    }

    @Override
    public int addLeague(League league) throws SQLException {
        int leagueID = leagueRepository.addLeague(league);
        league.setLeagueID(leagueID);
        int initialListsize = league.getTeamList().size();
        for (int i = 0; i < initialListsize; i++) {
            league.getTeamList().get(i).setLeagueID(leagueID);
            int teamID = teamRepository.addTeam(league.getTeamList().get(i));
            league.getTeamList().get(i).setTeamID(teamID);
        }
        for (int i = 0; i < initialListsize; i++) {
            for (int j = 0; j < initialListsize; j++) {
                Team hometeam = league.getTeamList().get(i);
                Team awayteam = league.getTeamList().get(j);
                if (!hometeam.equals(awayteam)) {
                    Match match = new Match(leagueID, hometeam.getTeamID(), awayteam.getTeamID());
                    matchRepository.addMatch(match);
                }
            }
        }
        return leagueID;
    }

    @Override
    public void rankingTable(List<Team> teamList) {
        Collections.sort(teamList, new sortByPoan());
        Collections.sort(teamList, new sortByScore());
        Collections.sort(teamList, new sortByPoints());
    }

    @Override
    public List<League> previousLeagues() throws SQLException {
        return leagueRepository.getAllLeagues(Sports.VOLLEYBALL);
    }

    @Override
    public League findLeagueByName(String leagueName) throws SQLException {
        return leagueRepository.findLeagueByName(leagueName);
    }

    @Override
    public String toString() {
        return "Volleyball League";
    }
}
