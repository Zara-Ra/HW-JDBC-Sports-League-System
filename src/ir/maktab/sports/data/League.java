package ir.maktab.sports.data;

import ir.maktab.sports.data.team.Team;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class League {
    private List<Team> teamList = new ArrayList<>();
    private List<Match> matchList = new ArrayList<>();

    private Date startDate;

    public League(Date startDate) {
        this.startDate = startDate;
    }
    //private LeagueService leagueService;

   /* public League(List<Team> teamList, List<Match> matchList) {
        this.teamList = teamList;
        this.matchList = matchList;
    }*/

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

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
