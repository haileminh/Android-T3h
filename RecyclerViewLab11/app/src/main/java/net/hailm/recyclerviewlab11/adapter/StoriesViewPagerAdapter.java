package net.hailm.recyclerviewlab11.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.hailm.recyclerviewlab11.R;
import net.hailm.recyclerviewlab11.model.Story;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hai_l on 06/11/2017.
 */

public class StoriesViewPagerAdapter extends PagerAdapter {
    private List<Story> stories;
    private Context context;
    private LayoutInflater inflater;
    private CircleImageView cImageAvatar;
    private TextView txtName, txtAuthor, txtStatus, txtChapters, txtType, txtUpdateDate, txtUploadDate, txtContents;

    public StoriesViewPagerAdapter(List<Story> stories, Context context) {
        this.stories = stories;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return stories.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object != null && object instanceof View && object == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_view_pager, container, false);

        // Ánh xạ
        cImageAvatar = view.findViewById(R.id.circle_img_avatar);
        txtName = view.findViewById(R.id.txt_name);
        txtAuthor = view.findViewById(R.id.txt_author);
        txtChapters = view.findViewById(R.id.txt_chapters);
        txtType = view.findViewById(R.id.txt_type);
        txtStatus = view.findViewById(R.id.txt_status);
        txtUpdateDate = view.findViewById(R.id.txt_update_date);
        txtUploadDate = view.findViewById(R.id.txt_upload_date);
        txtContents = view.findViewById(R.id.txt_contents);

        // Đổ dữ liệu lên view
        Story story = stories.get(position);
//        cImageAvatar.setImageResource(story.getAvatar());

        txtName.setText(story.getName());
        txtAuthor.setText("Tác giả: " + story.getAuthor());
        txtType.setText("Thể loại: " + story.getType());
        txtStatus.setText("Trạng thái:" + story.getStatus());
        txtChapters.setText("Số chương: " + story.getChapters());
        txtUpdateDate.setText("Ngày up: " + story.getUpdateDate());
        txtUploadDate.setText("Ngày cập nhật: " + story.getUploadDate());
        txtContents.setText(story.getContent());
        Glide.with(context).load(story.getAvatar()).into(cImageAvatar);

        // Đính view
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
