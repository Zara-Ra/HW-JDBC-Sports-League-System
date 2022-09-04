package ir.maktab.sports.data;

import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.team.Team;
import ir.maktab.sports.service.LeagueService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class League {
    private List<Team> teamList = new ArrayList<>();
    private List<Match> matchList = new ArrayList<>();
    //private LeagueService leagueService;

   /* public League(List<Team> teamList, List<Match> matchList) {
        this.teamList = teamList;
        this.matchList = matchList;
    }*/

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public void addTeam(Team team) {
        teamList.add(team);
    }

    public void deleteTeam(Team team) {
        teamList.remove(team);
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }

    public void addMatch(Match match) throws SQLException {
        matchList.add(match);
    }

    public void ranking() {
        //TODO not sure to implement it here
    }
}
