package ir.maktab.sports.dao.team;

public class FootballTeam extends Team{
    private int drawn;
    private int goalsFor;
    private int goalsAgainst;

    public FootballTeam(String teamName) {
        super(teamName);
    }

    public FootballTeam(int leagueID, String teamName) {
        super(leagueID, teamName);
    }

    public FootballTeam(int teamID, int leagueID, String teamName, int played, int won, int lost, int points, int drawn, int goalsFor, int goalsAgainst) {
        super(teamID, leagueID, teamName, played, won, lost, points);
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

        return  super.getTeamName()+"\n"+
                " Played : " + super.getPlayed() +
                "\t Won :" + super.getWon() +
                "\t Drawn : " + drawn +
                "\t Lost : " + super.getLost()+
                "\t GF : " + goalsFor +
                "\t GA : " + goalsAgainst +
                "\t GD : " + (goalsFor-goalsAgainst) +
                "\t POINTS : "+ super.getPoints()+"\n";
    }
}
