package ex2;

import org.junit.Test;
import runner.Points;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NumberRepositoryTest {

    @Test
    @Points(2)
    public void sumsSimpleNumbers() {

        NumberRepository repo = new NumberRepository();

        int sum = 0;
        for (SimpleNumber num : repo.getSimpleNumbers()) {
            sum += num.intValue();
        }

        assertThat(sum, is(33));
    }

    @Test
    @Points(5)
    public void sumsScientificNumbers() {

        NumberRepository repo = new NumberRepository();

        int sum = 0;
        for (ScientificNumber num : repo.getScientificNumbers()) {
            sum += num.intValue();
        }

        assertThat(sum, is(28000));
    }

    @Test
    @Points(10)
    public void sumsAllNumbers() {

        NumberRepository repo = new NumberRepository();

        int sum = 0;
        for (Number num : repo.getAllNumbers()) {
            sum += num.intValue();
        }

        assertThat(sum, is(28033));

        repo.getAllNumbers().forEach(this::ensureCustomClass);
    }

    private void ensureCustomClass(Object x) {
        if (x.getClass() == SimpleNumber.class
                || x.getClass() == ScientificNumber.class) {

            return;
        }

        throw new AssertionError("Number should be SimpleNumber or ScientificNumber");
    }

}
