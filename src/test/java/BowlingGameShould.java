import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BowlingGameShould {

    @Test
    public void add_all_threes_to_60() {
        BowlingGame game = new BowlingGame();
        assertThat(game.calculate("33|33|33|33|33|33|33|33|33|33||"), is(60));
    }

    @Test
    public void add_all_ones_to_20() {
        BowlingGame game = new BowlingGame();
        assertThat(game.calculate("11|11|11|11|11|11|11|11|11|11||"), is(20));
    }

}
