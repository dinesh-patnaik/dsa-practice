package com.dsapractice.tools;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.awt.Desktop;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Live progress dashboard. Serves an HTML page that is rebuilt from the {@code Status: To Do | Done}
 * line in every problem's header comment on each request - there is no generated file to go stale.
 *
 * <p>Run it once ({@code ./run.sh TrackerDashboard}); the page opens in your browser and refreshes
 * itself every few seconds, so flipping a Status to Done shows up without doing anything else.
 * Binds to 127.0.0.1 only. Optional argument: port (default 8765).
 *
 * <p>The UI is a clean card-based layout (light, dark or system theme) with its own palette and logo.
 * It is fully self-contained: no external fonts, scripts or images, and no third-party brand assets.
 */
public final class TrackerDashboard {

    private static final int DEFAULT_PORT = 8765;
    private static final int REFRESH_SECONDS = 3;
    private static final String SRC_PKG = "src/main/java/com/dsapractice/leetcode";
    private static final double RING_CIRCUMFERENCE = 2 * Math.PI * 52;
    private static final List<String> DIFFICULTIES = List.of("Easy", "Medium", "Hard");

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
    /** Splits CamelCase / digit boundaries: "NumberOf1Bits" -> "Number Of 1 Bits", "LRUCache" -> "LRU Cache". */
    private static final Pattern WORD_BOUNDARY = Pattern.compile(
            "(?<=[a-z])(?=[A-Z0-9])|(?<=[A-Z0-9])(?=[A-Z][a-z])|(?<=[0-9])(?=[A-Za-z])");

    private record Problem(int id, String name, String difficulty, String category, String sourceUrl,
                           String file, boolean done) {
    }

    private TrackerDashboard() {
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;
        Path projectDir = findProjectDir();

        HttpServer server = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), port), 0);
        server.createContext("/", exchange -> handle(exchange, projectDir));
        server.start();

        String url = "http://localhost:" + port + "/";
        System.out.println("Tracker dashboard running at " + url + "  (Ctrl+C to stop)");
        openBrowser(url);
    }

    private static void handle(HttpExchange exchange, Path projectDir) throws IOException {
        try (exchange) {
            boolean get = "GET".equals(exchange.getRequestMethod());
            if (!get || !"/".equals(exchange.getRequestURI().getPath())) {
                respond(exchange, get ? 404 : 405, "Not found");
                return;
            }
            respond(exchange, 200, render(readProblems(projectDir)));
        } catch (IOException | RuntimeException e) {
            System.err.println("[TrackerDashboard] " + e);
            respond(exchange, 500, "Internal error");
        }
    }

    private static void respond(HttpExchange exchange, int status, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
        exchange.getResponseHeaders().set("Cache-Control", "no-store");
        exchange.getResponseHeaders().set("X-Content-Type-Options", "nosniff");
        exchange.getResponseHeaders().set("Content-Security-Policy",
                "default-src 'none'; style-src 'unsafe-inline'; script-src 'unsafe-inline'; connect-src 'self'; img-src data:");
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream out = exchange.getResponseBody()) {
            out.write(bytes);
        }
    }

    private static void openBrowser(String url) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(URI.create(url));
            }
        } catch (IOException | RuntimeException e) {
            // Not fatal - the URL is printed above.
        }
    }

    /** The IDE's working directory may be a parent folder, so walk up from target/classes to the pom.xml. */
    private static Path findProjectDir() throws URISyntaxException {
        Path dir = Path.of(TrackerDashboard.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        while (dir != null && !Files.exists(dir.resolve("pom.xml"))) {
            dir = dir.getParent();
        }
        if (dir == null) {
            throw new IllegalStateException("pom.xml not found above the compiled classes");
        }
        return dir;
    }

    private static List<Problem> readProblems(Path projectDir) throws IOException {
        try (Stream<Path> files = Files.walk(projectDir.resolve(SRC_PKG))) {
            return files
                    .filter(p -> p.toString().endsWith(".java"))
                    .filter(p -> !p.getParent().getFileName().toString().equals("common"))
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
        String status = firstGroup(STATUS, content, "to do").toLowerCase(Locale.ROOT);

        return new Problem(
                hasHeader ? Integer.parseInt(idAndName.group(1)) : 0,
                hasHeader ? idAndName.group(2).trim() : className,
                firstGroup(DIFFICULTY, content, "Medium"),
                category,
                firstGroup(SOURCE, content, "https://leetcode.com/problems/"),
                projectDir.relativize(file).toString().replace('\\', '/'),
                status.contains("done") || status.contains("solved"));
    }

    private static String firstGroup(Pattern pattern, String content, String fallback) {
        Matcher m = pattern.matcher(content);
        return m.find() ? m.group(1).trim() : fallback;
    }

    private static String esc(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    private static String topic(String category) {
        return CATEGORIES.getOrDefault(category, category);
    }

    private static String readable(String name) {
        return WORD_BOUNDARY.matcher(name).replaceAll(" ");
    }

    private static String pct(double value) {
        return String.format(Locale.ROOT, "%.1f", value);
    }

    // ------------------------------------------------------------------ page

    private static String render(List<Problem> problems) {
        return new StringBuilder(HEAD)
                .append("<div class=\"layout\"><section id=\"summary\" aria-label=\"Progress\">").append(renderSummary(problems)).append("</section>")
                .append("<div class=\"content\"><div class=\"section-head\"><h2>Problems</h2><span class=\"count\" id=\"shown\" aria-live=\"polite\"></span></div>")
                .append(renderFilters())
                .append("<section id=\"list\" aria-label=\"Problems\">").append(renderList(problems)).append("</section></div></div>")
                .append(TAIL)
                .toString();
    }

    private static String renderSummary(List<Problem> problems) {
        int total = problems.size();
        long done = problems.stream().filter(Problem::done).count();
        double pct = total > 0 ? done * 100.0 / total : 0;
        String pctText = pct(pct) + "%";
        double dash = RING_CIRCUMFERENCE * pct / 100;

        StringBuilder sb = new StringBuilder("<div class=\"hero\">")
                .append("<svg class=\"deco\" viewBox=\"0 0 400 240\" preserveAspectRatio=\"xMaxYMid slice\" aria-hidden=\"true\">")
                .append("<circle class=\"d1\" cx=\"330\" cy=\"40\" r=\"90\"/>")
                .append("<circle class=\"d2\" cx=\"392\" cy=\"205\" r=\"70\"/>")
                .append("<circle class=\"d3\" cx=\"235\" cy=\"225\" r=\"40\"/></svg>")
                .append("<div class=\"hero-text\"><div class=\"eyebrow\">LeetCode roadmap &middot; ")
                .append(CATEGORIES.size()).append(" topics</div>")
                .append("<h1>").append(done).append(" of ").append(total).append(" solved</h1>")
                .append("<p>").append(total - done).append(" to go. Mark a problem <b>Status: Done</b> in its code and this page updates itself.</p>")
                .append("</div>")
                .append("<svg class=\"ring\" width=\"132\" height=\"132\" viewBox=\"0 0 120 120\" role=\"img\" aria-label=\"")
                .append(pctText).append(" complete\">")
                .append("<circle class=\"track\" cx=\"60\" cy=\"60\" r=\"52\" fill=\"none\" stroke-width=\"10\"/>")
                .append("<circle class=\"prog\" cx=\"60\" cy=\"60\" r=\"52\" fill=\"none\" stroke-width=\"10\" stroke-linecap=\"round\"")
                .append(" stroke-dasharray=\"").append(pct(dash)).append(' ').append(pct(RING_CIRCUMFERENCE))
                .append("\" transform=\"rotate(-90 60 60)\"/>")
                .append("<text x=\"60\" y=\"60\" text-anchor=\"middle\" dominant-baseline=\"central\">").append(pctText).append("</text></svg>")
                .append("</div><div class=\"meters\">");

        for (String difficulty : DIFFICULTIES) {
            long all = problems.stream().filter(p -> p.difficulty().equalsIgnoreCase(difficulty)).count();
            long solved = problems.stream().filter(p -> p.difficulty().equalsIgnoreCase(difficulty) && p.done()).count();
            sb.append("<div class=\"meter\"><div class=\"mh\"><span class=\"mn ").append(difficulty).append("\">")
                    .append(difficulty).append("</span><span class=\"mc\">").append(solved).append(" of ").append(all)
                    .append("</span></div><div class=\"bar\"><i class=\"").append(difficulty).append("\" style=\"width:")
                    .append(pct(all > 0 ? solved * 100.0 / all : 0)).append("%\"></i></div></div>");
        }
        return sb.append("</div>").toString();
    }

    private static String renderFilters() {
        StringBuilder options = new StringBuilder("<option value=\"\">All topics</option>");
        CATEGORIES.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(e ->
                options.append("<option value=\"").append(esc(e.getKey())).append("\">")
                        .append(esc(e.getValue())).append("</option>"));

        return "<div class=\"chips\" role=\"group\" aria-label=\"Filters\">"
                + chip("s", "all", true, true) + chip("s", "todo", false, true) + chip("s", "done", false, true)
                + "<span class=\"sep\"></span>"
                + chip("d", "Easy", false, false) + chip("d", "Medium", false, false) + chip("d", "Hard", false, false)
                + "<span class=\"sep\"></span>"
                + "<div class=\"sel\"><select id=\"topic\" aria-label=\"Filter by topic\">" + options + "</select>"
                + "<svg class=\"ic\" aria-hidden=\"true\"><use href=\"#i-chev\"/></svg></div>"
                + "<button type=\"button\" class=\"link\" id=\"clear\" hidden>Clear filters</button></div>"
                + "<div class=\"finder\" role=\"search\"><svg class=\"ic\" aria-hidden=\"true\"><use href=\"#i-search\"/></svg>"
                + "<input id=\"q\" type=\"search\" placeholder=\"Search by name, number or topic\" aria-label=\"Search problems\" autocomplete=\"off\">"
                + "<kbd aria-hidden=\"true\">/</kbd></div>";
    }

    private static String chip(String group, String value, boolean pressed, boolean withCount) {
        String label = switch (value) {
            case "all" -> "All";
            case "todo" -> "To do";
            case "done" -> "Done";
            default -> value;
        };
        return "<button type=\"button\" class=\"chip\" data-g=\"" + group + "\" data-v=\"" + value + "\" aria-pressed=\""
                + pressed + "\">" + label
                + (withCount ? " <span class=\"n\" data-n=\"" + value + "\"></span>" : "") + "</button>";
    }

    private static String renderList(List<Problem> problems) {
        StringBuilder sb = new StringBuilder("<div class=\"card\"><div class=\"scroll\"><table><thead><tr>")
                .append("<th scope=\"col\" class=\"nm\">#</th><th scope=\"col\">Problem</th><th scope=\"col\" class=\"tp\">Topic</th>")
                .append("<th scope=\"col\">Difficulty</th><th scope=\"col\" class=\"st\">Status</th></tr></thead><tbody>");

        for (Problem p : problems) {
            String file = p.file().substring(p.file().lastIndexOf('/') + 1);
            String title = readable(p.name());
            String search = (p.id() + " " + title + " " + topic(p.category()) + " " + file + " " + p.difficulty())
                    .toLowerCase(Locale.ROOT);

            sb.append("<tr data-s=\"").append(p.done() ? "done" : "todo").append("\" data-d=\"").append(esc(p.difficulty()))
                    .append("\" data-c=\"").append(esc(p.category())).append("\" data-q=\"").append(esc(search)).append("\">")
                    .append("<td class=\"num\">").append(p.id()).append("</td>")
                    .append("<td><a class=\"title\" href=\"").append(esc(p.sourceUrl()))
                    .append("\" target=\"_blank\" rel=\"noopener noreferrer\">").append(esc(title)).append("</a>")
                    .append("<div class=\"file\">").append(esc(file)).append("</div></td>")
                    .append("<td class=\"topic tp\">").append(esc(topic(p.category()))).append("</td>")
                    .append("<td><span class=\"pill ").append(esc(p.difficulty())).append("\">")
                    .append(esc(p.difficulty())).append("</span></td>")
                    .append("<td class=\"st\"><span class=\"status ").append(p.done() ? "done\"><i></i>Done" : "todo\"><i></i>To do")
                    .append("</span></td></tr>");
        }
        return sb.append("<tr class=\"empty\" id=\"empty\" hidden><td colspan=\"5\">")
                .append("<b>No problems match your filters</b><br>Try a different search or clear the filters.</td></tr>")
                .append("</tbody></table></div></div>").toString();
    }

    // -------------------------------------------------------------- templates

    private static final String HEAD = """
            <!DOCTYPE html>
            <html lang="en"><head><meta charset="utf-8">
            <meta name="viewport" content="width=device-width,initial-scale=1">
            <meta name="color-scheme" content="light dark">
            <title>DSA Practice</title>
            <link rel="icon" href="data:,">
            <script>
            (function(){
              var t='system';try{t=localStorage.getItem('dsa-theme')||'system';}catch(e){}
              var mq=matchMedia('(prefers-color-scheme: dark)');
              function eff(x){return x==='system'?(mq.matches?'dark':'light'):x;}
              function paint(){document.documentElement.setAttribute('data-theme',eff(t));}
              paint();
              mq.addEventListener('change',function(){if(t==='system')paint();});
              window.__theme={get:function(){return t;},set:function(n){t=n;try{localStorage.setItem('dsa-theme',n);}catch(e){}paint();}};
            })();
            </script>
            <style>
            :root{color-scheme:light;--bg:#f5f6fb;--surface:#fff;--container:#eef0f8;--container-hi:#e3e6f2;--text:#1a1c28;--muted:#5a5f73;
            --outline:#c3c8da;--soft:#e1e4ef;--primary:#4338ca;--on-primary:#fff;--primary-c:#e0e3ff;--on-primary-c:#1e1b6b;--accent:#0d9488;
            --ok:#15803d;--green:#166534;--green-c:#dcfce7;--amber:#92400e;--amber-c:#fef3c7;--red:#b91c1c;--red-c:#fee2e2;
            --hero1:#e0e3ff;--hero2:#f1f2fb;--track:rgba(255,255,255,.8);--shadow:0 1px 2px rgba(20,22,40,.25),0 2px 8px 2px rgba(20,22,40,.12)}
            :root[data-theme=dark]{color-scheme:dark;--bg:#11121a;--surface:#1b1d28;--container:#242635;--container-hi:#2e3144;--text:#e6e8f2;
            --muted:#a1a6bd;--outline:#4a4f66;--soft:#2f3245;--primary:#a5b4fc;--on-primary:#17194f;--primary-c:#3a3f9a;--on-primary-c:#e0e3ff;--accent:#5eead4;
            --ok:#4ade80;--green:#86efac;--green-c:#12351f;--amber:#fcd34d;--amber-c:#3b2e0a;--red:#fca5a5;--red-c:#4a1515;
            --hero1:#262a68;--hero2:#1b1d28;--track:rgba(255,255,255,.14);--shadow:0 1px 3px rgba(0,0,0,.6),0 4px 10px 3px rgba(0,0,0,.35)}
            *{box-sizing:border-box}
            html{scroll-padding-top:80px}
            body{margin:0;background:var(--bg);color:var(--text);font:14px/20px system-ui,-apple-system,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif;-webkit-font-smoothing:antialiased}
            [hidden]{display:none!important}
            .ic{width:20px;height:20px;flex:none;fill:none;stroke:currentColor;stroke-width:2;stroke-linecap:round;stroke-linejoin:round}
            .skip{position:absolute;left:-999px;top:8px;background:var(--primary);color:var(--on-primary);padding:8px 16px;border-radius:20px;z-index:10}
            .skip:focus{left:16px}

            .appbar{position:sticky;top:0;z-index:5;background:var(--surface);border-bottom:1px solid var(--soft);transition:box-shadow .2s}
            .appbar::before{content:"";display:block;height:4px;background:linear-gradient(90deg,var(--primary),var(--accent))}
            .appbar.scrolled{box-shadow:var(--shadow)}
            .appbar-in{max-width:1680px;margin:0 auto;padding:10px 32px;min-height:68px;display:flex;align-items:center;gap:16px}
            .brand{display:flex;align-items:center;gap:14px;min-width:0;white-space:nowrap}
            .logo{width:44px;height:44px;flex:none}
            .wm{display:flex;flex-direction:column;gap:2px}
            .wm-t{font-size:23px;line-height:26px;font-weight:400;letter-spacing:-.2px;color:var(--text)}
            .wm-t b{font-weight:800;letter-spacing:.4px;color:var(--primary)}
            .wm-s{font-size:12px;line-height:16px;letter-spacing:.3px;color:var(--muted)}
            .actions{display:flex;align-items:center;gap:4px;margin-left:auto}
            .live{display:flex;align-items:center;gap:8px;height:32px;padding:0 12px;border-radius:16px;color:var(--muted);font-size:13px;white-space:nowrap}
            .live i{width:8px;height:8px;border-radius:50%;background:var(--ok)}
            .live .ts{margin-left:-4px}
            .live.off{color:var(--red)}.live.off i{background:var(--red)}
            .iconbtn{width:40px;height:40px;display:inline-flex;align-items:center;justify-content:center;border:0;border-radius:50%;background:transparent;color:var(--muted);cursor:pointer}
            .iconbtn:hover{background:var(--container-hi)}
            .menuwrap{position:relative}
            .menu{position:absolute;right:0;top:46px;min-width:200px;padding:8px 0;background:var(--surface);border-radius:12px;box-shadow:var(--shadow);z-index:6}
            .menu-h{padding:8px 16px 4px;color:var(--muted);font-size:12px}
            .menu button{display:flex;align-items:center;gap:16px;width:100%;height:44px;padding:0 16px;border:0;background:transparent;color:var(--text);font:inherit;text-align:left;cursor:pointer}
            .menu button:hover,.menu button:focus-visible{background:var(--container);outline:none}
            .menu button[aria-checked=true]{color:var(--primary)}
            .menu button[aria-checked=true]::after{content:"✓";margin-left:auto;font-weight:700}
            .iconbtn:focus-visible,.chip:focus-visible,.link:focus-visible,.sel select:focus-visible,.title:focus-visible{outline:2px solid var(--primary);outline-offset:2px}

            main{max-width:1680px;margin:0 auto;padding:16px 32px 24px}
            .hero{position:relative;overflow:hidden;display:flex;align-items:center;justify-content:space-between;gap:32px;padding:36px 48px;border-radius:28px;background:linear-gradient(120deg,var(--hero1),var(--hero2))}
            .deco{position:absolute;right:0;top:0;height:100%;width:auto;pointer-events:none}
            .d1{fill:var(--primary);opacity:.14}.d2{fill:var(--accent);opacity:.16}.d3{fill:#8b5cf6;opacity:.16}
            .hero-text{position:relative;min-width:0}
            .eyebrow{color:var(--primary);font-size:12px;font-weight:500;letter-spacing:.8px;text-transform:uppercase}
            .hero h1{margin:4px 0 8px;font-size:40px;line-height:48px;font-weight:400}
            .hero p{margin:0;max-width:480px;color:var(--muted);font-size:15px;line-height:22px}
            .hero p b{color:var(--text);font-weight:500}
            .ring{position:relative;flex:none}.ring .track{stroke:var(--track)}.ring .prog{stroke:var(--ok)}
            .ring text{fill:var(--text);font-size:19px;font-weight:500}
            .meters{display:grid;grid-template-columns:repeat(3,1fr);gap:16px;margin-top:16px}
            .meter{padding:16px 20px;background:var(--surface);border:1px solid var(--soft);border-radius:16px}
            .mh{display:flex;align-items:baseline;justify-content:space-between;margin-bottom:12px}
            .mn{font-weight:500}.mn.Easy{color:var(--green)}.mn.Medium{color:var(--amber)}.mn.Hard{color:var(--red)}
            .mc{color:var(--muted);font-size:13px}
            .bar{height:8px;border-radius:4px;background:var(--container-hi);overflow:hidden}
            .bar i{display:block;height:100%;border-radius:4px;min-width:0}
            .bar i.Easy{background:var(--ok)}.bar i.Medium{background:#f59e0b}.bar i.Hard{background:#dc2626}

            .section-head{display:flex;align-items:baseline;justify-content:space-between;margin:32px 0 12px}
            .section-head h2{margin:0;font-size:22px;line-height:28px;font-weight:400}
            .count{color:var(--muted);font-size:13px}
            .chips{display:flex;flex-wrap:wrap;align-items:center;gap:8px;margin-bottom:12px}
            .finder{position:relative;margin-bottom:16px}
            .finder .ic{position:absolute;left:16px;top:13px;color:var(--muted);pointer-events:none}
            .finder input{width:100%;height:46px;padding:0 48px;border:1px solid var(--outline);border-radius:12px;background:var(--surface);color:var(--text);font:inherit;font-size:15px;outline:none;transition:border-color .15s,box-shadow .15s}
            .finder input::placeholder{color:var(--muted)}
            .finder input:focus{border-color:var(--primary);box-shadow:0 0 0 3px var(--primary-c)}
            .finder kbd{position:absolute;right:14px;top:12px;height:22px;min-width:22px;padding:0 6px;border:1px solid var(--outline);border-radius:6px;color:var(--muted);font:12px/20px ui-monospace,SFMono-Regular,Menlo,monospace;text-align:center}
            .finder input:focus~kbd,.finder input:not(:placeholder-shown)~kbd{display:none}
            .sep{width:1px;height:24px;background:var(--outline);opacity:.6;margin:0 4px}
            .chip{display:inline-flex;align-items:center;gap:6px;height:32px;padding:0 12px;border:1px solid var(--outline);border-radius:8px;background:transparent;color:var(--text);font:inherit;cursor:pointer}
            .chip:hover{background:var(--container)}
            .chip[aria-pressed=true]{background:var(--primary-c);border-color:transparent;color:var(--on-primary-c)}
            .chip[aria-pressed=true]::before{content:"✓"}
            .chip .n{color:var(--muted);font-size:12px}
            .chip[aria-pressed=true] .n{color:inherit}
            .sel{position:relative}
            .sel select{appearance:none;-webkit-appearance:none;height:32px;padding:0 32px 0 12px;border:1px solid var(--outline);border-radius:8px;background:transparent;color:var(--text);font:inherit;cursor:pointer}
            .sel select:hover{background:var(--container)}
            .sel option{background:var(--surface);color:var(--text)}
            .sel .ic{position:absolute;right:8px;top:6px;width:18px;height:18px;color:var(--muted);pointer-events:none}
            .link{height:32px;padding:0 12px;border:0;border-radius:16px;background:transparent;color:var(--primary);font:inherit;font-weight:500;cursor:pointer}
            .link:hover{background:var(--primary-c)}

            .card{background:var(--surface);border:1px solid var(--soft);border-radius:16px;overflow:hidden}
            .scroll{overflow-x:auto}
            table{width:100%;border-collapse:collapse}
            th{padding:14px 16px;text-align:left;font-size:12px;font-weight:500;color:var(--muted);background:var(--container);white-space:nowrap}
            td{padding:12px 16px;border-top:1px solid var(--soft);vertical-align:middle}
            tbody tr:hover{background:var(--container)}
            .num{width:64px;color:var(--muted);font-variant-numeric:tabular-nums}
            .title{color:var(--text);font-weight:500;text-decoration:none;border-radius:4px}
            .title:hover{color:var(--primary);text-decoration:underline}
            .file{margin-top:2px;color:var(--muted);font:12px/16px ui-monospace,SFMono-Regular,Menlo,monospace}
            .topic{color:var(--muted)}
            .pill,.status{display:inline-flex;align-items:center;gap:6px;height:24px;padding:0 10px;border-radius:12px;font-size:12px;font-weight:500;white-space:nowrap;background:var(--container-hi);color:var(--muted)}
            .pill.Easy{background:var(--green-c);color:var(--green)}
            .pill.Medium{background:var(--amber-c);color:var(--amber)}
            .pill.Hard{background:var(--red-c);color:var(--red)}
            .status i{width:8px;height:8px;border-radius:50%;background:currentColor;opacity:.6}
            .status.done{background:var(--green-c);color:var(--green)}
            .status.done i{opacity:1}
            th.st,td.st{width:1%;white-space:nowrap}
            .empty td{padding:56px 16px;text-align:center;color:var(--muted);line-height:24px}
            .empty b{color:var(--text);font-size:16px;font-weight:500}
            footer{margin:24px 0 8px;color:var(--muted);font-size:12px;text-align:center}

            @media(min-width:1101px){
              .layout{display:grid;grid-template-columns:360px minmax(0,1fr);gap:24px;align-items:start}
              #summary{position:sticky;top:92px;max-height:calc(100vh - 108px);overflow-y:auto}
              .content .section-head{margin-top:0}
              .hero{flex-direction:column-reverse;align-items:flex-start;justify-content:flex-start;gap:16px;padding:28px;border-radius:24px}
              .hero h1{font-size:36px;line-height:44px}
              .ring{width:120px;height:120px}
              .meters{grid-template-columns:1fr;gap:12px}
              .meter{padding:14px 20px}
              .mh{margin-bottom:10px}
            }
            @media(max-width:900px){.ts{display:none}.hero{padding:28px 32px}.hero h1{font-size:34px;line-height:42px}}
            @media(max-width:720px){
              .appbar-in{padding:8px 16px;gap:8px;min-height:60px}
              .logo{width:38px;height:38px}.brand{gap:10px}.wm-t{font-size:20px;line-height:24px}
              .finder kbd{display:none}
              main{padding:8px 16px 16px}
              .hero{flex-direction:column-reverse;align-items:flex-start;gap:16px;padding:24px;border-radius:24px}
              .ring{width:96px;height:96px}.hero h1{font-size:30px;line-height:38px}
              .meters{gap:8px}.meter{padding:12px}.mh{flex-direction:column;gap:2px;margin-bottom:8px}
              .tp,.nm,.num,.file{display:none}
              .deco,.sep{display:none}
            }
            @media(max-width:480px){.wm-s{display:none}}
            @media(prefers-reduced-motion:reduce){*{transition:none!important}}
            @media(prefers-reduced-motion:no-preference){.chip,.iconbtn,tbody tr{transition:background .15s}}
            </style></head>
            <body>
            <svg width="0" height="0" style="position:absolute" aria-hidden="true"><defs>
              <linearGradient id="lg" x1="0" y1="0" x2="1" y2="1"><stop offset="0" stop-color="#4338ca"/><stop offset="1" stop-color="#0d9488"/></linearGradient>
              <symbol id="i-search" viewBox="0 0 24 24"><circle cx="11" cy="11" r="8"/><path d="M21 21l-4.35-4.35"/></symbol>
              <symbol id="i-sun" viewBox="0 0 24 24"><circle cx="12" cy="12" r="5"/><path d="M12 1v2M12 21v2M4.22 4.22l1.42 1.42M18.36 18.36l1.42 1.42M1 12h2M21 12h2M4.22 19.78l1.42-1.42M18.36 5.64l1.42-1.42"/></symbol>
              <symbol id="i-moon" viewBox="0 0 24 24"><path d="M21 12.79A9 9 0 1111.21 3 7 7 0 0021 12.79z"/></symbol>
              <symbol id="i-system" viewBox="0 0 24 24"><rect x="3" y="4" width="18" height="12" rx="2"/><path d="M8 20h8M12 16v4"/></symbol>
              <symbol id="i-chev" viewBox="0 0 24 24"><path d="M6 9l6 6 6-6"/></symbol>
            </defs></svg>
            <a class="skip" href="#list">Skip to problems</a>
            <header class="appbar" id="appbar"><div class="appbar-in">
              <div class="brand">
                <svg class="logo" viewBox="0 0 44 44" aria-hidden="true"><rect width="44" height="44" rx="12" fill="url(#lg)"/><rect x="11" y="24" width="6" height="10" rx="2" fill="#fff" opacity=".55"/><rect x="19" y="17" width="6" height="17" rx="2" fill="#fff" opacity=".8"/><rect x="27" y="9" width="6" height="25" rx="2" fill="#fff"/></svg>
                <div class="wm"><span class="wm-t"><b>DSA</b> Practice</span><span class="wm-s">LeetCode progress tracker</span></div>
              </div>
              <div class="actions">
                <div class="live" id="live" role="status"><i></i><span id="livetxt">Live</span><span class="ts" id="livets"></span></div>
                <div class="menuwrap">
                  <button type="button" id="themebtn" class="iconbtn" aria-haspopup="menu" aria-expanded="false" aria-label="Choose theme" title="Theme">
                    <svg class="ic" data-ic="light" hidden aria-hidden="true"><use href="#i-sun"/></svg>
                    <svg class="ic" data-ic="dark" hidden aria-hidden="true"><use href="#i-moon"/></svg>
                    <svg class="ic" data-ic="system" aria-hidden="true"><use href="#i-system"/></svg>
                  </button>
                  <div class="menu" id="thememenu" role="menu" aria-label="Theme" hidden>
                    <div class="menu-h">Theme</div>
                    <button type="button" role="menuitemradio" data-t="light" aria-checked="false"><svg class="ic" aria-hidden="true"><use href="#i-sun"/></svg>Light</button>
                    <button type="button" role="menuitemradio" data-t="dark" aria-checked="false"><svg class="ic" aria-hidden="true"><use href="#i-moon"/></svg>Dark</button>
                    <button type="button" role="menuitemradio" data-t="system" aria-checked="true"><svg class="ic" aria-hidden="true"><use href="#i-system"/></svg>Device default</button>
                  </div>
                </div>
              </div>
            </div></header>
            <main>
            """;

    private static final String TAIL = """
            <footer>Status is read live from the <code>Status:</code> line in each problem's header comment. Press <b>/</b> to search.</footer>
            </main>
            <script>
            (function(){
            var st={s:'all',d:'',c:'',q:''},last=null;
            var $=function(x){return document.querySelector(x);},$$=function(x){return document.querySelectorAll(x);};
            function active(){return st.s!=='all'||!!st.d||!!st.c||!!st.q;}
            function apply(){
              var cnt={all:0,todo:0,done:0},shown=0,total=0;
              $$('#list tr[data-s]').forEach(function(r){
                total++;
                var ok=(!st.c||r.dataset.c===st.c)&&(!st.d||r.dataset.d===st.d)&&(!st.q||r.dataset.q.indexOf(st.q)>-1);
                if(ok){cnt.all++;cnt[r.dataset.s]++;}
                var show=ok&&(st.s==='all'||r.dataset.s===st.s);
                r.hidden=!show;
                if(show)shown++;
              });
              $$('[data-n]').forEach(function(e){e.textContent=cnt[e.dataset.n];});
              $('#shown').textContent='Showing '+shown+' of '+total;
              var em=$('#empty');if(em)em.hidden=shown>0;
              $('#clear').hidden=!active();
            }
            function syncChips(){
              $$('.chip').forEach(function(c){
                c.setAttribute('aria-pressed',String(c.dataset.g==='s'?st.s===c.dataset.v:st.d===c.dataset.v));
              });
            }
            $$('.chip').forEach(function(b){
              b.addEventListener('click',function(){
                if(b.dataset.g==='s')st.s=b.dataset.v;else st.d=(st.d===b.dataset.v?'':b.dataset.v);
                syncChips();apply();
              });
            });
            $('#q').addEventListener('input',function(e){st.q=e.target.value.trim().toLowerCase();apply();});
            $('#topic').addEventListener('change',function(e){st.c=e.target.value;apply();});
            $('#clear').addEventListener('click',function(){
              st={s:'all',d:'',c:'',q:''};$('#q').value='';$('#topic').value='';syncChips();apply();
            });

            var btn=$('#themebtn'),menu=$('#thememenu');
            function paintTheme(){
              var t=window.__theme.get();
              $$('[data-ic]').forEach(function(e){e.toggleAttribute('hidden',e.dataset.ic!==t);});
              $$('#thememenu [data-t]').forEach(function(b){b.setAttribute('aria-checked',String(b.dataset.t===t));});
            }
            function openMenu(o){
              menu.hidden=!o;btn.setAttribute('aria-expanded',String(o));
              if(o)menu.querySelector('[aria-checked=true]').focus();
            }
            btn.addEventListener('click',function(e){e.stopPropagation();openMenu(menu.hidden);});
            menu.addEventListener('click',function(e){
              var b=e.target.closest('[data-t]');
              if(b){window.__theme.set(b.dataset.t);paintTheme();openMenu(false);btn.focus();}
            });
            menu.addEventListener('keydown',function(e){
              var items=[].slice.call(menu.querySelectorAll('[data-t]')),i=items.indexOf(document.activeElement);
              if(e.key==='ArrowDown'){e.preventDefault();items[(i+1)%items.length].focus();}
              if(e.key==='ArrowUp'){e.preventDefault();items[(i+items.length-1)%items.length].focus();}
            });
            document.addEventListener('click',function(e){if(!menu.hidden&&!menu.contains(e.target))openMenu(false);});
            document.addEventListener('keydown',function(e){
              var tag=document.activeElement.tagName;
              if(e.key==='/'&&tag!=='INPUT'&&tag!=='SELECT'){e.preventDefault();$('#q').focus();}
              if(e.key==='Escape'){
                if(!menu.hidden){openMenu(false);btn.focus();}
                else if(document.activeElement===$('#q')){$('#q').blur();}
              }
            });
            window.addEventListener('scroll',function(){$('#appbar').classList.toggle('scrolled',window.scrollY>4);},{passive:true});

            function live(ok){
              $('#live').classList.toggle('off',!ok);
              $('#livetxt').textContent=ok?'Live':'Offline';
              $('#livets').textContent=ok?'· updated '+new Date().toLocaleTimeString():'· restart the dashboard';
            }
            async function tick(){
              if(document.hidden)return;
              try{
                var r=await fetch('/',{cache:'no-store'});
                if(!r.ok)throw new Error(r.status);
                var t=await r.text();
                if(t!==last){
                  var d=new DOMParser().parseFromString(t,'text/html');
                  $('#summary').innerHTML=d.querySelector('#summary').innerHTML;
                  $('#list').innerHTML=d.querySelector('#list').innerHTML;
                  last=t;apply();
                }
                live(true);
              }catch(e){live(false);}
            }
            paintTheme();apply();live(true);
            setInterval(tick,REFRESH_MS);
            })();
            </script>
            </body></html>
            """.replace("REFRESH_MS", String.valueOf(REFRESH_SECONDS * 1000));
}
