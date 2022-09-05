package ir.maktab.sports.data.team;

public abstract class Team {
    private int teamID;
    private int leagueID;
    private String teamName;
    private int numOfPlayed;
    private int numOfWon;
    private int numOfLost;
    private int points;

    public Team(int teamID, int leagueID, int numOfPlayed, int numOfWon, int numOfLost, int points) {
        this.teamID = teamID;
        this.leagueID = leagueID;
        this.numOfPlayed = numOfPlayed;
        this.numOfWon = numOfWon;
        this.numOfLost = numOfLost;
        this.points = points;
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(String teamName, int numOfMatch, int numOfWin, int numOfLose,int points) {
        this.teamName = teamName;
        this.numOfPlayed = numOfMatch;
        this.numOfWon = numOfWin;
        this.numOfLost = numOfLose;
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

    public void setPoints(int points) {
        this.points = points;
    }

    public abstract int calculatePoints();

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

    public int getNumOfPlayed() {
        return numOfPlayed;
    }

    public void setNumOfPlayed(int numOfPlayed) {
        this.numOfPlayed = numOfPlayed;
    }

    public int getNumOfWon() {
        return numOfWon;
    }

    public void setNumOfWon(int numOfWon) {
        this.numOfWon = numOfWon;
    }

    public int getNumOfLost() {
        return numOfLost;
    }

    public void setNumOfLost(int numOfLost) {
        this.numOfLost = numOfLost;
    }

    @Override
    public String toString() {
        return "Team Information of " + teamName+"\n"+
                " Played : " + numOfPlayed +
                "\t Won :" + numOfWon +
                "\t Lost : " + numOfLost;
    }
}
