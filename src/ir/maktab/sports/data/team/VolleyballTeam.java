package ir.maktab.sports.data.team;

public class VolleyballTeam extends Team{
    private int poans ;//  پوئن شماری
    private int scoreSets; // TODO ست های برده و باخته که داخل متچ نگگهداری می شه
    public VolleyballTeam(String teamName) {
        super(teamName);
    }


    public int getScoreSets() {
        return scoreSets;
    }

    public void setScoreSets(int scoreSets) {
        this.scoreSets = scoreSets;
    }

    public VolleyballTeam(String teamName, int numOfMatch, int numOfWin, int numOfLose, int points, int poans,int scoreSets) {
        super(teamName, numOfMatch, numOfWin, numOfLose,points);
        this.poans = poans;
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
        return "Volleyball Team " + super.getTeamName()+
                " Played : "+super.getPlayed()+
                "\t Won : "+super.getWon()+
                "\t Lost : "+super.getLost()+
                "\t Total Points : "+ super.getPoints()+
                "\t Won Sets : "+ scoreSets+
                "\t Poans : " + poans;
    }
}
