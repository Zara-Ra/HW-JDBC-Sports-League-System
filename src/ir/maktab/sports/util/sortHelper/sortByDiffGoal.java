package ir.maktab.sports.util.sortHelper;

import ir.maktab.sports.dao.team.FootballTeam;
import ir.maktab.sports.dao.team.Team;

import java.util.Comparator;

public class sortByDiffGoal implements Comparator<Team> {
    @Override
    public int compare(Team team1, Team team2) {
        int DG1 = ((FootballTeam) team1).getGoalsFor() - ((FootballTeam) team1).getGoalsAgainst();
        int DG2 = ((FootballTeam) team2).getGoalsFor() - ((FootballTeam) team2).getGoalsAgainst();
        return DG2 - DG1;
    }
}
