package runner;

import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class Runner {

    public static void main(String[] args) {
        new Runner().run(args[0]);
    }

    private void run(String tag) {

        PrintStream out = System.out;

        final PointHolder pointHolder = new PointHolder();

        JUnitCore junit = new JUnitCore();
        junit.addListener(new RunListener() {
            @Override
            public void testFailure(Failure failure) {
                Points annotation = failure.getDescription()
                        .getAnnotation(Points.class);

                if (annotation != null) {
                    pointHolder.subtract(annotation.value());
                }
            }

            @Override
            public void testFinished(Description description) {
                Points annotation = description.getAnnotation(Points.class);

                if (annotation != null) {
                    pointHolder.add(annotation.value());
                }
            }
        });

        junit.run(resolveClass(tag));

        String pattern = "{0} of {1} points";

        out.println(MessageFormat.format(pattern,
                pointHolder.getPoints(), 17));
    }

    private Class<?> resolveClass(String tag) {
        Map<String, String> map = new HashMap<>();
        map.put("ex1", "ex1.WordSortTest");
        map.put("ex2", "ex2.NumberRepositoryTest");

        if (!map.containsKey(tag)) {
            throw new IllegalStateException("unknown tag: " + tag);
        }

        return loadClass(map.get(tag));
    }

    private Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static class PointHolder {
        int points = 0;

        public int getPoints() {
            return points;
        }

        void add(int points) {
            this.points += points;
        }

        void subtract(int points) {
            this.points -= points;
        }
    }
}