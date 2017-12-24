package net.hailm.fragmentls14.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.hailm.fragmentls14.R;
import net.hailm.fragmentls14.dialog.EditCongViecDialog;
import net.hailm.fragmentls14.model.CongViec;

import java.util.List;

/**
 * Created by hai_l on 16/11/2017.
 */

public class CongViecAdapter extends RecyclerView.Adapter<CongViecAdapter.ViewHolder> {
    private List<CongViec> congViecs;
    private Context context;
    private LayoutInflater inflater;

    public CongViecAdapter(List<CongViec> congViecs, Context context) {
        this.congViecs = congViecs;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_congviec, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CongViec congViec = congViecs.get(position);
        holder.txtName.setText(congViec.getTenCV());

         // Bat su kien xoa, sua
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditCongViecDialog dialog = new EditCongViecDialog(context);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return congViecs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private ImageView imgEdit;
        private ImageView imgDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_name);
            imgDelete = itemView.findViewById(R.id.img_delete);
            imgEdit = itemView.findViewById(R.id.img_edit);
        }
    }
}
