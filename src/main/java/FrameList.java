public class FrameList {
    public static final char STRIKE = 'X';
    private static final char MISS = '-';

    private String scorecard;

    public FrameList(String scorecard) {
        this.scorecard = scorecard;
    }

    public int score() {
        String allBalls = scorecard.replace("|", "");
        int total = 0;
        for (int i = 0; i < getNumRegularBalls(); ++i) {
            char ball = allBalls.charAt(i);
            total += ballScore(ball);
            if (allBalls.charAt(i) == STRIKE) {
                for (var j = 1; j < 3; ++j) {
                    total += ballScore(allBalls.charAt(i + j));
                }
            }
        }
        return total;
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
