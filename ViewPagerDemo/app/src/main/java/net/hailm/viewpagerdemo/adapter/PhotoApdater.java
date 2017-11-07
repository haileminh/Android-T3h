package net.hailm.viewpagerdemo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.hailm.viewpagerdemo.R;
import net.hailm.viewpagerdemo.model.Photo;

import java.util.List;

/**
 * Created by hai_l on 03/11/2017.
 */

public class PhotoApdater extends PagerAdapter {
    private List<Photo> photos;
    private Context context;
    private LayoutInflater inflater;

    public PhotoApdater(List<Photo> photos, Context context) {
        this.photos = photos;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // Kiểm tra obj có phải view hay ko
        // Nếu là view thì ms đưa lên ViewPager

        return object != null && object instanceof View && object == view;
    }

    @Override
    // Tao view, anh xa , dinh view len viewPager
    public Object instantiateItem(ViewGroup container, int position) {
        // Bước 1: Chuyển xml thành obj
        View view = inflater.inflate(R.layout.item_photo, container, false);

        // Ánh xạ
        ImageView imgPhoto = view.findViewById(R.id.img_photo);
        TextView txtName = view.findViewById(R.id.txt_photo_name);

        // Đổ dữ liệu lên View
        Photo photo = photos.get(position);
        txtName.setText(photo.getName());
        imgPhoto.setImageResource(photo.getPhotoId());

        // Đính view lên viewPager
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
