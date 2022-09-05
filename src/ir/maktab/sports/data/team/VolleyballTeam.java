package ir.maktab.sports.data.team;

import java.util.Arrays;

public class VolleyballTeam extends Team{
    private int [] sets = new int[2];// 1: number of won sets 2: number of lost sets

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
        this.sets = sets;
    }

    @Override
    public String toString() {
        return "Volleyball Team " + super.getTeamName()+
                " Played : "+super.getNumOfPlayed()+
                "\t Won : "+super.getNumOfWon()+
                "\t Lost : "+super.getNumOfLost()+
                "\t Sets "+ sets[0] + ":" + sets[1];
    }
}
