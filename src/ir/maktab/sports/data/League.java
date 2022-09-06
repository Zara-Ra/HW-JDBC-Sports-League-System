package ir.maktab.sports.data;

import ir.maktab.sports.data.team.Team;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class League {
    //TODO have a constructor with different number of teams
    //TODO delete matchList from League, just like its table
    private List<Team> teamList = new ArrayList<>();
    //private List<Match> matchList = new ArrayList<>();

    private int LeagueID;
    private Date startDate;

    public League(Date startDate) {
        this.startDate = startDate;
    }

    public int getLeagueID() {
        return LeagueID;
    }

    public void setLeagueID(int leagueID) {
        LeagueID = leagueID;
    }

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

    public Team findTeam(int id){
        for (int i = 0; i < teamList.size(); i++) {
            if(teamList.get(i).getTeamID() == id)
                return teamList.get(i);
        }
        return null;
    }
    public Team findTeam(String name){
        for (int i = 0; i < teamList.size(); i++) {
            if(name.equals(teamList.get(i).getTeamName()))
                return teamList.get(i);
        }
        return null;
    }
    /*public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }

    public void addMatch(Match match) throws SQLException {
        matchList.add(match);
    }*/

    @Override
    public String toString() {
        String teamNames = "";
        for (int i = 0; i < teamList.size(); i++) {
            teamNames = teamNames.concat(teamList.get(i).getTeamName());
            teamNames = teamNames.concat("\t");
        }
        return "League ID " + LeagueID +
                " Start Date " + startDate+
                "\n Team Names : " + teamNames;
    }
}
