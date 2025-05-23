package com.github.maxsouldrake.scoreboard;

public record TeamPair(String homeTeamName, String awayTeamName) {
    public static TeamPair of(String homeTeamName, String awayTeamName) {
        if (homeTeamName == null || homeTeamName.isEmpty() || awayTeamName == null || awayTeamName.isEmpty()) {
            throw new IllegalArgumentException("Teams must have not empty names");
        }
        return new TeamPair(homeTeamName, awayTeamName);
    }
}
