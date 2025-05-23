package com.github.maxsouldrake.scoreboard;

import java.util.List;

public interface ScoreBoard {
    void startGame(TeamPair teams);
    void finishGame(TeamPair teams);
    void updateGameScore(TeamPair teams, int newHomeTeamScore, int newAwayTeamScore);
    List<Game> getSummary();
}
