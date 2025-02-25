package runner;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class PointsCounterExtension implements TestWatcher {

    private static final PointHolder pointHolder = new PointHolder();

    public static int getPoints() {
        return pointHolder.getPoints();
    }

    public static int getTotalPoints() {
        return pointHolder.totalPoints;
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Optional<Points> points = context.getElement()
                .flatMap(el -> Optional.ofNullable(el.getAnnotation(Points.class)));

        points.ifPresent(a -> pointHolder.increaseTotal(a.value()));

        Optional<NoPointsIfThisTestFails> noPoints = context.getElement()
                .flatMap(el -> Optional.ofNullable(el.getAnnotation(
                        NoPointsIfThisTestFails.class)));

        noPoints.ifPresent(a -> pointHolder.failAll());
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        Optional<Points> annotation = context.getElement()
                .flatMap(el -> Optional.ofNullable(el.getAnnotation(Points.class)));

        annotation.ifPresent(
                a -> {
                    pointHolder.add(a.value());
                    pointHolder.increaseTotal(a.value());
                });

    }

    private static class PointHolder {
        int points = 0;
        int totalPoints = 0;

        public int getPoints() {
            return Math.max(0, points);
        }

        void add(int points) {
            this.points += points;
        }

        void increaseTotal(int points) {
            this.totalPoints += points;
        }

        void failAll() {
            this.points = -100;
        }
    }

}
