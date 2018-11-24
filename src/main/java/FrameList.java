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
            return scoreRegularBall(ball);
        }
    }

    private int scoreSpareAt(int ballIndex) {
        if (ballIndex + 1 < balls.length()) {
            int thisBallScore = 10 - scoreRegularBall(balls.charAt(ballIndex - 1));
            int spareBonus = scoreRegularBall(balls.charAt(ballIndex + 1));
            return thisBallScore + spareBonus;
        }
        return 10 - scoreRegularBall(balls.charAt(ballIndex - 1));
    }

    private int scoreStrikeAt(int ballIndex) {
        if (ballIndex + 2 < balls.length()) {
            return scoreRegularBall(balls.charAt(ballIndex))
                    + scoreRegularBall(balls.charAt(ballIndex + 1))
                    + scoreRegularBall(balls.charAt(ballIndex + 2));
        }
        return scoreRegularBall(balls.charAt(ballIndex));
    }

    private int getNumRegularBalls() {
        return scorecard
                .substring(0, scorecard.indexOf("||"))
                .replace("|", "")
                .length();
    }

    private int scoreRegularBall(char c) {
        if (c == STRIKE) {
            return 10;
        } else if (c == MISS) {
            return 0;
        } else {
            return Character.getNumericValue(c);
        }
    }
}
