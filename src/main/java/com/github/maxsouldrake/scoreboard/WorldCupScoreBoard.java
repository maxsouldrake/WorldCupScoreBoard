package com.github.maxsouldrake.scoreboard;

import java.util.List;

public class WorldCupScoreBoard implements ScoreBoard {
    @Override
    public void startGame(TeamPair teams) {}

    @Override
    public void finishGame(TeamPair teams) {}

    @Override
    public void updateGameScore(TeamPair teams, int newHomeTeamScore, int newAwayTeamScore) {}

    @Override
    public List<Game> getSummary() {
        return null;
    }
}
