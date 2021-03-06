package com.debugg3r.android.academy01;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.debugg3r.android.academy01.data.Activity;
import com.debugg3r.android.academy01.data.Talk;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThemeListAdapter extends RecyclerView.Adapter<ThemeListAdapter.BaseViewHolder> implements DataAdapter<Activity> {

    private static final int TYPE_TALK = 9001;
    private static final int TYPE_ACTIVITY = 9002;

    private static Map<String, Drawable> flags;
    private List<Activity> data;

    ThemeListAdapter(List<Activity> data) {
        this.data = data;
    }

    @NotNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        if (flags == null) initFlags(context);

        BaseViewHolder holder;
        if (viewType == TYPE_ACTIVITY) {
            View view = LayoutInflater.from(context).inflate(R.layout.list_item_activity, parent, false);
            holder = new ActivityViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.list_item_talk, parent, false);
            holder = new TalkViewHolder(view);
        }

        return holder;
    }

    private void initFlags(Context context) {
        flags = new HashMap<>();
        flags.put("de", context.getDrawable(R.drawable.f_ger));
        flags.put("ru", context.getDrawable(R.drawable.f_rus));
        flags.put("gb", context.getDrawable(R.drawable.f_uk));
        flags.put("ua", context.getDrawable(R.drawable.f_ukr));
        flags.put("us", context.getDrawable(R.drawable.f_usa));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (data.size() < position)
            throw new IndexOutOfBoundsException("Position " + position + " out of " + data.size());

        Activity lecture = data.get(position);
        if (holder.getItemViewType() == TYPE_TALK)
            ((TalkViewHolder) holder).bind((Talk) lecture);
        else
            ((ActivityViewHolder) holder).bind(lecture);
    }

    @Override
    public int getItemViewType(int position) {
        if (data.size() < position)
            throw new IndexOutOfBoundsException("Position " + position + " out of " + data.size());

        if (data.get(position) instanceof Talk)
            return TYPE_TALK;
        return TYPE_ACTIVITY;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void updateData(List<Activity> newData) {
        if (data == null)
            data = newData;
        else {
            List<Activity> oldData = this.data;
            this.data = newData;
            DiffCallback callback = new DiffCallback(oldData, newData);
            DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this);
        }
    }

    abstract class BaseViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView theme;
        Activity activity;

        BaseViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.list_item_text_view_time);
            theme = itemView.findViewById(R.id.list_item_text_view_theme);

            setupOnClick(itemView);
        }

        abstract void bind(Activity activity);

        void setupOnClick(View itemView) {
            itemView.setOnClickListener(view -> {
                Context context = itemView.getContext();
                if (context instanceof ListFragment.OnFragmentInteractionListener) {
                    ((ListFragment.OnFragmentInteractionListener) context)
                            .onFragmentInteraction(activity.time, activity.title);
                }
//                Intent intent = new Intent(context, DetailActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("lecture", activity);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
            });
        }

    }

    class TalkViewHolder extends BaseViewHolder {
        TextView speaker;
        TextView track;
        TextView room;
        ImageView speakerCountry;
        TextView speakerCompany;

        TalkViewHolder(View itemView) {
            super(itemView);
            speaker = itemView.findViewById(R.id.list_item_text_view_speaker);
            track = itemView.findViewById(R.id.list_item_text_view_track);
            room = itemView.findViewById(R.id.list_item_text_view_room);
            speakerCountry = itemView.findViewById(R.id.list_item_image_view_speaker_country);
            speakerCompany = itemView.findViewById(R.id.list_item_text_view_speaker_company);
        }

        @Override
        void bind(Activity activity) {
            this.activity = activity;

            Talk talk = (Talk) activity;
            theme.setText(talk.title);
            time.setText(talk.time);
            speaker.setText(talk.getSpeakerName());
            room.setText("Room " + talk.room);

            track.setText(talk.track);
            if (talk.track.toLowerCase().equals("android")) {
                ((GradientDrawable) track.getBackground())
                        .setColor(track.getContext().getResources().getColor(R.color.track_android));

            } else if (talk.track.toLowerCase().equals("frontend")) {
                ((GradientDrawable) track.getBackground())
                        .setColor(track.getContext().getResources().getColor(R.color.track_frontend));

            } else if (talk.track.toLowerCase().equals("common")) {
                ((GradientDrawable) track.getBackground())
                        .setColor(track.getContext().getResources().getColor(R.color.track_common));

            } else
                ((GradientDrawable) track.getBackground()).setColor(0xDDDDDD);

            speakerCompany.setText(talk.getSpeakerCompany());

            String location = talk.getSpeakerCountry().toLowerCase();
            speakerCountry.setBackground(flags.get(location));

        }
    }

    class ActivityViewHolder extends BaseViewHolder {

        ActivityViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(Activity activity) {
            this.activity = activity;
            theme.setText(activity.title);
            time.setText(activity.time);
        }
    }


}
