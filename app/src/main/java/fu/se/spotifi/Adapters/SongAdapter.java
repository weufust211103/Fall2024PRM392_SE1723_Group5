package fu.se.spotifi.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fu.se.spotifi.Const.Utils;
import fu.se.spotifi.Entities.Song;
import fu.se.spotifi.R;

public class SongAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> { // Use RecyclerView.ViewHolder as generic type
    Context context;
    ArrayList<Song> songs;
    Utils utils = new Utils();
    OnItemClickListener listener;
    private boolean isHomeActivity;

    private static final int HOME_VIEW_TYPE = 0;
    private static final int NORMAL_VIEW_TYPE = 1;

    public interface OnItemClickListener {
        void onItemClick(Song song);
    }

    public SongAdapter(@NonNull Context context, @NonNull ArrayList<Song> songs, OnItemClickListener listener, boolean isHomeActivity) {
        this.context = context;
        this.songs = songs;
        this.listener = listener;
        this.isHomeActivity = isHomeActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;

        if (viewType == HOME_VIEW_TYPE) {
            view = inflater.inflate(R.layout.item_home_song, parent, false);
            return new HomeViewHolder(view); // Returning HomeViewHolder for home layout
        } else {
            view = inflater.inflate(R.layout.item_song, parent, false);
            return new ViewHolder(view); // Returning ViewHolder for normal layout
        }
    }

    @Override
    public int getItemViewType(int position) {
        // Use the isHomeActivity flag to determine which layout to use
        return isHomeActivity ? HOME_VIEW_TYPE : NORMAL_VIEW_TYPE;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Song song = songs.get(position);

        if (holder instanceof HomeViewHolder) {
            HomeViewHolder homeHolder = (HomeViewHolder) holder;
            homeHolder.songTitle.setText(song.getTitle());
            homeHolder.songArtist.setText(song.getArtist());
            Glide.with(context).load(song.getThumbnail()).into(homeHolder.songThumbnail);
            homeHolder.itemView.setOnClickListener(v -> {
                addToQueue(song);
            });
            // Set click listener for home layout
            homeHolder.itemView.setOnClickListener(v -> {
                // Call your method to add the song to the queue
                addToQueue(song);
            });

        } else if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.songTitle.setText(song.getTitle());
            viewHolder.songArtist.setText(song.getArtist());
            viewHolder.duration.setText(utils.milisecondsToString(song.getDuration()));
            Glide.with(context).load(song.getThumbnail()).into(viewHolder.songThumbnail);
            viewHolder.itemView.setOnClickListener(v -> {
                addToQueue(song);
            });
        }
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView songTitle, songArtist, duration;
        ImageView songThumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            songTitle = itemView.findViewById(R.id.songTitle);
            songArtist = itemView.findViewById(R.id.songArtist);
            duration = itemView.findViewById(R.id.songDuration);
            songThumbnail = itemView.findViewById(R.id.songThumbnail);
        }
    }

    // ViewHolder for home layout
    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView songTitle, songArtist;
        ImageView songThumbnail;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            songTitle = itemView.findViewById(R.id.songTitle); // Ensure this matches your item_home_song layout
            songArtist = itemView.findViewById(R.id.songArtist); // Ensure this matches your item_home_song layout
            songThumbnail = itemView.findViewById(R.id.songThumbnail); // Ensure this matches your item_home_song layout
        }
    }
    public interface OnSongClickListener {
        void onSongClick(Song song);
    }
    private void addToQueue(Song song) {
        // Notify the listener if it's set
        if (listener != null) {
            listener.onItemClick(song); // Call the listener's method
        }
    }

}
