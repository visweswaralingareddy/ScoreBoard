import junit.framework.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(JUnit4ClassRunner.class)
public class ScoreBoardTests {
    ScoreBoardOperations sbo= new ScoreBoardOperations();
    @Test
    public void testStartGame() throws Exception {

        String home = "England";
        String away = "Netherlands";
        String key = Utilities.getKey(home,away);

        Match newMatch = new Match(new Team(home,true),new Team(away,false),0,0,false);
        Map<String, Match> expectedScoreBoard = new HashMap<String, Match>();
        expectedScoreBoard.put(key,newMatch);

        Map<String, Match> actualScoreBoard = sbo.startGame(home,away);
        //check if score board returns same size
        Assert.assertEquals(expectedScoreBoard.size(),actualScoreBoard.size());
        //check if score board returns actual object
        Assert.assertEquals(newMatch,actualScoreBoard.get(key));

        //check by modifying values to expected output
        newMatch.setHomeTeamScore(1);
        Assert.assertNotSame(newMatch,actualScoreBoard.get(key));
    }

    @Test(expected = Exception.class)
    public void testExceptionForStartGame() throws Exception {
        sbo.startGame("","Russia");
    }

    @Test
    public void testFinishAGame() throws Exception {
        sbo.startGame("England","Netherlands");
        Assert.assertEquals(0,sbo.finishAGame("England","Netherlands").size());

        //When a match not found for the input
        try{
            sbo.finishAGame("Norway","England");
        }catch(Exception e){
            Assert.assertTrue("Can not find the match".contains(e.getMessage()));
        }

        //when null passed as input
        try{
            sbo.finishAGame(null,"England");
        }catch(Exception e){
            Assert.assertTrue("Either of the parameters should not be null".contains(e.getMessage()));
        }
    }

    @Test(expected = Exception.class)
    public void testExceptionForFinishAGame() throws Exception {
        Assert.assertNotSame(1,sbo.finishAGame("Norway","England"));
    }

    @Test
    public void testUpdateScore() throws Exception {
        Match newMatch = new Match(new Team("England",true),new Team("Netherlands",false),0,0,false);
        //Start a game
        sbo.startGame("England","Netherlands");
        newMatch.setHomeTeamScore(6);
        newMatch.setAwayTeamScore(4);
        Match actualUpdateMatchScore = sbo.updateScoreBoard("England",6,"Netherlands",4);
        Assert.assertEquals(newMatch,actualUpdateMatchScore);

        //when a match is not found for home and away team combinations
        try{
            sbo.updateScoreBoard("Greenland",6,"Netherlands",4);
        }catch (Exception e){
            Assert.assertTrue("Match not found for the given home team and away team combination".contains(e.getMessage()));
        }

        //when null passed as input
        try{
            sbo.updateScoreBoard(null, 5,"England", 8);
        }catch(Exception e){
            Assert.assertTrue("Home team and away team parameters should not be null".contains(e.getMessage()));
        }
    }

    @Test
    public void testSummary() throws Exception {
        Match match1 = new Match(new Team("England",true),new Team("Netherlands",false),6,4,false);
        Match match2 = new Match(new Team("Denmark",true),new Team("Norway",false),3,2,false);
        Match match3 = new Match(new Team("Sweden",true),new Team("Poland",false),3,3,false);
        List<Match> expectedMatchSummary = new ArrayList<>();
        expectedMatchSummary.add(match3);
        expectedMatchSummary.add(match2);
        expectedMatchSummary.add(match1);

        sbo.startGame("England","Netherlands");
        sbo.updateScoreBoard("England",6,"Netherlands",4);
        sbo.startGame("Denmark","Norway");
        sbo.updateScoreBoard("Denmark",3,"Norway",2);
        sbo.startGame("Sweden","Poland");
        sbo.updateScoreBoard("Sweden",3,"Poland",3);
        List<Match> actualSummary = sbo.getSummary();

        Assert.assertEquals(expectedMatchSummary.get(0),actualSummary.get(0));
        Assert.assertEquals(expectedMatchSummary.get(1),actualSummary.get(1));
        Assert.assertEquals(expectedMatchSummary.get(2),actualSummary.get(2));
    }

}
