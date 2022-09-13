package ir.maktab.sports.repository;

import ir.maktab.sports.dao.League;
import ir.maktab.sports.dao.enums.Sports;
import ir.maktab.sports.util.AppConstant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeagueRepository {
    public int addLeague(League league) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO league (league_name,start_date,sport_type) VALUES (?,?,?)";
        Connection connection = AppConstant.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,league.getLeagueName());
        preparedStatement.setDate(2,league.getStartDate());
        preparedStatement.setString(3,String.valueOf(league.getSportType()));
        preparedStatement.executeUpdate();

        String sqlForID = "SELECT league_id FROM league WHERE league_name = ?";
        PreparedStatement prepared = AppConstant.getConnection().prepareStatement(sqlForID);
        prepared.setString(1, league.getLeagueName());
        ResultSet resultSet = prepared.executeQuery();
        if (resultSet.next()) {
            result = resultSet.getInt(1);
        }
        return result;

    }
    public List<League> getAllLeagues(Sports sports) throws SQLException {
        String sql = "SELECT league_name, start_date FROM league WHERE sport_type = ?";
        Connection connection = AppConstant.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,sports.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        List <League> leagueList = new ArrayList<>();
        while (resultSet.next()){
            String name = resultSet.getString(1);
            Date date = resultSet.getDate(2);
            League league = new League(date,name,sports);
            leagueList.add(league);
        }
        return leagueList;
    }
    public League findLeagueByName(String leagueName) throws SQLException {
        League result = null;
        String sql = "SELECT * FROM league WHERE league_name = ?";
        Connection connection = AppConstant.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,leagueName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            int league_id = resultSet.getInt(1);
            String league_name = resultSet.getString(2);
            Date startDate =resultSet.getDate(3);
            Sports sportType = Sports.valueOf(resultSet.getString(4));
            result = new League(league_id,startDate,leagueName,sportType);
        }
        return result;
    }
}
