import java.io.*; import java.nio.charset.StandardCharsets; import java.util.*;
public class ReadData {
    public Song[] read() {
        String f = "songs 2025-2026.csv";
        int n = rows(f); Song[] a = new Song[n];
        try (Scanner in = new Scanner(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))) {
            if (in.hasNextLine()) in.nextLine();
            int i = 0;
            while (in.hasNextLine() && i < n) {
                String[] p = in.nextLine().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for (int k = 0; k < p.length; k++) {
                    p[k] = p[k].trim();
                    if (p[k].length() >= 2 && p[k].startsWith("\"") && p[k].endsWith("\""))
                        p[k] = p[k].substring(1, p[k].length() - 1);
                }
                String artist = g(p, 0), title = g(p, 1), genre = g(p, 3), topic = p.length > 9 ? g(p, 9) : "";
                int year = y(g(p, 2));
                double len = d(g(p, 4)), shake = d(g(p, 5)), obscene = d(g(p, 6)), dance = d(g(p, 7)), loud = d(g(p, 8));
                a[i++] = new Song(artist, title, genre, year, len, shake, obscene, dance, loud, topic);
            }
        } catch (IOException e) { System.out.println("Error reading file: " + e.getMessage()); }
        return a;
    }
    private static String g(String[] a, int i) { return i < a.length ? a[i] : ""; }
    private static int y(String s) {
        if (s == null || s.isEmpty()) return 0;
        for (int i = 0; i + 3 < s.length(); i++)
            if (Character.isDigit(s.charAt(i)) && Character.isDigit(s.charAt(i+1)) &&
                Character.isDigit(s.charAt(i+2)) && Character.isDigit(s.charAt(i+3)))
                try { return Integer.parseInt(s.substring(i, i+4)); } catch (Exception ignored) {}
        return 0;
    }
    private static double d(String s) { if (s == null || s.isEmpty()) return 0.0; try { return Double.parseDouble(s); } catch (Exception e) { return 0.0; } }
    private static int rows(String f) {
        int c = 0;
        try (Scanner in = new Scanner(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))) {
            if (in.hasNextLine()) in.nextLine();
            while (in.hasNextLine()) { in.nextLine(); c++; }
        } catch (IOException ignored) {}
        return c;
    }
}

