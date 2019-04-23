package com.debugg3r.android.academy01;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.debugg3r.android.academy01.data.Lecture;

import java.net.Authenticator;
import java.util.List;

public class ThemeListAdapter extends RecyclerView.Adapter<ThemeListAdapter.ViewHolder> {

    private List<Lecture> data;

    ThemeListAdapter(List<Lecture> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (data.size() < position)
            throw new IndexOutOfBoundsException("Position " + position + " out of " + data.size());

        Lecture lecture = data.get(position);
        holder.theme.setText(lecture.theme);
        holder.time.setText(lecture.time);
        holder.author.setText(lecture.author.name);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView theme;
        TextView author;

        public ViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.list_item_text_view_time);
            theme = itemView.findViewById(R.id.list_item_text_view_theme);
            author = itemView.findViewById(R.id.list_item_text_view_author);
        }
    }
}
