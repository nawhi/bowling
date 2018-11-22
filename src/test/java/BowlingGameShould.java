import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BowlingGameShould {

    @ParameterizedTest
    @CsvSource({
            "33|33|33|33|33|33|33|33|33|33||, 60",
            "11|11|11|11|11|11|11|11|11|11||, 20"
    })
    public void add_numbers_correctly(String scorecard, int expectedTotal) {
        BowlingGame game = new BowlingGame();
        assertThat(game.calculate(scorecard), is(expectedTotal));
    }

}
