package ir.maktab.sports.data.sortHelper;

import ir.maktab.sports.data.team.FootballTeam;

import java.util.Comparator;

public class sortByDiffGoal implements Comparator<FootballTeam> {
    @Override
    public int compare(FootballTeam ft1, FootballTeam ft2) {
        int DG1 = ft1.getGoalsFor() - ft1.getGoalsAgainst();
        int DG2 = ft2.getGoalsFor() - ft2.getGoalsAgainst();
        return DG2 - DG1;
    }
}
