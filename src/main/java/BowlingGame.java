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
            return bonusScore(index, 2);
        } else if (ball == SPARE) {
            return bonusScore(index, 1);
        } else {
            return regularScore(index);
        }
    }

    private int bonusScore(int ix, int numBonuses) {
        int score = regularScore(ix);
        if (ix + numBonuses < balls.length()) {
            for (int j = 1; j <= numBonuses; j++) {
                score += regularScore(ix + j);
            }
        }
        return score;
    }

    private int regularScore(int ix) {
        char c = balls.charAt(ix);
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
