package ir.maktab.sports.data;

public class Match {
    int matchID;
    int leagueID; //TODO add to navicat & constructors
    int homeTeamID;
    int awayTeamID;

    int homeTeamPoints;
    int awayTeamPoints;

    public Match(int homeTeamID, int awayTeamID, int homeTeamScore, int awayTeamScore, int leagueID) {
        this.homeTeamID = homeTeamID;
        this.awayTeamID = awayTeamID;
        this.homeTeamPoints = homeTeamScore;
        this.awayTeamPoints = awayTeamScore;
        this.leagueID = leagueID;
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

    public int getHomeTeamPoints() {
        return homeTeamPoints;
    }

    public void setHomeTeamPoints(int homeTeamPoints) {
        this.homeTeamPoints = homeTeamPoints;
    }

    public int getAwayTeamPoints() {
        return awayTeamPoints;
    }

    public void setAwayTeamPoints(int awayTeamPoints) {
        this.awayTeamPoints = awayTeamPoints;
    }

    @Override
    public String toString() {
        return "Match Information of match ID "+ matchID +"\n "+
                "Score of Home Team ID " + homeTeamID +" : "+ homeTeamPoints +
                "Score of Away Team ID " + awayTeamID +" : "+ awayTeamPoints;
    }
}
