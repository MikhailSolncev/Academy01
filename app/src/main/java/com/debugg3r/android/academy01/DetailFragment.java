package com.debugg3r.android.academy01;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.debugg3r.android.academy01.data.Activity;
import com.debugg3r.android.academy01.data.DataProvider;
import com.debugg3r.android.academy01.data.Talk;

public class DetailFragment extends Fragment {

    private Activity activity = null;

    public DetailFragment() {
    }

    public void setDetails(String time, String title) {
        if (time != null && title != null)
            activity = DataProvider.getInstance().getActivity(time, title);
    }

    public static DetailFragment getInstance(String time, String title) {
        DetailFragment fragment = new DetailFragment();
        fragment.setDetails(time, title);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (activity == null) return;

        TextView title = view.findViewById(R.id.text_view_title);
        TextView track = view.findViewById(R.id.text_view_track);
        TextView speaker = view.findViewById(R.id.text_view_speaker);
        TextView description = view.findViewById(R.id.text_view_description);
        Button back = view.findViewById(R.id.button_all_show);

        description.setMovementMethod(new ScrollingMovementMethod());

        back.setOnClickListener(buttinView -> {
            getActivity().getSupportFragmentManager()
                    .popBackStack();
        });

        if (activity instanceof Talk) {
            if (track.getVisibility() != View.VISIBLE)
                track.setVisibility(View.VISIBLE);
            if (speaker.getVisibility() != View.VISIBLE)
                speaker.setVisibility(View.VISIBLE);
            if (description.getVisibility() != View.VISIBLE)
                description.setVisibility(View.VISIBLE);

            Talk talk = (Talk) activity;
            title.setText(talk.title);
            track.setText(talk.track);
            speaker.setText(talk.getSpeakerName());
            description.setText(talk.description);
            if (talk.track.toLowerCase().equals("android")) {
                ((GradientDrawable) track.getBackground())
                        .setColor(getContext().getResources().getColor(R.color.track_android));

            } else if (talk.track.toLowerCase().equals("frontend")) {
                ((GradientDrawable) track.getBackground())
                        .setColor(getContext().getResources().getColor(R.color.track_frontend));

            } else if (talk.track.toLowerCase().equals("common")) {
                ((GradientDrawable) track.getBackground())
                        .setColor(getContext().getResources().getColor(R.color.track_common));

            } else
                ((GradientDrawable) track.getBackground()).setColor(0xDDDDDD);

        } else {
            title.setText(activity.title);
            if (track.getVisibility() != View.GONE)
                track.setVisibility(View.GONE);
            if (speaker.getVisibility() != View.GONE)
                speaker.setVisibility(View.GONE);
            if (description.getVisibility() != View.GONE)
                description.setVisibility(View.GONE);
        }

    }
}
