public class BowlingGame {

    public static final char STRIKE = 'X';

    public int calculate(String scorecard) {
        return new FrameList(scorecard).score();
    }

}
