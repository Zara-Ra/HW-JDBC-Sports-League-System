package ir.maktab.sports.data.leagues;

import ir.maktab.sports.data.Match;
import ir.maktab.sports.data.team.Team;
import ir.maktab.sports.service.LeagueService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public  class League {
    private List<Team> teamList = new ArrayList<>();
    private List<Match> matchList = new ArrayList<>();
    private LeagueService leagueService;

   /* public League(List<Team> teamList, List<Match> matchList) {
        this.teamList = teamList;
        this.matchList = matchList;
    }*/

    public void setLeaugeService(LeagueService leaugeService) {
        this.leagueService = leaugeService;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public void teamInfo(Team team){
        leagueService.TeamInfo(team);
    }

    public void addTeam(Team team){
        if(leagueService.addTeam(team))
            teamList.add(team);
    }

    public void deleteTeam(Team team){
        if(leagueService.deleteTeam(team))
            teamList.remove(team);
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }

    public boolean addMatch(Match match) throws SQLException {
        if(leagueService.addMatch(match)) {
            matchList.add(match);
            return true;
        }
        return false;
    }
    public void ranking(){
        leagueService.rankingTable();
    }
}
