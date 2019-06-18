package com.example.moviemaster;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {
    ArrayList<Review> rwlist;
    Context context;

    public ReviewAdapter(ArrayList<Review> rwlist, Context context) {
        this.rwlist = rwlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v;
        v= LayoutInflater.from(context).inflate(R.layout.design,viewGroup,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.MyViewHolder myViewHolder, int i) {

        Review rm;
        rm=rwlist.get(i);
        myViewHolder.authork.setText(rm.getAuthor());
        myViewHolder.contentk.setText(rm.getContent());


    }

    @Override
    public int getItemCount() {
        return rwlist.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
            TextView authork;
            TextView contentk;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            authork=itemView.findViewById(R.id.author_id);
            contentk=itemView.findViewById(R.id.content);

        }
    }
}
