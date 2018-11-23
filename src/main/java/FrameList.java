import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

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
        return totalFor(bonusBalls);
    }

    private int regularFrameTotal() {

        String regularBalls = scorecard.substring(0, scorecard.indexOf("||")).replace("|", "");
        int total = 0;
        for (int i = 0; i < regularBalls.length(); ++i) {
            char ball = regularBalls.charAt(i);
            total += ballScore(ball);
            if (regularBalls.charAt(i) == STRIKE) {
                if (i + 1 < regularBalls.length())
                    total += ballScore(regularBalls.charAt(i + 1));
                if (i + 2 < regularBalls.length())
                    total += ballScore(regularBalls.charAt(i + 2));
            }
        }
        return total;
//
//        int total = 0;
//        for (int i = 0; i < 10; ++i) {
//            var frame = frames.get(i);
//            total += totalFor(frame);
//            if (frame.equals(String.valueOf(STRIKE)) && i < 9) {
//                total += totalFor(frames.get(i+1));
//            }
//        }
//        return total;
    }

    private int totalFor(String frame) {
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
