package ir.maktab.sports.data.team;

public class VolleyballTeam extends Team{
    private int [] sets = new int[2];// 1: number of won sets 2: number of lost sets
    
    public VolleyballTeam(String teamName, int numOfMatch, int numOfWin, int numOfLose, int points,int[] sets) {
        super(teamName, numOfMatch, numOfWin, numOfLose, points);
        this.sets = sets;
    }

    public int[] getSets() {
        return sets;
    }

    public void setSets(int[] sets) {
        this.sets = sets;
    }

}
