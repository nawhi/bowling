public class BowlingGame {
    public int calculate(String scorecard) {
        int total = 0;
        for (char c: scorecard.toCharArray()) {
            if (Character.isDigit(c))
                total += Integer.parseInt(String.valueOf(c));
        }
        return total;
    }
}
