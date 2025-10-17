public class Song {
    private String artist;
    private String title;
    private String genre;
    private int year;
    private double len;
    private double shake;
    private double obscene;
    private double dance;
    private double loud;
    private String topic;

    public Song(String artist, String title, String genre, int year,
                double len, double shake, double obscene,
                double dance, double loud, String topic) {
        this.artist = artist;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.len = len;
        this.shake = shake;
        this.obscene = obscene;
        this.dance = dance;
        this.loud = loud;
        this.topic = topic;
    }

    public String getArtist() { return artist; }
    public String getTitle()  { return title; }
    public String getGenre()  { return genre; }
    public int getYear()      { return year; }
    public double getLen()    { return len; }
    public double getShake()  { return shake; }
    public double getObscene(){ return obscene; }
    public double getDance()  { return dance; }
    public double getLoud()   { return loud; }
    public String getTopic()  { return topic; }

    @Override
    public String toString() {
        return title + " — " + artist + " (" + year + ")";
    }
}
