import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

public class FrameList {
    public static final char STRIKE = 'X';
    private static final char MISS = '-';

    private final List<String> frames;


    public FrameList(String scorecard) {
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
        boolean haveBonusFrame = (frames.size() == 11);
        if (haveBonusFrame)
            return totalFor(frames.get(frames.size() - 1));
        return 0;
    }

    private int regularFrameTotal() {
        int total = 0;
        for (int i = 0; i < 10; ++i) {
            var frame = frames.get(i);
            total += totalFor(frame);
            if (frame.equals(String.valueOf(STRIKE)) && i < 9) {
                total += totalFor(frames.get(i+1));
            }
        }
        return total;
    }

    private int totalFor(String frame) {
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
