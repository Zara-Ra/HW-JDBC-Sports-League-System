package ir.maktab.sports.data;

public class Match {
    private int matchID;
    private int leagueID;
    private int homeTeamID;
    private int awayTeamID;
    private int homeTeamPoints;
    private int awayTeamPoints;
    private    int homeTeamScore;
    private int awayTeamScore;

    public Match(int homeTeamID, int awayTeamID, int homeTeamPoints, int awayTeamPoints, int leagueID,int homeTeamScore,int awayTeamScore) {
        this.homeTeamID = homeTeamID;
        this.awayTeamID = awayTeamID;
        this.homeTeamPoints = homeTeamPoints;
        this.awayTeamPoints = awayTeamPoints;
        this.leagueID = leagueID;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
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
