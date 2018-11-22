import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThat;

public class AT_Simple {

    @Test
    public void adds_up_numbers_with_no_strikes_spares_or_misses() {
        BowlingGame game = new BowlingGame();assertThat(game.calculate("33|33|33|33|33|33|33|33|33|33||"), CoreMatchers.is(60));
    }
}
