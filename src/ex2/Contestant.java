package ex2;

import java.util.List;

public record Contestant(ContestantName contestantName) {

    public static final List<String> TEAM1 = List.of("Alice", "Bob", "Carla", "David", "Eva");
    public static final List<String> TEAM2 = List.of("Frank", "Gina", "Henry", "Iris", "Jack");
    public static final List<String> TEAM3 = List.of("Kate", "Leo", "Mia", "Nick", "Oliver", "Paula");
    public static final List<String> TEAM4 = List.of("Quinn", "Rachel", "Sam", "Tina");

}
