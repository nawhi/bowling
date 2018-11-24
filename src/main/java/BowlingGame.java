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

    private int scoreBallAt(int ballIndex) {
        char ball = balls.charAt(ballIndex);
        if (ball == STRIKE) {
            return scoreStrikeAt(ballIndex);
        } else if (ball == SPARE) {
            return scoreSpareAt(ballIndex);
        } else {
            return scoreRegularBallAt(ballIndex);
        }
    }

    private int scoreSpareAt(int ballIndex) {
        int thisBallScore = scoreRegularBallAt(ballIndex);
        boolean haveNextBall = ballIndex + 1 < balls.length();
        if (haveNextBall) {
            int nextBallScore = scoreRegularBallAt(ballIndex + 1);
            return thisBallScore + nextBallScore;
        } else {
            return thisBallScore;
        }
    }

    private int scoreStrikeAt(int ballIndex) {
        int thisScore = 10;
        boolean haveTwoMoreBalls = ballIndex + 2 < balls.length();
        if (haveTwoMoreBalls) {
            int nextTwoScores = scoreRegularBallAt(ballIndex + 1)
                    + scoreRegularBallAt(ballIndex + 2);
            return thisScore + nextTwoScores;
        } else {
            return thisScore;
        }
    }

    private int scoreRegularBallAt(int ballIndex) {
        char c = balls.charAt(ballIndex);
        switch(c)
        {
            case STRIKE:
                return 10;
            case SPARE:
                return 10 - scoreRegularBallAt(ballIndex - 1);
            case MISS:
                return 0;
            default:
                return Character.getNumericValue(c);
        }
    }
}
