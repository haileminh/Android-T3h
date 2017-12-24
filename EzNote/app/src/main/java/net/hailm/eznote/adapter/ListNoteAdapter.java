package net.hailm.eznote.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.hailm.eznote.R;
import net.hailm.eznote.dialog.NoteDialog;
import net.hailm.eznote.model.Note;

import java.util.List;

/**
 * Created by hai_l on 07/12/2017.
 */

public class ListNoteAdapter extends RecyclerView.Adapter<ListNoteAdapter.ViewHolder> {
    private List<Note> notes;
    private Context context;
    private LayoutInflater inflater;

    public ListNoteAdapter(List<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_note, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Note note = notes.get(position);
        holder.txtDate.setText(note.getDate());
        holder.txtContent.setText(note.getContent());
        final String color = note.getColor();
        switch (color) {
            case Note.BG_XANH:
                holder.ln_note.setBackgroundColor(Color.parseColor("#029acc"));
                break;
            case Note.BG_CAM:
                holder.ln_note.setBackgroundColor(Color.parseColor("#ff8902"));
                break;
            case Note.BG_DO:
                holder.ln_note.setBackgroundColor(Color.parseColor("#FF7043"));
                break;
            case Note.BG_TIM:
                holder.ln_note.setBackgroundColor(Color.parseColor("#aa67cc"));
                break;
            default:
                break;
        }

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NoteDialog noteDialog = new NoteDialog(context);
                String date = note.getDate();
                String content = note.getContent();
                noteDialog.setNotification(date, content, "Xác nhận", "Hủy bỏ", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.btn_ok:
                                notes.remove(position);
                                noteDialog.dismiss();
                                notifyDataSetChanged();
                                break;
                            case R.id.btn_cancel:
                                noteDialog.dismiss();
                            default:
                                break;
                        }
                    }
                });
                noteDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDate;
        private TextView txtContent;
        private RelativeLayout ln_note;
        private Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtContent = itemView.findViewById(R.id.txt_list_content);
            ln_note = itemView.findViewById(R.id.ln_note);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
