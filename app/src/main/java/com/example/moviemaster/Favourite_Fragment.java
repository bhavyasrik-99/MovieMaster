package com.example.moviemaster;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Favourite_Fragment extends Fragment {
    ArrayList<MoviePojo> pojolist;
    RecyclerView recyclerView;
    MovieViewHolder movieViewHolder;

    public Favourite_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        v = inflater.inflate(R.layout.fragment_favourite_, container, false);
        recyclerView = v.findViewById(R.id.rcvview_favfragment);
        movieViewHolder = ViewModelProviders.of(getActivity()).get(MovieViewHolder.class);
        movieViewHolder.getAllMovie.observe(getActivity(), new Observer<List<MoviePojo>>() {
            @Override
            public void onChanged(@Nullable List<MoviePojo> moviePojos) {
                recyclerView.setAdapter(new MovieAdapter(getActivity(),moviePojos));
            }



    });
    pojolist=new ArrayList<>();
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

return v;
}
}