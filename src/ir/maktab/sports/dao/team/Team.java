package ir.maktab.sports.dao.team;

import java.util.Objects;

public  class Team {
    private int teamID;
    private int leagueID;
    private String teamName;
    private int Played;
    private int won;
    private int lost;
    private int points;

    public Team(int teamID, int leagueID, String teamName, int played, int won, int lost, int points) {
        this.teamID = teamID;
        this.leagueID = leagueID;
        this.teamName = teamName;
        Played = played;
        this.won = won;
        this.lost = lost;
        this.points = points;
    }
    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(int leagueID, String teamName) {
        this.leagueID = leagueID;
        this.teamName = teamName;
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return getTeamID() == team.getTeamID() && getLeagueID() == team.getLeagueID() && getTeamName().equals(team.getTeamName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTeamID(), getLeagueID());
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPlayed() {
        return Played;
    }

    public void setPlayed(int played) {
        this.Played = played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    @Override
    public String toString() {
        return "Team Information of " + teamName+"\n"+
                " Played : " + Played +
                "\t Won :" + won +
                "\t Lost : " + lost;
    }
}
