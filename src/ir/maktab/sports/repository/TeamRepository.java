package ir.maktab.sports.repository;

import ir.maktab.sports.dao.enums.Sports;
import ir.maktab.sports.dao.team.FootballTeam;
import ir.maktab.sports.dao.team.Team;
import ir.maktab.sports.dao.team.VolleyballTeam;
import ir.maktab.sports.util.AppConstant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamRepository {
    public int addTeam(Team team) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO team(league_id,team_name) VALUES (?,?)";
        Connection connection = AppConstant.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, team.getLeagueID());
        preparedStatement.setString(2, team.getTeamName());
        preparedStatement.executeUpdate();

        String sqlID = "SELECT team_id FROM team WHERE team_name = ? AND league_id = ?";
        PreparedStatement prS = AppConstant.getConnection().prepareStatement(sqlID);
        prS.setString(1, team.getTeamName());
        prS.setInt(2,team.getLeagueID());
        ResultSet resultSet = prS.executeQuery();
        if (resultSet.next())
            result = resultSet.getInt(1);
        return result;
    }
    public boolean removeTeam(Team team) throws SQLException{
        String sql = "DELETE FROM team WHERE team_id = ? AND league_id = ?";
        Connection connection = AppConstant.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, team.getTeamID());
        preparedStatement.setInt(2,team.getLeagueID());
        return preparedStatement.executeUpdate() > 0;
    }
    public Team teamByID(int ID, Sports sportType) throws SQLException {
        Team team = null;
        String sql = "SELECT * FROM team WHERE team_id = ?";
        Connection connection = AppConstant.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, ID);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            int leagueID = resultSet.getInt(2);
            String name = resultSet.getString(3);
            int played = resultSet.getInt(4);
            int won = resultSet.getInt(5);
            int lost = resultSet.getInt(6);
            int points = resultSet.getInt(7);
            int goalsFor = resultSet.getInt(8);
            int goalsAgainst = resultSet.getInt(9);
            int drawn = resultSet.getInt(10);

            if(sportType == Sports.FOOTBALL) {
                team = new FootballTeam(id,leagueID,name,played,won,lost,points,drawn,goalsFor,goalsAgainst);
            }
            else if (sportType == Sports.VOLLEYBALL) {
                team = new VolleyballTeam(id,leagueID,name,played,won,lost,points,goalsFor,goalsAgainst);
            }
        }
        return team;
    }
    public List<Team> teamsByLeagueID(int leagueID,Sports sportType) throws SQLException {
        List<Team> teamList = new ArrayList<>();
        String sql = "SELECT * FROM team WHERE league_id = ?";
        Connection connection = AppConstant.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, leagueID);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            int ID = resultSet.getInt(2);
            String name = resultSet.getString(3);
            int played = resultSet.getInt(4);
            int won = resultSet.getInt(5);
            int lost = resultSet.getInt(6);
            int points = resultSet.getInt(7);
            int goalsFor = resultSet.getInt(8);
            int goalsAgainst = resultSet.getInt(9);
            int drawn = resultSet.getInt(10);

            Team team = null;
            if(sportType == Sports.FOOTBALL) {
                team = new FootballTeam(id,leagueID,name,played,won,lost,points,drawn,goalsFor,goalsAgainst);
            }
            else if (sportType == Sports.VOLLEYBALL) {
                team = new VolleyballTeam(id,leagueID,name,played,won,lost,points,goalsFor,goalsAgainst);
            }
            teamList.add(team);
        }
        return teamList;
    }
    public boolean updateTeam(Team team,Sports sportType) throws SQLException {
        String sql = "UPDATE team SET played = ?,won = ?,lost = ?,points = ?,goals_for = ?,goals_against = ?, drawn = ? WHERE team_id = ? ";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,team.getPlayed());
        preparedStatement.setInt(2,team.getWon());
        preparedStatement.setInt(3,team.getLost());
        preparedStatement.setInt(4,team.getPoints());
        if(sportType == Sports.FOOTBALL) {
            preparedStatement.setInt(5, ((FootballTeam) team).getGoalsFor());
            preparedStatement.setInt(6, ((FootballTeam) team).getGoalsAgainst());
            preparedStatement.setInt(7, ((FootballTeam) team).getDrawn());
        } else if (sportType == Sports.VOLLEYBALL) {
            preparedStatement.setInt(5,((VolleyballTeam) team).getPoans());
            preparedStatement.setInt(6,((VolleyballTeam) team).getScoreSets());
            preparedStatement.setInt(7,0);
        }
        preparedStatement.setInt(8,team.getTeamID());

        return preparedStatement.executeUpdate() > 0;
    }

}
