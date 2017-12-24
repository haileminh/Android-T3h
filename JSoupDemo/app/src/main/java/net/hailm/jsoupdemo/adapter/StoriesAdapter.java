package net.hailm.jsoupdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.hailm.jsoupdemo.R;
import net.hailm.jsoupdemo.model.Story;
import net.hailm.jsoupdemo.view.StoriesDetailActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.List;


/**
 * Created by hai_l on 30/11/2017.
 */

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {
    private static final int MESSAGE_IMAGE = 1000;
    private List<Story> stories;
    private Context context;
    private LayoutInflater inflater;

    private Handler handler;

    public StoriesAdapter(List<Story> stories, Context context) {
        this.stories = stories;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_story, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Story story = stories.get(position);
        // CHo vào thé này à
        // viết cái download ra hàm riêng xemm
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(story.getImageUrl());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = conn.getInputStream();
//            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                    Bitmap bmp = BitmapFactory.decodeStream(inputStream);
//                     if (bmp != null) {
//                        holder.imgStory.setImageBitmap(bmp);// nếu
//                    }
                    // alo
                    // gửi xong nhận ngay ngoài này à

                    Message msg = new Message();
                    msg.what = MESSAGE_IMAGE;
                    msg.obj = bmp;
                    handler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //thử xem
        // alo: Viết code như nào cho n sach đi
        //Viet het khu na a

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bitmap obj = (Bitmap) msg.obj;
                switch (msg.what) {
                    case MESSAGE_IMAGE:
                        holder.imgStory.setImageBitmap(obj);
                        break;
                    default:
                        break;
                }
            }
        };


        holder.txtTitle.setText(story.getTitle());
        holder.txtDescription.setText(story.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Test:" + story.getArticleUrl(), Toast.LENGTH_SHORT).show();

                Intent aritcleUrlIntent = new Intent(context, StoriesDetailActivity.class);
                aritcleUrlIntent.putExtra("ARTICLE_URL", story.getArticleUrl());
                aritcleUrlIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(aritcleUrlIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgStory;
        private TextView txtTitle;
        private TextView txtDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            imgStory = itemView.findViewById(R.id.img_story);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtDescription = itemView.findViewById(R.id.txt_description);
        }
    }
}
