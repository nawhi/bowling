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

    public int score() {
        return regularFrameTotal() + bonusFrameTotal();
    }

    private int bonusFrameTotal() {
        boolean haveBonusFrame = (frames.length == 11);
        if (haveBonusFrame)
            return totalFor(frames[frames.length - 1]);
        return 0;
    }

    private int regularFrameTotal() {
        int total = 0;
        for (int i = 0; i < 10; ++i) {
            var frame = frames[i];
            total += totalFor(frame);
            if (frame.equals(String.valueOf(STRIKE)) && i < 9) {
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
