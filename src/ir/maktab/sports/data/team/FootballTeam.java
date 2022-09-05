package ir.maktab.sports.data.team;

public class FootballTeam extends Team{
    private int drawn;
    private int goalsFor;
    private int goalsAgainst;

    public FootballTeam(int teamID, int leagueID, int numOfPlayed, int numOfWon, int numOfLost, int points, int drawn, int goalsFor, int goalsAgainst) {
        super(teamID, leagueID, numOfPlayed, numOfWon, numOfLost, points);
        this.drawn = drawn;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

    public FootballTeam(String teamName) {
        super(teamName);
    }

    @Override
    public int calculatePoints() {
        int points = super.getWon() * 3 + drawn;
        this.setPoints(points);
        return points;
    }

    public FootballTeam(String teamName, int numOfMatch, int numOfWin, int numOfLose, int points, int drawn, int goalsFor, int goalsAgainst) {
        super(teamName, numOfMatch, numOfWin, numOfLose,points);
        this.drawn = drawn;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

    public int getDrawn() {
        return drawn;
    }

    public void setDrawn(int drawn) {
        this.drawn = drawn;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    @Override
    public String toString() {

        return "Team Information Of " + super.getTeamName()+"\n"+
                " Played : " + super.getPlayed() +
                "\t Won :" + super.getWon() +
                "\t Drawn : " + drawn +
                "\t Lost : " + super.getLost()+
                "\t GF : " + goalsFor +
                "\t GA : " + goalsAgainst +
                "\t POINTS : "+ super.getPoints();
    }
}
