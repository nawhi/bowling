import java.util.Arrays;
import java.util.regex.Pattern;

public class FrameList {
    public static final char STRIKE = 'X';

    private final String[] frames;



    public FrameList(String scorecard) {
        this.frames = splitToFrames(scorecard);
    }

    private static String[] splitToFrames(String scorecard) {
        return scorecard
                .replaceAll(Pattern.quote("||"), "|")
                .split(Pattern.quote("|"));
    }

    public boolean haveBonusFrame() {
        return (frames.length == 11);
    }

    public int score() {
        if (haveBonusFrame()) {
            String[] regularFrames = Arrays.copyOfRange(frames, 0, 10);
            return getTotal(regularFrames) + getBonusTotal();
        }
        return getTotal(frames);
    }

    private int getBonusTotal() {
        return totalFor(frames[frames.length - 1]);
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
