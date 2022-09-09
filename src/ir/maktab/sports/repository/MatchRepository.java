package ir.maktab.sports.repository;

import ir.maktab.sports.dao.Match;
import ir.maktab.sports.dao.enums.Sports;
import ir.maktab.sports.util.AppConstant;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MatchRepository {
    public boolean addMatch(Match match) throws SQLException {
        String sql = "INSERT INTO match (home_team_id,away_team_id,league_id) VALUES (?,?,?)";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, match.getHomeTeamID());
        preparedStatement.setInt(2, match.getAwayTeamID());
        preparedStatement.setInt(3, match.getLeagueID());
        return preparedStatement.executeUpdate() != 0;
    }

    public boolean updateMatch(Match match, Sports sportsType) throws SQLException {
        String sql = "UPDATE match SET home_team_score=?,away_team_score=? WHERE home_team_id = ? AND away_team_id = ? AND league_id = ?";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);

        preparedStatement.setInt(1, match.getHomeTeamScore());
        preparedStatement.setInt(2, match.getAwayTeamScore());
        preparedStatement.setInt(3, match.getHomeTeamID());
        preparedStatement.setInt(4, match.getAwayTeamID());
        preparedStatement.setInt(5, match.getLeagueID());
        return preparedStatement.executeUpdate() != 0;
    }

}
