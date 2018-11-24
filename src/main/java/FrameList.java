import java.util.stream.IntStream;

public class FrameList {
    public static final char STRIKE = 'X';
    private static final char MISS = '-';
    private static final char SPARE = '/';

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
        char ball = balls.charAt(ballIndex);
        if (ball == STRIKE) {
            return scoreStrikeAt(ballIndex);
        } else if (ball == SPARE) {
            return scoreSpareAt(ballIndex);
        } else {
            return scoreRegularBallAt(ball);
        }
    }

    private int scoreSpareAt(int ballIndex) {
        if (ballIndex + 1 < balls.length()) {
            return 10 - scoreRegularBallAt(balls.charAt(ballIndex - 1))
                    + scoreRegularBallAt(balls.charAt(ballIndex + 1));
        }
        return 10 - scoreRegularBallAt(balls.charAt(ballIndex - 1));
    }

    private int scoreStrikeAt(int ballIndex) {
        if (ballIndex + 2 < balls.length()) {
            return scoreRegularBallAt(balls.charAt(ballIndex))
                    + scoreRegularBallAt(balls.charAt(ballIndex + 1))
                    + scoreRegularBallAt(balls.charAt(ballIndex + 2));
        }
        return scoreRegularBallAt(balls.charAt(ballIndex));
    }

    private int getNumRegularBalls() {
        return scorecard
                .substring(0, scorecard.indexOf("||"))
                .replace("|", "")
                .length();
    }

    private int scoreRegularBallAt(char c) {
        if (c == STRIKE) {
            return 10;
        } else if (c == MISS) {
            return 0;
        } else {
            return Character.getNumericValue(c);
        }
    }
}
