package ir.maktab.sports.repository.team;

import ir.maktab.sports.data.team.Team;
import ir.maktab.sports.repository.util.AppConstant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        ResultSet resultSet = preparedStatement2.executeQuery();
        if(resultSet.next())
            return resultSet.getInt(1);
        return 0;
    }

    @Override
    public void removeTeam(Team team) throws SQLException {
        String sql = "DELETE FROM football_team WHERE team_id = ?";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,team.getTeamID());
        preparedStatement.executeUpdate();
    }
}
