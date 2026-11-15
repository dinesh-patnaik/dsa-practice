package com.dsapractice.leetcode.common;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Keeps TRACKER.md in sync with the {@code Status: To Do | Done} line in each problem's header comment.
 *
 * <p>Every problem's {@code main()} calls {@code TrackerAutoSync.sync(ThisProblem.class)}, so simply
 * running a problem refreshes the tracker. It never throws and only rewrites TRACKER.md when it changed.
 */
public final class TrackerAutoSync {

    private static final String SRC_PKG = "src/main/java/com/dsapractice/leetcode";
    private static final String TEST_PKG = "src/test/java/com/dsapractice/leetcode";

    private static final Map<String, String> CATEGORIES = Map.ofEntries(
            Map.entry("c01_arrays", "Arrays & Hashing"),
            Map.entry("c02_twopointers", "Two Pointers"),
            Map.entry("c03_slidingwindow", "Sliding Window"),
            Map.entry("c04_stack", "Stack"),
            Map.entry("c05_binarysearch", "Binary Search"),
            Map.entry("c06_linkedlist", "Linked List"),
            Map.entry("c07_trees", "Trees"),
            Map.entry("c08_tries", "Tries"),
            Map.entry("c09_heaps", "Heap & Priority Queue"),
            Map.entry("c10_backtracking", "Backtracking"),
            Map.entry("c11_graphs", "Graphs"),
            Map.entry("c12_advancedgraphs", "Advanced Graphs"),
            Map.entry("c13_1d_dp", "1-D Dynamic Programming"),
            Map.entry("c14_2d_dp", "2-D Dynamic Programming"),
            Map.entry("c15_greedy", "Greedy"),
            Map.entry("c16_intervals", "Intervals"),
            Map.entry("c17_math_geometry", "Math & Geometry"),
            Map.entry("c18_bit_manipulation", "Bit Manipulation"));

    private static final Pattern ID_AND_NAME = Pattern.compile("\\[(\\d+)]\\s*([a-zA-Z0-9_()]+)");
    private static final Pattern DIFFICULTY = Pattern.compile("Difficulty:\\s*(\\w+)");
    private static final Pattern SOURCE = Pattern.compile("Source:\\s*(https://\\S+)");
    private static final Pattern STATUS = Pattern.compile("Status:\\s*([^\\n*]+)", Pattern.CASE_INSENSITIVE);

    private record Problem(int id, String name, String difficulty, String category, String sourceUrl,
                           String solutionPath, String testPath, boolean done) {
    }

    private TrackerAutoSync() {
    }

    /**
     * Regenerates TRACKER.md.
     *
     * @param anyClassInProject any class of this project; used to locate the project root
     */
    public static void sync(Class<?> anyClassInProject) {
        try {
            Path projectDir = findProjectDir(anyClassInProject);
            List<Problem> problems = readProblems(projectDir);
            String tracker = render(problems);

            Path trackerFile = projectDir.resolve("TRACKER.md");
            if (Files.exists(trackerFile) && Files.readString(trackerFile).equals(tracker)) {
                return;
            }
            Files.writeString(trackerFile, tracker);
            long done = problems.stream().filter(Problem::done).count();
            System.out.printf("[TrackerAutoSync] TRACKER.md updated: %d/%d Done%n", done, problems.size());
        } catch (IOException | URISyntaxException | RuntimeException e) {
            // Tracking is a convenience - never break running a problem because of it.
            System.err.println("[TrackerAutoSync] Skipped: " + e);
        }
    }

    /** The IDE's working directory may be a parent folder, so walk up from target/classes to the pom.xml. */
    private static Path findProjectDir(Class<?> anyClassInProject) throws URISyntaxException {
        Path dir = Path.of(anyClassInProject.getProtectionDomain().getCodeSource().getLocation().toURI());
        while (dir != null && !Files.exists(dir.resolve("pom.xml"))) {
            dir = dir.getParent();
        }
        if (dir == null) {
            throw new IllegalStateException("pom.xml not found above " + anyClassInProject.getName());
        }
        return dir;
    }

    private static List<Problem> readProblems(Path projectDir) throws IOException {
        try (Stream<Path> files = Files.walk(projectDir.resolve(SRC_PKG))) {
            return files
                    .filter(p -> p.toString().endsWith(".java"))
                    .filter(p -> !p.getParent().getFileName().toString().equals("common"))
                    .sorted()
                    .map(p -> parse(projectDir, p))
                    .sorted(Comparator.comparing(Problem::category).thenComparingInt(Problem::id))
                    .toList();
        }
    }

    private static Problem parse(Path projectDir, Path file) {
        String content;
        try {
            content = Files.readString(file);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read " + file, e);
        }

        String category = file.getParent().getFileName().toString();
        String className = file.getFileName().toString().replaceFirst("\\.java$", "");

        Matcher idAndName = ID_AND_NAME.matcher(content);
        boolean hasHeader = idAndName.find();
        int id = hasHeader ? Integer.parseInt(idAndName.group(1)) : 0;
        String name = hasHeader ? idAndName.group(2).trim() : className;

        String status = firstGroup(STATUS, content, "to do").toLowerCase();

        return new Problem(id, name,
                firstGroup(DIFFICULTY, content, "Medium"),
                category,
                firstGroup(SOURCE, content, "https://leetcode.com/problems/"),
                projectDir.relativize(file).toString().replace('\\', '/'),
                TEST_PKG + "/" + category + "/" + className + "Test.java",
                status.contains("done") || status.contains("solved"));
    }

    private static String firstGroup(Pattern pattern, String content, String fallback) {
        Matcher m = pattern.matcher(content);
        return m.find() ? m.group(1).trim() : fallback;
    }

    private static String render(List<Problem> problems) {
        int total = problems.size();
        long done = problems.stream().filter(Problem::done).count();
        double pct = total > 0 ? done * 100.0 / total : 0;

        StringBuilder sb = new StringBuilder()
                .append("# LeetCode Curated Learning Roadmap (Ordered by Topic Priority)\n\n")
                .append("### 📊 Progress Summary\n")
                .append("- **Total Problems:** ").append(total).append('\n')
                .append("- **Completed (Done):** ").append(done).append(" ✅\n")
                .append("- **Remaining (To Do):** ").append(total - done).append(" ⏳\n")
                .append("- **Completion Rate:** ").append(String.format("%.1f", pct)).append("%\n\n")
                .append("> **Tip:** Change `* Status: To Do` to `* Status: Done` in the problem's Java file header comment. ")
                .append("This dashboard refreshes automatically the next time you run any problem.\n\n")
                .append("---\n\n")
                .append("| # | Problem | Difficulty | Topic / Stage | Solution Link | Unit Test Link | Status |\n")
                .append("|:---|:---|:---:|:---|:---|:---|:---:|\n");

        for (Problem p : problems) {
            sb.append("| ").append(p.id())
                    .append(" | [").append(p.name()).append("](").append(p.sourceUrl()).append(")")
                    .append(" | ").append(p.difficulty())
                    .append(" | ").append(CATEGORIES.getOrDefault(p.category(), p.category()))
                    .append(" | [`").append(p.category()).append("`](").append(p.solutionPath()).append(")")
                    .append(" | [Test](").append(p.testPath()).append(")")
                    .append(" | ").append(p.done() ? "✅ Done" : "⏳ To Do")
                    .append(" |\n");
        }
        return sb.toString();
    }
}
