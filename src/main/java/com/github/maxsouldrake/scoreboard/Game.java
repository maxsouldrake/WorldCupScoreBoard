package com.github.maxsouldrake.scoreboard;

import java.time.Instant;
import java.util.Objects;

public class Game {
    private final TeamPair teams;
    private final Instant startingTime;
    private int homeTeamScore;
    private int awayTeamScore;

    public Game(TeamPair teams) {
        this.teams = teams;
        this.startingTime = Instant.now();
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
    }

    public int totalScore() {
        return homeTeamScore + awayTeamScore;
    }

    public TeamPair getTeams() {
        return teams;
    }

    public Instant getStartingTime() {
        return startingTime;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public String display() {
        return teams.homeTeamName() + " " + homeTeamScore + " - " + teams.awayTeamName() + " " + awayTeamScore;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Game game)) return false;
        return Objects.equals(teams, game.teams) && Objects.equals(startingTime, game.startingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teams, startingTime);
    }
}
