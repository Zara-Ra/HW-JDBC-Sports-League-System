package ir.maktab.sports.repository.team;

import ir.maktab.sports.data.team.Team;

import java.sql.SQLException;

public interface TeamRepository {
    int addTeam(Team team) throws SQLException;//return new team id
    void removeTeam(Team team) throws SQLException;
    //void rankingTeams();
}
