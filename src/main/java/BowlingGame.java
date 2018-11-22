import java.util.regex.Pattern;

public class BowlingGame {
    public int calculate(String scorecard) {
        int total = 0;
        var frames = scorecard.split(Pattern.quote("|"));
        for (String frame: frames) {
            for (char c: frame.toCharArray()) {
                if (c == 'X') {
                    total += 10;
                } else if (Character.isDigit(c)) {
                    total += Integer.parseInt(String.valueOf(c));
                }
            }
        }
        return total;
    }
}
