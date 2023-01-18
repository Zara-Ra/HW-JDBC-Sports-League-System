package ir.maktab.sports.dao.team;

public class VolleyballTeam extends Team {
    private int poans;
    private int scoreSets;

    public VolleyballTeam(String teamName) {
        super(teamName);
    }

    public VolleyballTeam(int leagueID, String teamName) {
        super(leagueID, teamName);
    }

    public VolleyballTeam(int teamID, int leagueID, String teamName, int played, int won, int lost, int points, int poans, int scoreSets) {
        super(teamID, leagueID, teamName, played, won, lost, points);
        this.poans = poans;
        this.scoreSets = scoreSets;
    }

    public int getScoreSets() {
        return scoreSets;
    }

    public void setScoreSets(int scoreSets) {
        this.scoreSets = scoreSets;
    }

    public int getPoans() {
        return poans;
    }

    public void setPoans(int poans) {
        this.poans += poans;
    }

    @Override
    public String toString() {
        return super.getTeamName() + "\n" +
                " Played : " + super.getPlayed() +
                "\t Won : " + super.getWon() +
                "\t Lost : " + super.getLost() +
                "\t Total Points : " + super.getPoints() +
                "\t Won Sets : " + scoreSets +
                "\t Poans : " + poans + "\n";
    }
}
