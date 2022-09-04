package ir.maktab.sports.repository;

import ir.maktab.sports.data.Match;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchRepository {
    public boolean addFootballMatch(Match match) throws SQLException {
        String sql = "INSERT INTO football_match (home_team_id,away_team_id,home_team_points,away_team_points) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,match.getHomeTeamID());
        preparedStatement.setInt(2,match.getAwayTeamID());
        preparedStatement.setInt(3,match.getHomeTeamPoints());
        preparedStatement.setInt(4,match.getAwayTeamPoints());
        preparedStatement.executeUpdate();

        String sqlForID = "SELECT match_id FROM football_match WHERE home_team_id = ? AND away_team_id = ?";
        PreparedStatement prepared = AppConstant.getConnection().prepareStatement(sqlForID);
        prepared.setInt(1, match.getHomeTeamID());
        prepared.setInt(2, match.getAwayTeamID());
        ResultSet resultSet = prepared.executeQuery();
        if (resultSet.next()) {
             match.setMatchID(resultSet.getInt(1));
            return true;
        }
        return false;

    }

    public boolean addVolleyballMatch(Match match) throws SQLException {
        String sql = "INSERT INTO volleyball_match (home_team_id,away_team_id,home_team_points,away_team_points) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,match.getHomeTeamID());
        preparedStatement.setInt(2,match.getAwayTeamID());
        preparedStatement.setInt(3,match.getHomeTeamPoints());
        preparedStatement.setInt(4,match.getAwayTeamPoints());
        preparedStatement.executeUpdate();

        String sqlForID = "SELECT match_id FROM volleyball_match WHERE home_team_id = ? AND away_team_id = ?";
        PreparedStatement prepared = AppConstant.getConnection().prepareStatement(sqlForID);
        prepared.setInt(1, match.getHomeTeamID());
        prepared.setInt(2, match.getAwayTeamID());
        ResultSet resultSet = prepared.executeQuery();
        if (resultSet.next()) {
             match.setMatchID(resultSet.getInt(1));
            return true;
        }
        return false;

    }
}
