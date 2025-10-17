public class Driver {
    public static void main(String[] args) {
       
        ReadData r = new ReadData();
        Song[] list = r.read();

        // 1) total number of songs
        System.out.println("Total number of songs: " + r.totalSongs(list));

        // 2) totals for specific years
        System.out.println("Songs from 1982: " + r.countByYear(list, 1982));
        System.out.println("Songs from 2000: " + r.countByYear(list, 2000));
        System.out.println("Songs from 2015: " + r.countByYear(list, 2015));

        // 3) danceability > 0.80
        System.out.println("Songs with danceability > 0.80: " + r.countDanceableOver(list, 0.80));

        // 4) longest length
        Song longest = r.longestSong(list);
        System.out.println("Song with largest len: " + (longest == null ? "none" : longest));

        // 5) lowest shakeability
        Song lowShake = r.lowestShake(list);
        System.out.println("Lowest shakeability: " + (lowShake == null ? "none" : lowShake));

        // 6) loudest song
        Song loudest = r.loudestSong(list);
        System.out.println("Loudest song: " + (loudest == null ? "none" : loudest));

        // 7) average obscenity per decade
        System.out.println("Average obscenity by decade:");
        r.printAvgObsceneByDecade(list);

        // 8) Songs with MJ and Whitneyt houston 
        System.out.println("Songs mentioning Michael Jackson: " + r.countMentions(list, "Michael Jackson"));
        System.out.println("Songs mentioning Whitney Houston: " + r.countMentions(list, "Whitney Houston"));
    }
}
