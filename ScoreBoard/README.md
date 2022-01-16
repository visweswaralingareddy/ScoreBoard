Start Game:
-I considered two input parameters(home, away teams) for this method.
-I used map for keeping score borad of all the matches where key will comibination of home team and away team with lower cases. So that we can fetch a match 
easily for updating it or removing it from the score board.
-I'm keeping summary updated all the time when a new game is started or existing game score is updated, so that if any game is removed from score board 
  still I'll have complete match summary.


Finish Game:
-I considered home team and away team as input parameters to identify the exact match which needds to be removed from the score board.
-First I fetched macth object from score board map using the key.
-Then I iterated  a game is removed from the score board then summary will also be updated the match as it is completed.
-Then I fetched the summary list until I found the same object as match I fetched from the score board and updated it as completed.
-As a last step I removed the match object from score board map.


Update Score:
-When score of any game is being updated, that will also be updated in the summary.
-I considered home team and it's score and away team and it's score as parameters as I need some variable to find the exact match for updating the score.
-I iterated the summary list until I found the same object as match I fetched from the score board and updated it. So that the summary will have updated scores.
-After updating the summary then scores are being inside the match object I fetched from score board.

Summary:
-As per my understanding, summary should give complete report about all the games and they should be fetched in the following order
  1. Matches with same score
  2. Matches recently added
-So I used array list to return summary of the matches as it preserves the insertion order and quick in fetching.
-And I seperated the matches with same score and the matches with different scores in different lists, later I iterated the list having matches with different scores 
  in reverse order(as the matches which got added recently will be at the bottom of the list) and added them to the list having matches with same scores.
