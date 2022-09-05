package ir.maktab.sports.data.sortHelper;

import ir.maktab.sports.data.team.VolleyballTeam;

import java.util.Comparator;

public class sortByWonSets implements Comparator<VolleyballTeam> {
    @Override
    public int compare(VolleyballTeam vt1, VolleyballTeam vt2) {
        int [] sets1 = vt1.getSets();
        int [] sets2 = vt2.getSets();
        return sets2[0] - sets1[0];
    }
}
