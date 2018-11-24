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

    public int score() {
        return regularFrameTotal();
    }

    private static List<String> splitToFrames(String scorecard) {
        String[] frames = scorecard
                .replaceAll(Pattern.quote("||"), "|")
                .split(Pattern.quote("|"));
        return asList(frames);
    }

    private int regularFrameTotal() {
        String allBalls = scorecard.replace("|", "");
        int numRegularBalls = scorecard.substring(0, scorecard.indexOf("||")).replace("|", "").length();
        int total = 0;
        for (int i = 0; i < numRegularBalls; ++i) {
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
