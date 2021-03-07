package com.example.eventsappbottomnav;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;

import java.lang.annotation.Target;

public class EventDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        setTitle("Event details");

         Bundle extras = getIntent().getExtras();
        if(extras==null) return;



        ImageView eventImageView;
        TextView eventDayMonthView, eventNameView, eventLocationView;

        eventImageView = (ImageView) findViewById(R.id.eventImageView);
        eventDayMonthView = (TextView) findViewById(R.id.eventDayMonthView);
        eventNameView = (TextView) findViewById(R.id.eventNameView);
        eventLocationView = (TextView) findViewById(R.id.eventLocationView);

        String eventName = extras.getString("eventName");
        String eventLocation =extras.getString("eventLocation");
        String eventDayMonth =extras.getInt("eventDay")+ " " +extras.getString("eventMonth");
        String eventImage = extras.getString("eventImage");

        eventNameView.setText(eventName);
        eventLocationView.setText(eventLocation);
        eventDayMonthView.setText(eventDayMonth);

        // Setting up glide
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(AdapterUtils.getRandomDrawbleColor());
//        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        if (eventImage != null && !eventImage.isEmpty()) {
            Glide.with(eventImageView.getContext())
                    .load(eventImage)
                    .into(eventImageView);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}