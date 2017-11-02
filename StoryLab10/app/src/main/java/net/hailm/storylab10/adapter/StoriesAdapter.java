package net.hailm.storylab10.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.hailm.storylab10.R;
import net.hailm.storylab10.model.Story;
import net.hailm.storylab10.view.Key;
import net.hailm.storylab10.view.StoryDetailActivity;

import java.util.List;

/**
 * Created by hai_l on 31/10/2017.
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
        // inflater bien xml thanh obj
        View view = inflater.inflate(R.layout.item_story, parent, false);
        ViewHolder itemView = new ViewHolder(view);
        return itemView;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Story story = stories.get(position);
        String author = "- Tác giả: " + story.getAuthor();
        String chapter = "Chương : " + story.getChapters();
        String updateDate = "Ngày cập nhật: " + story.getUpdateDate();

        holder.txtName.setText(story.getName());
        holder.txtChapter.setText(chapter);
        holder.txtAuthor.setText(author);
        holder.txtUpdateDate.setText(updateDate);
        holder.imgAvatar.setImageResource(story.getAvatar());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" + story.getChapters(), Toast.LENGTH_SHORT).show();
                showStoryDetail(position);
            }
        });


    }

    private void showStoryDetail(int pos) {
        Intent intent = new Intent(context, StoryDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Key.NAME, stories.get(pos).getName());
        bundle.putInt(Key.IMG_AVATAR, stories.get(pos).getAvatar());
        bundle.putString(Key.AUTHOR, stories.get(pos).getAuthor());
        bundle.putString(Key.TYPE, stories.get(pos).getType());
        bundle.putString(Key.STATUS, stories.get(pos).getStatus());
        bundle.putString(Key.UPDATE_DATE, stories.get(pos).getUpdateDate());
        bundle.putString(Key.UPLOAD_DATE, stories.get(pos).getUploadDate());
        bundle.putString(Key.CONTENTS, stories.get(pos).getContent());
        bundle.putInt(Key.CHAPTER, stories.get(pos).getChapters());
        intent.putExtra(Key.DATA, bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvatar;
        private TextView txtName;
        private TextView txtAuthor;
        private TextView txtChapter;
        private TextView txtUpdateDate;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            txtAuthor = itemView.findViewById(R.id.txt_author);
            txtChapter = itemView.findViewById(R.id.txt_chapters);
            txtName = itemView.findViewById(R.id.txt_name);
            txtUpdateDate = itemView.findViewById(R.id.txt_updateDate);
        }
    }
}
