package ir.maktab.sports.repository;

import ir.maktab.sports.data.League;
import ir.maktab.sports.repository.util.AppConstant;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeagueRepository {
    public void addFootballLeague(League league) throws SQLException {
        String sql = "INSERT INTO football_league";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
    }
    public void addVolleyballLeague(League league){

    }
}
