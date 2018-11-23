import java.util.List;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

public class FrameList {
    public static final char STRIKE = 'X';
    private static final char MISS = '-';

    private final List<String> frames;
    private String scorecard;


    public FrameList(String scorecard) {
        this.scorecard = scorecard;
        this.frames = splitToFrames(scorecard);
    }

    private static List<String> splitToFrames(String scorecard) {
        return asList(scorecard
                .replaceAll(Pattern.quote("||"), "|")
                .split(Pattern.quote("|")));
    }

    public int score() {
        return regularFrameTotal() + bonusFrameTotal();
    }

    private int bonusFrameTotal() {
        String bonusBalls = scorecard.substring(scorecard.indexOf("||")).replace("|", "");
        return bonusBallTotal(bonusBalls);
    }

    private int regularFrameTotal() {
        String regularBalls = scorecard.substring(0, scorecard.indexOf("||")).replace("|", "");
        int total = 0;
        for (int i = 0; i < regularBalls.length(); ++i) {
            char ball = regularBalls.charAt(i);
            total += ballScore(ball);
            if (regularBalls.charAt(i) == STRIKE) {
                for (var j = 1; j < 3; ++j) {
                    if (i + j < regularBalls.length()) {
                        total += ballScore(regularBalls.charAt(i + j));
                    }
                }
            }
        }
        return total;
    }

    private int bonusBallTotal(String frame) {
        // TODO: stream not for-loop
        int total = 0;
        for (char c: frame.toCharArray()) {
            total += ballScore(c);
        }
        return total;
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
