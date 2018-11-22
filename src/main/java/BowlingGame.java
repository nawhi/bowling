import java.util.regex.Pattern;

public class BowlingGame {

    public static final char STRIKE = 'X';

    public int calculate(String scorecard) {
        var frames = scorecard.split(Pattern.quote("|"));
        return getTotal(frames);
    }

    private int getTotal(String[] frames) {
        int total = 0;
        for (int i = 0; i < frames.length; ++i) {
            var frame = frames[i];
            total += totalFor(frame);
            if (frame.equals(String.valueOf(STRIKE))) {
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
