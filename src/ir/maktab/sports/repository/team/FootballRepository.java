package ir.maktab.sports.repository;

import ir.maktab.sports.data.team.FootballTeam;
import ir.maktab.sports.data.team.Team;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FootballRepository implements TeamRepository{
    @Override
    public int addTeam(Team team) throws SQLException {
        String sql = "INSERT INTO football_team (team_name) VALUES (?)";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,team.getTeamName());
        preparedStatement.executeUpdate();

        String sqlID = "SELECT team_id FROM football_team WHERE team_name = ?";
        PreparedStatement preparedStatement2 = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement2.setString(1,team.getTeamName());
        preparedStatement2.executeQuery();

        return 0;
    }

    @Override
    public int removeTeam(Team team) {

        return 0;
    }
}
