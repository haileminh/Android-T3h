package net.hailm.mediademo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.hailm.mediademo.R;
import net.hailm.mediademo.manager.SongManager;
import net.hailm.mediademo.model.Song;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by hai_l on 25/11/2017.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private Context context;
    private List<Song> songs;
    private LayoutInflater inflater;

    // b2: Tao thuoc tinh
    private OnItemClickListener onItemClickListener;


    //    public SongAdapter(Context context, List<Song> songs) {
//        this.context = context;
//        this.songs = songs;
//        inflater = LayoutInflater.from(context);
//    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Song getItem(int pos) {
        return songs.get(pos);
    }

    public SongAdapter(Context context) {
        this.context = context;
        SongManager songManager = new SongManager();
        songs = songManager.getSongs(context);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_song, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Song song = songs.get(position);
        holder.txtTitle.setText(song.getTitle());
        holder.txtArtist.setText("Tác giả: " + song.getArtist());
        holder.txtDuration.setText(song.getDuration() + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                onItemClickListener.onClicked(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtArtist, txtDuration;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtArtist = itemView.findViewById(R.id.txt_artist);
            txtDuration = itemView.findViewById(R.id.txt_duration);

        }
    }


    // B1: tao interface
    public interface OnItemClickListener {
        void onClicked(int position);
    }
}

