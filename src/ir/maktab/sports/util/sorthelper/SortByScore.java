package ir.maktab.sports.util.sortHelper;

import ir.maktab.sports.dao.team.Team;
import ir.maktab.sports.dao.team.VolleyballTeam;

import java.util.Comparator;

public class sortByScore implements Comparator<Team> {
    @Override
    public int compare(Team team1, Team team2) {
        return ((VolleyballTeam) team2).getScoreSets()- ((VolleyballTeam) team1).getScoreSets();
    }
}
