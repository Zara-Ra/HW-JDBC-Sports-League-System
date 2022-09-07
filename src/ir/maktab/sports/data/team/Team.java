package ir.maktab.sports.data.team;

import java.util.Objects;

public abstract class Team {
    private int teamID;
    private int leagueID;
    private String teamName;
    private int Played;
    private int won;
    private int lost;
    private int points;

    public Team(int teamID, int leagueID, int Played, int won, int lost, int points) {
        this.teamID = teamID;
        this.leagueID = leagueID;
        this.Played = Played;
        this.won = won;
        this.lost = lost;
        this.points = points;
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(String teamName, int numOfMatch, int numOfWin, int numOfLose,int points) {
        this.teamName = teamName;
        this.Played = numOfMatch;
        this.won = numOfWin;
        this.lost = numOfLose;
        this.points = points;
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

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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
