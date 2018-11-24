import java.util.stream.IntStream;

public class FrameList {
    public static final char STRIKE = 'X';
    private static final char MISS = '-';

    private final String scorecard;
    private final String balls;
    private final int numRegularBalls;

    public FrameList(String scorecard) {
        this.scorecard = scorecard;
        this.balls = scorecard.replace("|", "");
        this.numRegularBalls = getNumRegularBalls();
    }

    public int score() {
        return IntStream.range(0, numRegularBalls)
                .map(this::scoreBallAt)
                .sum();
    }

    private int scoreBallAt(int ballIndex) {
        int total = 0;
        char ball = balls.charAt(ballIndex);
        total += ballScore(ball);
        if (ball == STRIKE) {
            total += scoreStrikeAt(ballIndex);
        }
        return total;
    }

    private int scoreStrikeAt(int ballIndex) {
        return ballScore(balls.charAt(ballIndex + 1))
                + ballScore(balls.charAt(ballIndex + 2));
    }

    private int getNumRegularBalls() {
        return scorecard
                .substring(0, scorecard.indexOf("||"))
                .replace("|", "")
                .length();
    }

    private int ballScore(char c) {
        if (c == STRIKE) {
            return 10;
        } else if (c == MISS) {
            return 0;
        } else {
            return Character.getNumericValue(c);
        }
    }
}
