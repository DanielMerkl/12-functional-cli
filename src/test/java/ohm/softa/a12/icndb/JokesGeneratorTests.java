package ohm.softa.a12.icndb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Peter Kurfer
 * Created on 12/28/17.
 */
class JokesGeneratorTests {

    private JokeGenerator jokeGenerator = new JokeGenerator();

    @Test
    void testRandomStream() {
		jokeGenerator.randomJokesStream()
			.limit(5)
			.forEach(Assertions::assertNotNull);
    }


    @Test
    void testJokesStream() {
		jokeGenerator.jokesStream()
			.limit(5)
			.forEach(Assertions::assertNotNull);
    }

}
