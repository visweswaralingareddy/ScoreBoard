import java.util.*;

public class ScoreBoardOperations {

    public  Map<String, Match> scoreBoard = new HashMap<String, Match>();
    public  List<Match> summary = new LinkedList<>();
    public Map<String,Match> startGame(String home, String away) throws Exception {
        if(!Utilities.isNullOrEmpty(home) && !Utilities.isNullOrEmpty(away) ){
            Team homeTeam = new Team(home,true);
            Team awayTeam = new Team(away,false);
            Match newMatch = new Match(homeTeam,awayTeam,0,0,false);

            scoreBoard.put(Utilities.getKey(home,away),newMatch);
            summary.add(newMatch);

        }else{
            throw new Exception("Either of the parameters should not be null");
        }

        return scoreBoard;
    }

    public Map<String,Match> finishAGame(String home, String away) throws Exception {
        if(!Utilities.isNullOrEmpty(home) && !Utilities.isNullOrEmpty(away) ){
            String key = Utilities.getKey(home,away);
            Match match = scoreBoard.get(key);
            if(match!=null) {
                //update the match status in summary
                for(Match m : summary){
                    if(m.equals(match)){
                        m.setMatchCompleted(true);
                    }
                }
                //removing match from score board
                scoreBoard.remove(key);
            }else{
                throw new Exception("Can not find the match");
            }
        }else{
            throw new Exception("Either of the parameters should not be null");
        }
        return scoreBoard;
    }
    public Match updateScoreBoard(String homeTeam, int homeTeamScore, String awayTeam, int awayTeamScore) throws Exception {
        Match match = null;
        if(!Utilities.isNullOrEmpty(homeTeam) && !Utilities.isNullOrEmpty(awayTeam) ){
            String key = Utilities.getKey(homeTeam,awayTeam);
            match = scoreBoard.get(key);
            if(match != null){
                match.setHomeTeamScore(homeTeamScore);
                match.setAwayTeamScore(awayTeamScore);
                //update the score in summary
                for(Match m : summary){
                    if(m.equals(match)){
                        m.setHomeTeamScore(homeTeamScore);
                        m.setAwayTeamScore(awayTeamScore);
                    }
                }
                //update the score in score board
                scoreBoard.replace(key,match);
            }else {
                throw new Exception("Match not found for the given home team and away team combination");
            }
        }else {
            throw new Exception("Home team and away team parameters should not be null");
        }
        return match;
    }

    public List<Match> getSummary(){
        List<Match> orderedMatches = new ArrayList<>();
        List<Match> matchesWithDifferentScores = new LinkedList<>();
        for(Match match : summary){
            if(match.getHomeTeamScore()==match.getAwayTeamScore()){
                orderedMatches.add(match);
            }else{
                matchesWithDifferentScores.add(match);
            }
        }
        orderedMatches.addAll(matchesWithDifferentScores);
        return orderedMatches;
    }
}
