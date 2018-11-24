import java.util.stream.IntStream;

public class BowlingGame {
    public static final char STRIKE = 'X';
    private static final char MISS = '-';
    private static final char SPARE = '/';

    private final String balls;
    private final int numRegularBalls;

    public BowlingGame(String scorecard) {
        this.balls = scorecard.replace("|", "");
        this.numRegularBalls = getNumRegularBalls(scorecard);
    }

    public int score() {
        return IntStream.range(0, numRegularBalls)
                .map(this::scoreBallAt)
                .sum();
    }

    private int getNumRegularBalls(String scorecard) {
        return scorecard
                .substring(0, scorecard.indexOf("||"))
                .replace("|", "")
                .length();
    }

    private int scoreBallAt(int index) {
        char ball = balls.charAt(index);
        if (ball == STRIKE) {
            return scoreStrikeAt(index);
        } else if (ball == SPARE) {
            return scoreSpareAt(index);
        } else {
            return scoreRegularBallAt(index);
        }
    }

    private int scoreSpareAt(int ix) {
        int score = scoreRegularBallAt(ix);
        if (ix + 1 < balls.length()) {
            int nextBallScore = scoreRegularBallAt(ix + 1);
            score += nextBallScore;
        }
        return score;
    }

    private int scoreStrikeAt(int ix) {
        int score = scoreRegularBallAt(ix);
        if (ix + 2 < balls.length()) {
            int nextTwoScores = scoreRegularBallAt(ix + 1)
                    + scoreRegularBallAt(ix + 2);
            score += nextTwoScores;
        }
        return score;
    }

    private int scoreRegularBallAt(int ix) {
        char c = balls.charAt(ix);
        switch(c)
        {
            case STRIKE:
                return 10;
            case SPARE:
                return 10 - scoreRegularBallAt(ix - 1);
            case MISS:
                return 0;
            default:
                return Character.getNumericValue(c);
        }
    }
}
