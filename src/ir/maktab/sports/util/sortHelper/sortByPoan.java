package ir.maktab.sports.util.sortHelper;

import ir.maktab.sports.data.team.Team;
import ir.maktab.sports.data.team.VolleyballTeam;

import java.util.Comparator;

public class sortByPoan implements Comparator<Team> {
    @Override
    public int compare(Team team1, Team team2) {
        int poans1 = ((VolleyballTeam) team1).getPoans();
        int  poans2 = ((VolleyballTeam) team2).getPoans();

        return poans2 - poans1;
    }
}
