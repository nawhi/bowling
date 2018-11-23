import java.util.Arrays;
import java.util.regex.Pattern;

public class BowlingGame {

    public static final char STRIKE = 'X';

    public int calculate(String scorecard) {
        var frames = splitToFrames(scorecard);
        if (hasBonusFrame(frames))
            return getTotal(Arrays.copyOfRange(frames, 0, 10)) + getBonusTotal(frames);
        return getTotal(frames);
    }

    private boolean hasBonusFrame(String[] frames) {
        return (frames.length == 11);
    }

    private int getBonusTotal(String[] frames) {
        return totalFor(frames[frames.length - 1]);
    }

    private String[] splitToFrames(String scorecard) {
        return scorecard
                .replaceAll(Pattern.quote("||"), "|")
                .split(Pattern.quote("|"));
    }

    private int getTotal(String[] frames) {
        int total = 0;
        for (int i = 0; i < frames.length; ++i) {
            var frame = frames[i];
            total += totalFor(frame);
            if (frame.equals(String.valueOf(STRIKE)) && i < frames.length - 1) {
                total += totalFor(frames[i+1]);
            }
        }
        return total;
    }

    private int totalFor(String frame) {
        int total = 0;
        for (char c: frame.toCharArray()) {
            if (c == STRIKE) {
                total += 10;
            } else if (Character.isDigit(c)) {
                total += Integer.parseInt(String.valueOf(c));
            }
        }
        return total;
    }
}
