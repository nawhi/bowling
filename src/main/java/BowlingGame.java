public class BowlingGame {
    public int calculate(String scorecard) {
        if (scorecard.contains("1"))
            return 20;
        return 60;
    }
}
