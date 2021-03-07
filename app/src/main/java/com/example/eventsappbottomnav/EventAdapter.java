package com.example.eventsappbottomnav;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EventAdapter extends FirebaseRecyclerAdapter<Event, EventAdapter.eventViewHolder> {

    private static final String LOG_TAG = EventAdapter.class.getSimpleName();
    Context context;

    public EventAdapter(@NonNull FirebaseRecyclerOptions options) {

        super(options);
        Log.e(LOG_TAG, "Event Adapter created");
    }

    @Override
    protected void onBindViewHolder(@NonNull eventViewHolder holder, int position, @NonNull Event event) {

        String eventName = event.getEventName();
        String eventLocation = event.getEventLocation();
        int eventDay = event.getEventDay();
        String eventMonth = event.getEventMonth();
        String eventImage = event.getEventImage();

        holder.eventNameView.setText(eventName);
        holder.eventLocationView.setText(eventLocation);
        holder.eventDayView.setText("" + eventDay);
        holder.eventMonthView.setText(eventMonth);

        // Setting up glide
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(AdapterUtils.getRandomDrawbleColor());
//        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        if (event.getEventImage() != null && !event.getEventImage().isEmpty()) {
            Glide.with(holder.eventImageView.getContext())
                    .load(event.getEventImage())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            holder.progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            holder.progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.eventImageView);
        } else {
            holder.progressBar.setVisibility(View.GONE);
        }
        holder.shareFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messageIntent = new Intent(Intent.ACTION_SEND);
                messageIntent.setType("text/plain");
                String message = eventName + "\n" + eventLocation + "\n" + eventDay + " " + eventMonth;
                messageIntent.putExtra(Intent.EXTRA_TEXT, message);
                context.startActivity(messageIntent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetailsActivity.class);
                intent.putExtra("eventName", eventName);
                intent.putExtra("eventLocation", eventLocation);
                intent.putExtra("eventDay", eventDay);
                intent.putExtra("eventMonth", eventMonth);
                intent.putExtra("eventImage", eventImage);
                context.startActivity(intent);
            }
        });

//        Log.e(LOG_TAG, "Name length" + event.getEventName().length());
    }

    class eventViewHolder extends RecyclerView.ViewHolder {
        ImageView eventImageView;
        TextView eventDayView, eventMonthView, eventNameView, eventLocationView;
        FloatingActionButton shareFabButton, favouriteFabButton;
        ProgressBar progressBar;
        View itemView;

        public eventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventImageView = (ImageView) itemView.findViewById(R.id.eventImage);
            eventDayView = (TextView) itemView.findViewById(R.id.eventDayView);
            eventMonthView = (TextView) itemView.findViewById(R.id.eventMonth);
            eventNameView = (TextView) itemView.findViewById(R.id.eventNameView);
            eventLocationView = (TextView) itemView.findViewById(R.id.eventLocation);
            shareFabButton = (FloatingActionButton) itemView.findViewById(R.id.shareFabButton);
            favouriteFabButton = (FloatingActionButton) itemView.findViewById(R.id.favoriteFabButton);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_load_photo);
            this.itemView = itemView;
        }
    }

    @NonNull
    @Override
    public eventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new eventViewHolder(view);
    }
}
