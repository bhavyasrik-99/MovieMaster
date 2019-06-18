package com.example.moviemaster;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.MyViewHolder> {

    ArrayList<Trailer>trlist;
    Context context;

    public TrailerAdapter(ArrayList<Trailer> trlist, Context context) {
        this.trlist = trlist;
        this.context = context;
    }

    @NonNull
    @Override
    public TrailerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v;
        v= LayoutInflater.from(context).inflate(R.layout.trailerdesign,viewGroup,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerAdapter.MyViewHolder myViewHolder, int i) {

        final Trailer design;
        design=trlist.get(i);
        myViewHolder.tv.setText(design.getKey());
        myViewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link;
                link=design.getKey();
                Intent in;
                in=new Intent(Intent.ACTION_VIEW);
                in.setData(Uri.parse("https://www.youtube.com/watch?v="+link));
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trlist.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.trailers_t);

        }
    }
}
