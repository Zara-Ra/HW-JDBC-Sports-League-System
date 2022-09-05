package ir.maktab.sports.data.team;

public class VolleyballTeam extends Team{
    private int [] sets = new int[2];// 0: number of won sets 1: number of lost sets

    public VolleyballTeam(String teamName) {
        super(teamName);
    }

    @Override
    public int calculatePoints() {
        return 0;
    }

    public VolleyballTeam(String teamName, int numOfMatch, int numOfWin, int numOfLose, int points, int[] sets) {
        super(teamName, numOfMatch, numOfWin, numOfLose,points);
        this.sets = sets;
    }

    public int[] getSets() {
        return sets;
    }

    public void setSets(int[] sets) {
        this.sets[0] += sets[0];
        this.sets[1] += sets[1];
    }

    @Override
    public String toString() {
        return "Volleyball Team " + super.getTeamName()+
                " Played : "+super.getPlayed()+
                "\t Won : "+super.getWon()+
                "\t Lost : "+super.getLost()+
                "\t Sets "+ sets[0] + ":" + sets[1];
    }
}
