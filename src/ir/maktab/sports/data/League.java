package ir.maktab.sports.data;

import ir.maktab.sports.data.team.Team;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class League {
    //TODO have a constructor with different number of teams
    private List<Team> teamList = new ArrayList<>();

    private int LeagueID;
    private String leagueName;
    private Date startDate;

    public League(Date startDate,String leagueName) {
        this.startDate = startDate;
        this.leagueName = leagueName;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
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
    @Override
    public String toString() {
        String teamNames = "";
        for (int i = 0; i < teamList.size(); i++) {
            teamNames = teamNames.concat(teamList.get(i).getTeamName());
            teamNames = teamNames.concat(" ");
        }
        return "League Name " + leagueName +
                " Start Date " + startDate+
                "\n Team Names : " + teamNames;
    }
}
