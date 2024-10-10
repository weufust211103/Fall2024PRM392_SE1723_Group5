package fu.se.spotifi.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "albums")
public class Album {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String artist;
    private int thumbnail;  // for the album thumbnail
    private String cover;    // this can store the path or URL of the song cover

    public Album(String title, String artist, int thumbnail, String cover) {
        this.title = title;
        this.artist = artist;
        this.thumbnail = thumbnail;
        this.cover = cover;  // store the cover image path/URL here
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
