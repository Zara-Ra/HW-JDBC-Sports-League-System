package ir.maktab.sports.service;

import ir.maktab.sports.data.League;
import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.sortHelper.sortByDiffGoal;
import ir.maktab.sports.data.sortHelper.sortByPoints;
import ir.maktab.sports.data.team.FootballTeam;
import ir.maktab.sports.data.team.Team;
import ir.maktab.sports.repository.team.FootballRepository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class FootballService implements LeagueService {

    private FootballRepository footballRepository = new FootballRepository();

    @Override
    public int addTeam(League league, Team team) throws SQLException {
        int teamID = footballRepository.addTeam(team);
        team.setTeamID(teamID);
        footballRepository.setTeamsLeagueID(team, league.getLeagueID());
        team.setLeagueID(league.getLeagueID());
        footballRepository.updateLeague(team);
        int initialSize = league.getTeamList().size();
        for (int i = 0; i < initialSize; i++) {
            Match match = new Match(league.getLeagueID(), team.getTeamID(), league.getTeamList().get(i).getTeamID());
            int matchID = footballRepository.addMatch(match);
            match.setMatchID(matchID);
            match.setLeagueID(league.getLeagueID());
            league.addMatch(match);
        }
        for (int i = 0; i < initialSize; i++) {
            Match match = new Match(league.getLeagueID(), league.getTeamList().get(i).getTeamID(), team.getTeamID());
            int matchID = footballRepository.addMatch(match);
            match.setMatchID(matchID);
            league.addMatch(match);
        }
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
    public boolean addMatch(League league, Match match) throws SQLException {
        FootballTeam homeT = (FootballTeam) league.findTeam(match.getHomeTeamID());
        FootballTeam awayT = (FootballTeam) league.findTeam(match.getAwayTeamID());

        homeT.setNumOfPlayed(homeT.getNumOfPlayed() + 1);
        awayT.setNumOfPlayed(awayT.getNumOfPlayed() + 1);

        homeT.setGoalsFor(homeT.getGoalsFor() + match.getHomeTeamScore());
        homeT.setGoalsAgainst(homeT.getGoalsAgainst() + match.getAwayTeamScore());

        awayT.setGoalsFor(awayT.getGoalsFor() + match.getHomeTeamScore());
        awayT.setGoalsAgainst(awayT.getGoalsAgainst() + match.getHomeTeamScore());

        if (match.getHomeTeamScore() > match.getAwayTeamScore()) {
            homeT.setPoints(homeT.getPoints() + 3);
            homeT.setNumOfWon(homeT.getNumOfWon() + 1);
            awayT.setNumOfLost(awayT.getNumOfLost() + 1);
        }
        else if (match.getHomeTeamScore() == match.getAwayTeamScore()) {
            homeT.setPoints(homeT.getPoints() + 1);
            awayT.setPoints(awayT.getPoints() + 1);
            homeT.setDrawn(homeT.getDrawn() + 1);
            awayT.setDrawn(awayT.getDrawn() + 1);
        }
        else {
            awayT.setPoints(awayT.getPoints() + 3);
            awayT.setNumOfWon(awayT.getNumOfWon() + 1);
            homeT.setNumOfLost(homeT.getNumOfLost() + 1);
        }
        footballRepository.updateTeam(homeT);
        footballRepository.updateTeam(awayT);
        return footballRepository.updatMatch(match);
    }

    @Override
    public int addLeague(League league) throws SQLException {
        int initialListsize = league.getTeamList().size();
        for (int i = 0; i < initialListsize; i++) {
            int teamID = footballRepository.addTeam(league.getTeamList().get(i));
            league.getTeamList().get(i).setTeamID(teamID);
        }

        int leagueID = footballRepository.addLeague(league);
        league.setLeagueID(leagueID);

        for (int i = 0; i < initialListsize; i++) {
            footballRepository.setTeamsLeagueID(league.getTeamList().get(i), leagueID);
        }
        for (int i = 0; i < initialListsize; i++) {
            for (int j = 0; j < initialListsize; j++) {
                Team hometeam = league.getTeamList().get(i);
                Team awayteam = league.getTeamList().get(j);
                if (!hometeam.equals(awayteam)) {
                    Match match = new Match(leagueID, hometeam.getTeamID(), awayteam.getTeamID());
                    int matchID = footballRepository.addMatch(match);
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
        Collections.sort(teamList, new sortByDiffGoal());
    }

    @Override
    public String toString() {
        return "Football League";
    }
}
