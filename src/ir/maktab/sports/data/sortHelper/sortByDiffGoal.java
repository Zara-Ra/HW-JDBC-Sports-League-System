package ir.maktab.sports.data.sortHelper;

import ir.maktab.sports.data.team.FootballTeam;
import ir.maktab.sports.data.team.Team;

import java.util.Comparator;

public class sortByDiffGoal implements Comparator<Team> {
    @Override
    public int compare(Team team1, Team team2) {
        FootballTeam ft1 = (FootballTeam) team1;
        FootballTeam ft2 = (FootballTeam) team2;
        int DG1 = ft1.getGoalsFor() - ft1.getGoalsAgainst();
        int DG2 = ft2.getGoalsFor() - ft2.getGoalsAgainst();
        return DG2 - DG1;
    }
}
