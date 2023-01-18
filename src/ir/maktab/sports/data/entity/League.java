package ir.maktab.sports.dao;

import ir.maktab.sports.dao.enums.Sports;
import ir.maktab.sports.dao.team.Team;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class League {
    private List<Team> teamList = new ArrayList<>();
    private int LeagueID;
    private String leagueName;
    private Date startDate;
    private Sports sportType;

    public League(Date startDate, String leagueName, Sports type) {
        this.startDate = startDate;
        this.leagueName = leagueName;
        this.sportType = type;
    }

    public League(int leagueID, Date startDate, String leagueName, Sports sportType) {
        LeagueID = leagueID;
        this.leagueName = leagueName;
        this.startDate = startDate;
        this.sportType = sportType;
    }

    public Sports getSportType() {
        return sportType;
    }

    public void setSportType(Sports sportType) {
        this.sportType = sportType;
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

    public Team findTeam(int id) {
        for (Team team : teamList) {
            if (team.getTeamID() == id)
                return team;
        }
        return null;
    }

    public Team findTeam(String name) {
        for (Team team : teamList) {
            if (name.equals(team.getTeamName()))
                return team;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof League)) return false;
        League league = (League) o;
        return getLeagueID() == league.getLeagueID() && getStartDate().equals(league.getStartDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeagueID(), getStartDate());
    }

    @Override
    public String toString() {
        String teamNames = "";
        for (Team team : teamList) {
            teamNames = teamNames.concat(team.getTeamName());
            teamNames = teamNames.concat(" ");
        }
        return "League Name " + leagueName +
                " Start Date " + startDate +
                "\n Team Names : " + teamNames;
    }
}
