package fu.se.spotifi.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fu.se.spotifi.Entities.Song;

@Dao
public interface SongDAO {
    @Insert
    void addSong(Song song);

    @Update
    void updateSong(Song song);

    @Delete
    void deleteSong(Song song);

    @Query("SELECT * FROM songs")
    List<Song> loadAllSongs();
    @Query("SELECT * FROM songs WHERE id = :songId")
    Song getSongById(int songId);

    @Query("SELECT * FROM songs WHERE title LIKE :searchQuery OR artist LIKE :searchQuery")
    List<Song> searchSongs(String searchQuery);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Song song);

}
