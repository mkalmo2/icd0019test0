package runner;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Map;

public class Runner {

    public static void main(String[] args) {
        new Runner().run(args[0]);
    }

    private String resolveClassName(String tag) {
        String className = Map.of(
                "ex1", "ex1.RotatingListTests"
                ).get(tag);

        if (className != null) {
            return className;
        } else {
            throw new RuntimeException("unknown tag: " + tag);
        }
    }

    public void run(String tag) {
        String className = resolveClassName(tag);

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectClass(loadClass(className)))
                .build();

        Launcher launcher = LauncherFactory.create();

        PrintStream out = System.out;

        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        launcher.execute(request);

        out.printf("%s of %s points%n",
                PointsCounterExtension.getPoints(), PointsCounterExtension.getTotalPoints());
    }

    private Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
