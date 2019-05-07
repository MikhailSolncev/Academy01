package com.debugg3r.android.academy01;

import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.debugg3r.android.academy01.data.Lecture;

import java.util.List;

public class ThemeListAdapter extends RecyclerView.Adapter<ThemeListAdapter.ListViewHolder> implements DataAdapter<Lecture> {

    private List<Lecture> data;

    ThemeListAdapter(List<Lecture> data) {
        this.data = data;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        if (data.size() < position)
            throw new IndexOutOfBoundsException("Position " + position + " out of " + data.size());

        Lecture lecture = data.get(position);
        holder.lecture = lecture;
        holder.theme.setText(lecture.theme);
        holder.time.setText(lecture.time);
        holder.author.setText(lecture.author.name);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void updateData(List<Lecture> newData) {
        if (data == null)
            data = newData;
        else {
            List<Lecture> oldData = this.data;
            this.data = newData;
            DiffCallback callback = new DiffCallback(oldData, newData);
            DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this);
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView theme;
        TextView author;
        Lecture lecture;

        ListViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.list_item_text_view_time);
            theme = itemView.findViewById(R.id.list_item_text_view_theme);
            author = itemView.findViewById(R.id.list_item_text_view_author);

            itemView.setOnClickListener(view -> {
                Context context = itemView.getContext();
                Intent intent = new Intent(context, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("lecture", lecture);
                intent.putExtras(bundle);
                context.startActivity(intent);
            });
        }

    }

}
