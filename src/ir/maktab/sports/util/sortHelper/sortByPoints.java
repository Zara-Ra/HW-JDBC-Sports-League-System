package ir.maktab.sports.util.sortHelper;

import ir.maktab.sports.data.team.Team;

import java.util.Comparator;

public class sortByPoints implements Comparator<Team> {
    @Override
    public int compare(Team team1, Team team2) {
        return team2.getPoints() - team1.getPoints();
    }

}
