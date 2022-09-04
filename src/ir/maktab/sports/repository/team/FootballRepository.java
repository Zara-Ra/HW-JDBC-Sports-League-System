package ir.maktab.sports.repository.team;

import ir.maktab.sports.data.League;
import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.team.Team;
import ir.maktab.sports.repository.util.AppConstant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FootballRepository implements TeamRepository {
    @Override
    public int addTeam(Team team) throws SQLException {
        String sql = "INSERT INTO football_team (team_name) VALUES (?)";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, team.getTeamName());
        preparedStatement.executeUpdate();

        String sqlID = "SELECT team_id FROM football_team WHERE team_name = ?";
        PreparedStatement preparedStatement2 = AppConstant.getConnection().prepareStatement(sqlID);
        preparedStatement2.setString(1, team.getTeamName());
        ResultSet resultSet = preparedStatement2.executeQuery();
        if (resultSet.next())
            return resultSet.getInt(1);
        return 0;
    }

    @Override
    public boolean removeTeam(Team team) throws SQLException {
        String sql = "DELETE FROM football_team WHERE team_id = ?";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, team.getTeamID());
        if (preparedStatement.executeUpdate() != 0)
            return true;
        return false;

    }

    @Override
    public int addMatch(Match match) throws SQLException {
        String sql = "INSERT INTO football_match (home_team_id,away_team_id,home_team_points,away_team_points) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, match.getHomeTeamID());
        preparedStatement.setInt(2, match.getAwayTeamID());
        preparedStatement.setInt(3, match.getHomeTeamPoints());
        preparedStatement.setInt(4, match.getAwayTeamPoints());
        preparedStatement.executeUpdate();

        String sqlForID = "SELECT match_id FROM football_match WHERE home_team_id = ? AND away_team_id = ?";
        PreparedStatement prepared = AppConstant.getConnection().prepareStatement(sqlForID);
        prepared.setInt(1, match.getHomeTeamID());
        prepared.setInt(2, match.getAwayTeamID());
        ResultSet resultSet = prepared.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public int addLeague(League league) throws SQLException {
        String sql = "INSERT INTO football_league (team1,team2,team3,team4,team5,team6,team7,team8,startdate) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        List<Team> teamList = league.getTeamList();
        for (int i = 0; i < 8 ; i++) {
            preparedStatement.setInt(i+1, teamList.get(i).getTeamID());
        }
        preparedStatement.setDate(9,league.getStartDate());
        preparedStatement.executeUpdate();

        String sqlForID = "SELECT league_id FROM football_league WHERE startdate = ?";
        PreparedStatement prepared = AppConstant.getConnection().prepareStatement(sqlForID);
        prepared.setDate(1, league.getStartDate());
        ResultSet resultSet = prepared.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
}



