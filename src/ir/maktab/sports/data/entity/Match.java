package ir.maktab.sports.dao;

import java.util.Objects;

public class Match {
    private int matchID;
    private int leagueID;
    private int homeTeamID;
    private int awayTeamID;
    private int homeTeamScore;
    private int awayTeamScore;

    public Match(int homeTeamID, int awayTeamID, int leagueID, int homeTeamScore, int awayTeamScore) {
        this.homeTeamID = homeTeamID;
        this.awayTeamID = awayTeamID;
        this.leagueID = leagueID;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return getMatchID() == match.getMatchID() && getLeagueID() == match.getLeagueID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMatchID(), getLeagueID());
    }

    public Match(int leagueID, int homeTeamID, int awayTeamID) {
        this.leagueID = leagueID;
        this.homeTeamID = homeTeamID;
        this.awayTeamID = awayTeamID;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public int getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(int leagueID) {
        this.leagueID = leagueID;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getHomeTeamID() {
        return homeTeamID;
    }

    public void setHomeTeamID(int homeTeamID) {
        this.homeTeamID = homeTeamID;
    }

    public int getAwayTeamID() {
        return awayTeamID;
    }

    public void setAwayTeamID(int awayTeamID) {
        this.awayTeamID = awayTeamID;
    }


    @Override
    public String toString() {
        return "Match Information of match ID " + matchID + "\n " +
                "Score of Home Team ID " + homeTeamID + " : " + homeTeamScore +
                "Score of Away Team ID " + awayTeamID + " : " + awayTeamScore;
    }
}
