package com.github.maxsouldrake.scoreboard

import spock.lang.Specification

class WorldCupScoreBoardTest extends Specification {
    ScoreBoard scoreBoard

    def setup() {
        scoreBoard = new WorldCupScoreBoard()
    }

    def "should start game successfully"() {
        given:
            def teams = TeamPair.of("Mexico", "Canada")
        when:
            scoreBoard.startGame(teams)
        then:
            scoreBoard.getSummary().size() == 1
    }

    def "should not duplicate game"() {
        given:
            def teams = TeamPair.of("Mexico", "Canada")
        when:
            scoreBoard.startGame(teams)
            scoreBoard.startGame(teams)
        then:
            def ex = thrown(IllegalArgumentException)
            ex.message == "Game already started"
    }

    def "should update game score"() {
        given:
            def teams = TeamPair.of("Mexico", "Canada")
        when:
            scoreBoard.startGame(teams)
            scoreBoard.updateGameScore(teams, 2, 3)
        then:
            with(scoreBoard.getSummary().get(0)) {
                homeTeamScore == 2
                awayTeamScore == 3
                totalScore() == 5
            }
    }

    def "should not update score if game doesn't exist"() {
        given:
            def teams = TeamPair.of("Mexico", "Canada")
        when:
            scoreBoard.updateGameScore(teams, 2, 3)
        then:
            def ex = thrown(IllegalArgumentException)
            ex.message == "Game not found"
    }

    def "should finish game successfully"() {
        given:
            def teams = TeamPair.of("Mexico", "Canada")
        when:
            scoreBoard.startGame(teams)
            scoreBoard.finishGame(teams)
        then:
            scoreBoard.getSummary().isEmpty()
    }

    def "should not finish game if game doesn't exist"() {
        given:
            def teams = TeamPair.of("Mexico", "Canada")
        when:
            scoreBoard.finishGame(teams)
        then:
            def ex = thrown(IllegalArgumentException)
            ex.message == "Game not found"
    }

    def "should not allow negative scores"() {
        given:
            def teams = TeamPair.of("Mexico", "Canada")
        when:
            scoreBoard.startGame(teams)
            scoreBoard.updateGameScore(teams, -1, -2)
        then:
            def ex = thrown(IllegalArgumentException)
            ex.message == "Score cannot be negative"
    }

    def "should return sorted summary"() {
        given:
            def teams1 = TeamPair.of("Mexico", "Canada")
            def teams2 = TeamPair.of("Spain", "Brazil")
            def teams3 = TeamPair.of("Germany", "France")
            def teams4 = TeamPair.of("Uruguay", "Italy")
            def teams5 = TeamPair.of("Argentina", "Australia")
        when:
            scoreBoard.startGame(teams1)
            scoreBoard.updateGameScore(teams1, 0, 2)
            scoreBoard.startGame(teams2)
            scoreBoard.updateGameScore(teams1, 0, 5)
            scoreBoard.updateGameScore(teams2, 10, 2)
            scoreBoard.startGame(teams3)
            scoreBoard.updateGameScore(teams3, 2, 2)
            scoreBoard.startGame(teams4)
            scoreBoard.updateGameScore(teams4, 6, 6)
            scoreBoard.startGame(teams5)
            scoreBoard.updateGameScore(teams5, 3, 1)
        then:
            def summary = scoreBoard.getSummary()
            with(scoreBoard.getSummary()) {
                get(0).display() == "Uruguay 6 - Italy 6"
                get(1).display() == "Spain 10 - Brazil 2"
                get(2).display() == "Mexico 0 - Canada 5"
                get(3).display() == "Argentina 3 - Australia 1"
                get(4).display() == "Germany 2 - France 2"
            }
    }
}
