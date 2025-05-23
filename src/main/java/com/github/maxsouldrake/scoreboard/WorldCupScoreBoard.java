package com.github.maxsouldrake.scoreboard;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldCupScoreBoard implements ScoreBoard {
    private final Map<TeamPair, Game> games = new HashMap<>();

    @Override
    public void startGame(TeamPair teams) {
        if (games.containsKey(teams)) {
            throw new IllegalArgumentException("Game already started");
        }
        games.put(teams, new Game(teams));
    }

    @Override
    public void finishGame(TeamPair teams) {
        if (!games.containsKey(teams)) {
            throw new IllegalArgumentException("Game not found");
        }
        games.remove(teams);
    }

    @Override
    public void updateGameScore(TeamPair teams, int newHomeTeamScore, int newAwayTeamScore) {
        if (!games.containsKey(teams)) {
            throw new IllegalArgumentException("Game not found");
        }
        if (newHomeTeamScore < 0 || newAwayTeamScore < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }
        Game game = games.get(teams);
        game.setHomeTeamScore(newHomeTeamScore);
        game.setAwayTeamScore(newAwayTeamScore);
    }

    @Override
    public List<Game> getSummary() {
        return games.values()
                .stream()
                .sorted(Comparator.comparing(Game::totalScore)
                        .thenComparing(Game::getStartingTime)
                        .reversed())
                .toList();
    }
}
