package ir.maktab.sports.service;

import ir.maktab.sports.data.entity.League;
import ir.maktab.sports.data.entity.Match;
import ir.maktab.sports.data.enums.Sports;
import ir.maktab.sports.data.entity.FootballTeam;
import ir.maktab.sports.data.entity.Team;
import ir.maktab.sports.data.repository.LeagueRepository;
import ir.maktab.sports.data.repository.MatchRepository;
import ir.maktab.sports.data.repository.TeamRepository;
import ir.maktab.sports.util.sorthelper.SortByDiffGoal;
import ir.maktab.sports.util.sorthelper.SortByPoints;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class FootballService implements LeagueService {

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
        return teamRepository.findTeamByID(ID, Sports.FOOTBALL);
    }

    @Override
    public List<Team> findTeamsByLeagueID(int leagueID) throws SQLException {
        return teamRepository.findTeamsByLeagueID(leagueID, Sports.FOOTBALL);
    }

    @Override
    public boolean addMatch(League league, Match match) throws SQLException {
        FootballTeam homeTeam = (FootballTeam) league.findTeam(match.getHomeTeamID());
        FootballTeam awayTeam = (FootballTeam) league.findTeam(match.getAwayTeamID());

        homeTeam.setPlayed(homeTeam.getPlayed() + 1);
        awayTeam.setPlayed(awayTeam.getPlayed() + 1);

        homeTeam.setGoalsFor(homeTeam.getGoalsFor() + match.getHomeTeamScore());
        homeTeam.setGoalsAgainst(homeTeam.getGoalsAgainst() + match.getAwayTeamScore());

        awayTeam.setGoalsFor(awayTeam.getGoalsFor() + match.getAwayTeamScore());
        awayTeam.setGoalsAgainst(awayTeam.getGoalsAgainst() + match.getHomeTeamScore());

        if (match.getHomeTeamScore() > match.getAwayTeamScore()) {
            homeTeam.setWon(homeTeam.getWon() + 1);
            awayTeam.setLost(awayTeam.getLost() + 1);
            homeTeam.setPoints(homeTeam.getPoints() + 3);
        } else if (match.getHomeTeamScore() == match.getAwayTeamScore()) {
            homeTeam.setDrawn(homeTeam.getDrawn() + 1);
            awayTeam.setDrawn(awayTeam.getDrawn() + 1);
            homeTeam.setPoints(homeTeam.getPoints() + 1);
            awayTeam.setPoints(awayTeam.getPoints() + 1);
        } else {
            awayTeam.setWon(awayTeam.getWon() + 1);
            homeTeam.setLost(homeTeam.getLost() + 1);
            awayTeam.setPoints(awayTeam.getPoints() + 3);
        }
        teamRepository.updateTeam(homeTeam, Sports.FOOTBALL);
        teamRepository.updateTeam(awayTeam, Sports.FOOTBALL);
        return matchRepository.updateMatch(match, Sports.FOOTBALL);
    }

    @Override
    public int addLeague(League league) throws SQLException {   //createLeagueAndTeams
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
        Collections.sort(teamList, new SortByDiffGoal());
        Collections.sort(teamList, new SortByPoints());
    }

    @Override
    public List<League> previousLeagues() throws SQLException {
        return leagueRepository.getAllLeagues(Sports.FOOTBALL);
    }

    @Override
    public League findLeagueByName(String leagueName) throws SQLException {
        return leagueRepository.findLeagueByName(leagueName);
    }

    @Override
    public String toString() {
        return "Football League";
    }
}
