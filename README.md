# Football World Cup Score Board

## Overview

Simple Football World Cup Score Board library in Java 21. It allows:

- Start a game (with initial score 0-0)
- Finish a game (remove it from the scoreboard)
- Update the score of a running game
- Get a summary of games sorted by total score (descending) and recency

## Implementation Notes

- **Spock framework** was used for testing instead of **JUnit**. I assumed it's acceptable for test task (and when I have choice I prefer spock to junit).
- I decided **not to add interfaces or factories** for the `Game` class since all games follow the same structure (two teams and scores). Keeping it simple and focused felt appropriate here.
- Wasn't sure if I should implement thread safety for the scoreboard, but eventually decided keep it simple and not toSince it’s a simple and left out concurrency.
- For simplicity didn't add extra dependencies (like lombok or apache commons)

