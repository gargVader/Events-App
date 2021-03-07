package com.example.eventsappbottomnav;

import android.app.DownloadManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class EventsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    EventAdapter eventAdapter;
    ShimmerFrameLayout shimmerFrameLayout;

    public EventsFragment() {
        // Required empty public constructor
    }

    public static EventsFragment newInstance(String param1, String param2) {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        shimmerFrameLayout = (ShimmerFrameLayout) view.findViewById(R.id.shimmer_view_container);
//        shimmerFrameLayout.setVisibility(View.VISIBLE);
//        shimmerFrameLayout.startShimmer();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        Query query = FirebaseDatabase.getInstance()
//                .getReference()
//                .child("events");
//
//        FirebaseRecyclerOptions<Event> options =
//                new FirebaseRecyclerOptions.Builder<Event>()
//                        .setQuery(query, Event.class)
//                        .build();

        FirebaseRecyclerOptions<Event> options =
                new FirebaseRecyclerOptions.Builder<Event>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("events"), Event.class)
                        .build();


        eventAdapter = new EventAdapter(options);
        recyclerView.setAdapter(eventAdapter);
        if (shimmerFrameLayout.isShimmerVisible()) {
            shimmerFrameLayout.stopShimmer();
            shimmerFrameLayout.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        eventAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        eventAdapter.stopListening();
    }


}