package net.hailm.recyclerviewlab11.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.hailm.recyclerviewlab11.R;
import net.hailm.recyclerviewlab11.model.Story;
import net.hailm.recyclerviewlab11.view.StoriesDetailActivity;

import java.io.Serializable;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hai_l on 04/11/2017.
 */

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {
    private List<Story> stories;
    private Context context;
    private LayoutInflater inflater;

    public StoriesAdapter(List<Story> stories, Context context) {
        this.stories = stories;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_story, parent, false);
        ViewHolder itemView = new ViewHolder(view);
        return itemView;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Story story = stories.get(position);
        //   holder.imgAvatar.setImageResource(story.getAvatar());
        Glide.with(context).load(story.getAvatar()).into(holder.imgAvatar);
        holder.txtTitle.setText(story.getName());
        holder.txtChuongTg.setText("Chuong: " + story.getChapters() + " -T/g: " + story.getAuthor());
        holder.txtUpdateDate.setText(story.getUpdateDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoriesDetailActivity.class);
                intent.putExtra("LIST", (Serializable) stories);
                intent.putExtra("POS", position);// position nay la cai minh bam'
                // nhưng sang kia k dùng mà
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stories.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgAvatar;
        private TextView txtTitle, txtChuongTg, txtUpdateDate;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.circle_images);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtChuongTg = itemView.findViewById(R.id.txt_chuong_tg);
            txtUpdateDate = itemView.findViewById(R.id.txt_update_date);
        }
    }
}
