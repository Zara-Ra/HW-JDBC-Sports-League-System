package ir.maktab.sports.repository.team;

import ir.maktab.sports.data.League;
import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.team.FootballTeam;
import ir.maktab.sports.data.team.Team;
import ir.maktab.sports.util.AppConstant;

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
        String sql = "DELETE FROM football_team WHERE team_id = ? AND league_id = ?";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, team.getTeamID());
        preparedStatement.setInt(2,team.getLeagueID());
        if (preparedStatement.executeUpdate() != 0)
            return true;
        return false;
    }

    @Override
    public Team teamInfoByID(int ID) throws SQLException {
        String sql = "SELECT * FROM football_team WHERE team_id = ?";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int played = resultSet.getInt(3);
            int won = resultSet.getInt(4);
            int drawn = resultSet.getInt(5);
            int lost = resultSet.getInt(6);
            int goalsFor = resultSet.getInt(7);
            int goalsAgainst = resultSet.getInt(8);
            int points = resultSet.getInt(9);
            int leagueID = resultSet.getInt(10);

            Team team = new FootballTeam(name, played, won, lost, points, drawn, goalsFor, goalsAgainst);
            team.setTeamID(id);
            team.setLeagueID(leagueID);
            return team;
        }
        return null;
    }

    @Override
    public boolean updateTeam(Team team) throws SQLException {
        String sql = "UPDATE football_team SET played = ?,won = ?, drawn = ?,lost = ?,goals_for = ?,goals_against = ?,points = ? WHERE team_id = ? ";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,team.getPlayed());
        preparedStatement.setInt(2,team.getWon());
        preparedStatement.setInt(3,((FootballTeam) team).getDrawn());
        preparedStatement.setInt(4,team.getLost());
        preparedStatement.setInt(5,((FootballTeam) team).getGoalsFor());
        preparedStatement.setInt(6,((FootballTeam) team).getGoalsAgainst());
        preparedStatement.setInt(7,team.getPoints());
        preparedStatement.setInt(8,team.getTeamID());

        if (preparedStatement.executeUpdate() != 0)
            return true;
        return false;

    }

    @Override
    public int addMatch(Match match) throws SQLException {
        String sql = "INSERT INTO football_match (home_team_id,away_team_id,league_id) VALUES (?,?,?)";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, match.getHomeTeamID());
        preparedStatement.setInt(2, match.getAwayTeamID());
        preparedStatement.setInt(3, match.getLeagueID());
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
    public boolean updateMatch(Match match) throws SQLException {
        String sql = "UPDATE football_match SET home_team_points = ?,away_team_points = ?,home_team_score=?,away_team_score=? WHERE home_team_id = ? AND away_team_id = ? AND league_id = ?";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,match.getHomeTeamPoints() );
        preparedStatement.setInt(2, match.getAwayTeamPoints());
        preparedStatement.setInt(3, match.getHomeTeamScore());
        preparedStatement.setInt(4, match.getAwayTeamScore());
        preparedStatement.setInt(5, match.getHomeTeamID());
        preparedStatement.setInt(6, match.getAwayTeamID());
        preparedStatement.setInt(7, match.getLeagueID());
        if (preparedStatement.executeUpdate() != 0)
            return true;
        return false;
    }

    @Override
    public int addLeague(League league) throws SQLException {
        String sql = "INSERT INTO football_league (league_name,team1,team2,team3,team4,team5,team6,team7,team8,startdate) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        List<Team> teamList = league.getTeamList();
        preparedStatement.setString(1,league.getLeagueName());
        for (int i = 0; i < 8; i++) {
            preparedStatement.setInt(i + 2, teamList.get(i).getTeamID());
        }
        preparedStatement.setDate(10, league.getStartDate());
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

    @Override
    public boolean setTeamsLeagueID(Team team, int leagueID) throws SQLException {
        String sql = "UPDATE football_team SET league_id = ? WHERE team_id = ?";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, leagueID);
        preparedStatement.setInt(2, team.getTeamID());
        if (preparedStatement.executeUpdate() != 0)
            return true;
        return false;
    }

    @Override
    public boolean updateLeague(Team team) throws SQLException {
        String sql = "SELECT * FROM football_league WHERE league_id = ?";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, team.getLeagueID());
        ResultSet resultSet = preparedStatement.executeQuery();
        int[] teamIDList = new int[8];
        if (resultSet.next()) {
            teamIDList[0] = resultSet.getInt(2);
            teamIDList[1] = resultSet.getInt(3);
            teamIDList[2] = resultSet.getInt(4);
            teamIDList[3] = resultSet.getInt(5);
            teamIDList[4] = resultSet.getInt(6);
            teamIDList[5] = resultSet.getInt(7);
            teamIDList[6] = resultSet.getInt(8);
            teamIDList[7] = resultSet.getInt(9);
        }
        int emptyTeam = -1;
        for (int i = 0; i < 8; i++) {
            if(teamIDList[i] == 0) {
                emptyTeam = i;
                break;
            }
        }
        if(emptyTeam == -1)
            return false;
        emptyTeam++;
        String updatesql = "UPDATE football_league SET team"+emptyTeam+" = ? WHERE league_id = ?";
        PreparedStatement preparedStatement1 = AppConstant.getConnection().prepareStatement(updatesql);
        preparedStatement1.setInt(1, team.getTeamID());
        preparedStatement1.setInt(2, team.getLeagueID());
        if (preparedStatement1.executeUpdate() != 0)
            return true;
        return false;
    }

    @Override
    public String[] showAllLeague() throws SQLException {
        String sql = "SELECT count(league_name) FROM football_league";
        PreparedStatement preparedStatement = AppConstant.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        int numOfLeague = 0;
        if (resultSet.next()){
            numOfLeague = resultSet.getInt(1);
        }
        String query = "SELECT league_name FROM football_league";
        PreparedStatement preparedStatement1 = AppConstant.getConnection().prepareStatement(query);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        String [] leagueNames = new String[numOfLeague];
        int i = 0;
        while (resultSet1.next()){
            leagueNames[i] = resultSet1.getString(1);
            i++;
        }
        return leagueNames;
    }

}



