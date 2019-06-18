package com.example.moviemaster;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    Context context;
    List<MoviePojo> mplist;

    public MovieAdapter(Context context, List<MoviePojo> mplist) {
        this.context = context;
        this.mplist = mplist;
    }





    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
     return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.model,viewGroup,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder myViewHolder, final int i) {
        MoviePojo mpojo;
        mpojo = mplist.get(i);
        myViewHolder.tv.setText(mpojo.title);
        Picasso.with(context).load("http://image.tmdb.org/t/p/w185/"+ mpojo.image).into(myViewHolder.imgv);
        Log.i("image",mpojo.image);
        myViewHolder.imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] str = new String[6];
                str[0] = mplist.get(i).getImage();
                str[1] = mplist.get(i).getTitle();
                str[2] = mplist.get(i).getId();
                str[3] = mplist.get(i).getAvg();
                str[4] = mplist.get(i).getOver();
                str[5] = mplist.get(i).getRelease();
                Intent in = new Intent(context, MovieDetailsActivity.class);
                in.putExtra("hai", str);
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mplist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
TextView tv;
ImageView imgv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.text_model);
            imgv=itemView.findViewById(R.id.image_model);

        }
    }
}