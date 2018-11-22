import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BowlingGameShould {

    @Test
    public void add_up_numbers_with_no_strikes_spares_or_misses() {
        BowlingGame game = new BowlingGame();
        assertThat(game.calculate("33|33|33|33|33|33|33|33|33|33||"), is(60));
    }
}
