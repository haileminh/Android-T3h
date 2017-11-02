package net.hailm.viewpager.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.hailm.viewpager.R;
import net.hailm.viewpager.model.Photo;

import java.util.List;

/**
 * Created by hai_l on 01/11/2017.
 */

public class PhotoAdapter extends PagerAdapter {
    private List<Photo> photos;
    private Context context;
    private LayoutInflater inflater;

    public PhotoAdapter(List<Photo> photos, Context context) {
        this.photos = photos;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    // Check obj có phải view hay ko
    public boolean isViewFromObject(View view, Object object) {
        // Nếu là view thì ms đưa lên ViewPager
        return object != null && object instanceof View && object == view;
    }

    @Override
    // Tao ra view, dua du lieu len view, dinh view len ViewPager
    public Object instantiateItem(ViewGroup container, int position) {
        // Bước 1: CHuyen xml thanh obj view
        View view = inflater.inflate(R.layout.item_photo, container, false);

        // Ánh xạ
        ImageView imgPhoto = view.findViewById(R.id.img_photo);
        TextView txtName = view.findViewById(R.id.txt_photo_name);

        // Bước 2: Đổ dữ liệu lên View
        Photo photo = photos.get(position);
        txtName.setText(photo.getName());
        imgPhoto.setImageResource(photo.getPhotoId());

        // ĐÍnh vào ViewPager
        container.addView(view);
        return view;
    }

    @Override
    // Xóa item khỏi viewPager
    public void destroyItem(ViewGroup container, int position, Object object) {
        // obj là item
        // kiểm tra bằng câu lệnh isViewFromObject
        container.removeView((View) object);
    }
}
