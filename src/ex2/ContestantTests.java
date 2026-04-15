package ex2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import runner.Points;
import runner.PointsCounterExtension;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PointsCounterExtension.class)
public class ContestantTests {

    private final List<ContestantName> wordList = new ArrayList<>();
    private final Set<ContestantName> wordSet = new LinkedHashSet<>();

    private final List<Contestant> contestantList = new ArrayList<>();
    private final Set<Contestant> contestantSet = new LinkedHashSet<>();

    @Test
    @Points(2)
    public void canTestWhetherListContainsContestantName() {

        wordList.add(new ContestantName("Alice"));
        wordList.add(new ContestantName("Bob"));

        assertThat(wordList.contains(new ContestantName("Bob"))).isTrue();
        assertThat(wordList.contains(new ContestantName("Carol"))).isFalse();
    }

    @Test
    @Points(4)
    public void doesNotHoldDuplicateNames() {

        wordSet.add(new ContestantName("Alice"));
        wordSet.add(new ContestantName("Bob"));
        wordSet.add(new ContestantName("Carol"));
        wordSet.add(new ContestantName("Alice"));

        assertThat(wordSet.size()).isEqualTo(3);
    }

    @Test
    @Points(9)
    public void listContainsOnlyOneContestantFromEachTeam() {

        contestantList.add(contestant("Alice"));
        contestantList.add(contestant("Mia"));

        assertThat(contestantList.contains(contestant("Bob"))).isTrue(); // from Alice's team
        assertThat(contestantList.contains(contestant("David"))).isTrue(); // from Alice's team
        assertThat(contestantList.contains(contestant("Oliver"))).isTrue(); // from Mia's team
        assertThat(contestantList.contains(contestant("Frank"))).isFalse();
        assertThat(contestantList.contains(contestant("Quinn"))).isFalse();
    }

    @Test
    @Points(10)
    public void setContainsOnlyOneContestantFromEachTeam() {

        contestantSet.addAll(List.of(
                contestant("Bob"),
                contestant("Tina"),
                contestant("David"),
                contestant("Rachel"),
                contestant("Henry"),
                contestant("Kate")));

        assertThat(contestantSet.toString())
                .isEqualTo("[Bob, Tina, Henry, Kate]");
    }

    private Contestant contestant(String name) {
        return new Contestant(new ContestantName(name));
    }

}