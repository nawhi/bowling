public class FrameList {
    public static final char STRIKE = 'X';
    private static final char MISS = '-';
    private final String balls;

    private String scorecard;

    public FrameList(String scorecard) {
        this.scorecard = scorecard;
        this.balls = scorecard.replace("|", "");
    }

    public int score() {
        int total = 0;
        for (int i = 0; i < getNumRegularBalls(); ++i) {
            char ball = balls.charAt(i);
            total += ballScore(ball);
            if (ball == STRIKE) {
                total += scoreStrikeAt(i);
            }
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
