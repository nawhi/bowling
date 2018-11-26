import java.util.stream.IntStream;

public class BowlingGame {
    private static final char STRIKE = 'X';
    private static final char MISS = '-';
    private static final char SPARE = '/';

    private final String allBalls;
    private final String regularBalls;

    public BowlingGame(String scorecard) {
        this.allBalls = allBallsFrom(scorecard);
        this.regularBalls = nonBonusBallsFrom(scorecard);
    }

    public int score() {
        return IntStream.range(0, regularBalls.length())
                .map(this::scoreBallAt)
                .sum();
    }

    private String allBallsFrom(String scorecard) {
        return scorecard.replace("|", "");
    }

    private String nonBonusBallsFrom(String scorecard) {
        int bonusIx = scorecard.indexOf("||");
        return allBallsFrom(scorecard.substring(0, bonusIx));
    }

    private int scoreBallAt(int index) {
        char ball = allBalls.charAt(index);
        if (ball == STRIKE) {
            return bonusScore(index, 2);
        } else if (ball == SPARE) {
            return bonusScore(index, 1);
        } else {
            return regularScore(index);
        }
    }

    private int bonusScore(int ix, int numBonuses) {
        int score = regularScore(ix);
        if (ix + numBonuses < allBalls.length()) {
            score += IntStream.range(1, numBonuses + 1)
                    .map(i -> regularScore(ix + i))
                    .sum();
        }
        return score;
    }

    private int regularScore(int ix) {
        char c = allBalls.charAt(ix);
        switch(c)
        {
            case STRIKE:
                return 10;
            case SPARE:
                return 10 - regularScore(ix - 1);
            case MISS:
                return 0;
            default:
                return Character.getNumericValue(c);
        }
    }
}
