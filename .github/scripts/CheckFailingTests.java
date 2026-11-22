import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

// Checks that only the expected tests failed in a lesson's last Maven test run.
// In lessons 1, 2 and 4, failing tests show attendees what to fix, so any other result means a change
// broke the lesson: it solved a challenge, or it broke a test that should pass.
// Run it from the repository root, after ./mvnw test -Dmaven.test.failure.ignore=true in the lesson:
//   java .github/scripts/CheckFailingTests.java LESSON [TestClass.testMethod ...]
public class CheckFailingTests {

    public static void main(String[] args) throws Exception {
        String lesson = args[0];
        Set<String> expected = new TreeSet<>(List.of(args).subList(1, args.length));
        Set<String> ran = new TreeSet<>();
        Set<String> failed = new TreeSet<>();

        Path reports = Path.of(lesson, "target", "surefire-reports");
        if (Files.isDirectory(reports)) {
            try (DirectoryStream<Path> files = Files.newDirectoryStream(reports, "TEST-*.xml")) {
                for (Path file : files) {
                    NodeList testCases = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                            .parse(file.toFile()).getElementsByTagName("testcase");
                    for (int i = 0; i < testCases.getLength(); i++) {
                        Element testCase = (Element) testCases.item(i);
                        String className = testCase.getAttribute("classname");
                        String test = className.substring(className.lastIndexOf('.') + 1) + "." + testCase.getAttribute("name");
                        ran.add(test);
                        if (testCase.getElementsByTagName("failure").getLength() > 0
                                || testCase.getElementsByTagName("error").getLength() > 0) {
                            failed.add(test);
                        }
                    }
                }
            }
        }

        if (ran.isEmpty()) {
            System.out.println("::error::" + lesson + ": no test results in " + reports);
            System.exit(1);
        }
        if (!failed.equals(expected)) {
            System.out.println("::error::" + lesson + ": expected only " + expected + " to fail, but " + failed + " failed");
            System.exit(1);
        }
        System.out.println(lesson + ": " + ran.size() + " tests ran, and only the expected " + failed.size() + " failed");
    }
}
