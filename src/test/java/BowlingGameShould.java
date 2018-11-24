import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BowlingGameShould {

    @ParameterizedTest
    @CsvSource({
            "33|33|33|33|33|33|33|33|33|33||, 60",
            "11|11|11|11|11|11|11|11|11|11||, 20",
            "11|22|11|22|11|22|11|22|11|22||, 30",
            "12|22|11|22|11|22|11|22|11|22||, 31",
            "--|--|--|--|--|--|--|--|--|--||, 0",
            "1-|2-|3-|4-|5-|6-|7-|8-|9-|--||, 45",
            "X|--|--|--|--|--|--|--|--|--||, 10",
            "X|--|--|5-|5-|5-|5-|--|--|--||, 30",
            "X|5-|--|--|--|--|--|--|--|--||, 20",
            "X|51|--|--|--|--|--|--|--|--||, 22",
            "--|--|--|--|--|--|--|--|--|X||5-, 15",
            "--|--|--|--|--|--|--|--|--|X||X4, 24",
            "--|--|--|--|--|--|--|--|--|X||XX, 30",
            "X|X|X|--|--|--|--|--|--|--||, 60",
            "X|X|X|X|X|X|X|X|X|X||XX, 300",
            "9/|--|--|--|--|--|--|--|--|--||, 10",
            "9/|34|--|--|--|--|--|--|--|--||, 20",
            "5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5, 150",
//            "X|X|X|X|X|X|X|X|X|X||5/, 285",
    })
    public void add_numbers_correctly(String scorecard, int expectedTotal) {
        BowlingGame game = new BowlingGame();
        assertThat(game.calculate(scorecard), is(expectedTotal));
    }

}