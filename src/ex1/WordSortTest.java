package ex1;

import org.junit.Test;
import runner.Points;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WordSortTest {

    @Test
    @Points(2)
    public void insertedInOrder() {
        WordStore wordStore = new WordStore();

        wordStore.add("one");
        wordStore.add("two");
        wordStore.add("three");

        assertThat(wordStore.getStoredWords(),
                contains("one", "two", "three"));
    }

    @Test
    @Points(5)
    public void allowsToCompareWords() {
        WordStore wordStore = new WordStore();

        assertThat(wordStore.compare("one", "three"), is(lessThan(0)));
        assertThat(wordStore.compare("five", "two"), is(greaterThan(0)));
        assertThat(wordStore.compare("ten", "ten"), is(0));
    }

    @Test
    @Points(10)
    public void insertedInArbitraryOrder() {
        WordStore wordStore = new WordStore();

        wordStore.add("three");
        wordStore.add("one");
        wordStore.add("two");
        wordStore.add("ten");
        wordStore.add("one");
        wordStore.add("five");
        wordStore.add("ten");
        wordStore.add("nine");
        wordStore.add("eight");

        assertThat(wordStore.getStoredWords(),
                contains("one", "one", "two", "three",
                        "five", "eight", "nine", "ten", "ten"));
    }
}
