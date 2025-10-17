import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ReadData {
    public Song[] read() {
        String fileName = "songs 2025-2026.csv";
        int rows = countRows(fileName);
        Song[] list = new Song[rows];

        try {
            Scanner in = new Scanner(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
            if (in.hasNextLine()) in.nextLine();

            int i = 0;
            while (in.hasNextLine() && i < rows) {
                String line = in.nextLine();
                String[] parts = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for (int k = 0; k < parts.length; k++) {
                    parts[k] = parts[k].trim();
                    if (parts[k].length() >= 2 && parts[k].startsWith("\"") && parts[k].endsWith("\"")) {
                        parts[k] = parts[k].substring(1, parts[k].length() - 1);
                    }
                }

                String artist   = take(parts, 0);
                String title    = take(parts, 1);
                int year        = parseYear(take(parts, 2));
                String genre    = take(parts, 3);
                double len      = parseDouble(take(parts, 4));
                double shake    = parseDouble(take(parts, 5));
                double obscene  = parseDouble(take(parts, 6));
                double dance    = parseDouble(take(parts, 7));
                double loud     = parseDouble(take(parts, 8));
                String topic    = parts.length > 9 ? take(parts, 9) : "";

                list[i++] = new Song(artist, title, genre, year, len, shake, obscene, dance, loud, topic);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return list;
    }

    private static String take(String[] a, int i) { return i < a.length ? a[i] : ""; }

    private static int parseYear(String s) {
        if (s == null || s.isEmpty()) return 0;
        for (int i = 0; i + 3 < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) && Character.isDigit(s.charAt(i+1))
                    && Character.isDigit(s.charAt(i+2)) && Character.isDigit(s.charAt(i+3))) {
                return Integer.parseInt(s.substring(i, i+4));
            }
        }
        return 0;
    }

    private static double parseDouble(String s) {
        if (s == null || s.isEmpty()) return 0.0;
        try { return Double.parseDouble(s); } catch (Exception e) { return 0.0; }
    }

    private static int countRows(String fileName) {
        int c = 0;
        try (Scanner in = new Scanner(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            if (in.hasNextLine()) in.nextLine();
            while (in.hasNextLine()) { in.nextLine(); c++; }
        } catch (IOException ignored) {}
        return c;
    }

    public int totalSongs(Song[] list) { return list.length; }

    public int countByYear(Song[] list, int year) {
        int c = 0;
        for (Song s : list) if (s.getYear() == year) c++;
        return c;
    }

    public int countDanceableOver(Song[] list, double t) {
        int c = 0;
        for (Song s : list) if (s.getDance() > t) c++;
        return c;
    }

    public Song longestSong(Song[] list) {
        if (list.length == 0) return null;
        Song best = list[0];
        for (Song s : list) if (s.getLen() > best.getLen()) best = s;
        return best;
    }

    public Song lowestShake(Song[] list) {
        if (list.length == 0) return null;
        Song best = list[0];
        for (Song s : list) if (s.getShake() < best.getShake()) best = s;
        return best;
    }

    public Song loudestSong(Song[] list) {
        if (list.length == 0) return null;
        Song best = list[0];
        for (Song s : list) if (s.getLoud() > best.getLoud()) best = s;
        return best;
    }

    public void printAvgObsceneByDecade(Song[] list) {
        int[] starts = {1950, 1960, 1970, 1980, 1990, 2000, 2010, 2020};
        for (int start : starts) {
            int end = start + 9;
            double sum = 0.0;
            int cnt = 0;
            for (Song s : list) {
                int y = s.getYear();
                if (y >= start && y <= end) { sum += s.getObscene(); cnt++; }
            }
            if (cnt > 0) System.out.printf("%d-%d: %.4f%n", start, end, sum / cnt);
            else System.out.printf("%d-%d: no data%n", start, end);
        }
    }

    public int countMentions(Song[] list, String name) {
        String needle = name.toLowerCase();
        int c = 0;
        for (Song s : list) {
            if (s.getArtist().toLowerCase().contains(needle) ||
                s.getTitle().toLowerCase().contains(needle)) c++;
        }
        return c;
    }
}
