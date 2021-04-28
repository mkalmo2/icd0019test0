package ex2;

import java.util.List;

public class NumberRepository {

    private String[] simpleNumberData = "2, 4, 6, 2, 9, 10".split(", ");
    private String[] scientificNumberData = "2e3, 2e4, 6e3".split(", ");

    public List<SimpleNumber> getSimpleNumbers() {
        throw new RuntimeException("not implemented yet");
    }

    public List<ScientificNumber> getScientificNumbers() {
        throw new RuntimeException("not implemented yet");
    }

    public List<Number> getAllNumbers() {
        throw new RuntimeException("not implemented yet");
    }

}
